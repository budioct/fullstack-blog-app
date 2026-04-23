package dev.budioct.blog.services;

import dev.budioct.blog.domain.CreatePostRequest;
import dev.budioct.blog.domain.UpdatePostRequest;
import dev.budioct.blog.domain.entities.Post;
import dev.budioct.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    void deletePost(UUID id);
}
