package dev.budioct.blog.mappers;

import dev.budioct.blog.domain.PostStatus;
import dev.budioct.blog.domain.dtos.CategoryDto;
import dev.budioct.blog.domain.dtos.CreateCategoryRequest;
import dev.budioct.blog.domain.entities.Category;
import dev.budioct.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postsCount", source = "posts", qualifiedByName = "calculatePostsCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostsCount")
    default long calculatePostsCount(List<Post> posts) {
        if (null == posts) {
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }

}
