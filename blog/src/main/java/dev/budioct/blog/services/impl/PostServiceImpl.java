package dev.budioct.blog.services.impl;

import dev.budioct.blog.domain.CreatePostRequest;
import dev.budioct.blog.domain.PostStatus;
import dev.budioct.blog.domain.UpdatePostRequest;
import dev.budioct.blog.domain.entities.Category;
import dev.budioct.blog.domain.entities.Post;
import dev.budioct.blog.domain.entities.Tag;
import dev.budioct.blog.domain.entities.User;
import dev.budioct.blog.repositories.PostRepository;
import dev.budioct.blog.services.CategoryService;
import dev.budioct.blog.services.PostService;
import dev.budioct.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

    @Override
    public Post getPost(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }
        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);

    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        post.setStatus(createPostRequest.getStatus());
        post.setAuthor(user);
        post.setReadingTime(calculateReadingTime(createPostRequest.getContent()));

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        post.setCategory(category);

        Set<UUID> tagIds = createPostRequest.getTagIds();
        List<Tag> tags = tagService.getTagsByIds(tagIds);
        post.setTags(new HashSet<>(tags));

        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with id: " + id));

        post.setTitle(updatePostRequest.getTitle());
        String postContent = updatePostRequest.getContent();
        post.setContent(postContent);
        post.setStatus(updatePostRequest.getStatus());
        post.setReadingTime(calculateReadingTime(postContent));

        UUID updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if (!post.getCategory().getId().equals(updatePostRequestCategoryId)) {
            Category newCategory = categoryService.getCategoryById(updatePostRequestCategoryId);
            post.setCategory(newCategory);
        }

        Set<UUID> existingTagIds = post.getTags().stream().map(tag -> tag.getId()).collect(Collectors.toSet());
        Set<UUID> updatePostRequestTagIds = updatePostRequest.getTagIds();
        if (existingTagIds.equals(updatePostRequestTagIds)) {
            List<Tag> newTags = tagService.getTagsByIds(updatePostRequestTagIds);
            post.setTags(new HashSet<>(newTags));
        }

        return postRepository.save(post);
    }

    @Override
    public void deletePost(UUID id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }

    private Integer calculateReadingTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }

        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }

}
