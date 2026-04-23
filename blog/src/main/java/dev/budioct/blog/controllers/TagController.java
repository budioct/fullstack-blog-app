package dev.budioct.blog.controllers;

import dev.budioct.blog.domain.dtos.CreateTagsRequest;
import dev.budioct.blog.domain.dtos.TagResponse;
import dev.budioct.blog.domain.entities.Tag;
import dev.budioct.blog.mappers.TagMapper;
import dev.budioct.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getListTags() {
        List<Tag> tags = tagService.getListTag();
        List<TagResponse> tagResponses = tags.stream()
                .map(tag -> tagMapper.toTagResponse(tag))
                .toList();
        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream().map(tag -> tagMapper.toTagResponse(tag)).collect(Collectors.toList());
        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
