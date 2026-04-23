package dev.budioct.blog.repositories;

import dev.budioct.blog.domain.PostStatus;
import dev.budioct.blog.domain.entities.Category;
import dev.budioct.blog.domain.entities.Post;
import dev.budioct.blog.domain.entities.Tag;
import dev.budioct.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);
    List<Post> findAllByAuthorAndStatus(User user, PostStatus status);
}
