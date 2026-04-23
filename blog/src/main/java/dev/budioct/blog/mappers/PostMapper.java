package dev.budioct.blog.mappers;

import dev.budioct.blog.domain.CreatePostRequest;
import dev.budioct.blog.domain.UpdatePostRequest;
import dev.budioct.blog.domain.dtos.CreatePostRequestDto;
import dev.budioct.blog.domain.dtos.PostDto;
import dev.budioct.blog.domain.dtos.UpdatePostRequestDto;
import dev.budioct.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}
