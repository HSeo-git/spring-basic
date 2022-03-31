package com.example.springbasic.api;

import com.example.springbasic.annotation.RunningTime;
import com.example.springbasic.dto.CommentDto;
import com.example.springbasic.entity.Comment;
import com.example.springbasic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //check the list of comments
    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        //send to service
        List<CommentDto> dtos = commentService.comments(articleId);
        //response
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //create
    @PostMapping("api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        //send to service
        CommentDto createDto = commentService.create(articleId, dto);
        //response
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    //update
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        CommentDto updateDto = commentService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    //delete
    @RunningTime
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        CommentDto deleted = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
