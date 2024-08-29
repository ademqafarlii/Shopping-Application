package org.adem.commentservice.controller;

import org.adem.commentservice.dto.CommentRequest;
import org.adem.commentservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add-comment")
    @ResponseStatus(HttpStatus.CREATED)
    void addComment(@RequestBody CommentRequest commentRequest){
        commentService.addComment(commentRequest);
    }

    @DeleteMapping("/delete-comment-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteCommentByID(@PathVariable Integer id){
        commentService.deleteCommentByID(id);
    }
}
