package com.project.questapp.controller;

import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<Comment> getAllComment(@RequestParam Optional<Long>userId
            ,@RequestParam Optional<Long>postId){
            return commentService.getAllCommentWithParam(userId,postId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request){
        return commentService.createOneComment(request);
    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
            return commentService.getOneCommentById(commentId);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateRequest){
            return commentService.updateOneCommentId(commentId,updateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }
}
