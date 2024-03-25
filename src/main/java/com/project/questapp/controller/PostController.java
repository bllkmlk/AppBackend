package com.project.questapp.controller;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.services.PostService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "/posts")
public class PostController {

   private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<Post> getAllPost(@RequestParam Optional<Long> userId){
        return postService.getAllPost(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @GetMapping("{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest){
        return postService.updateOnePostById(postId,updateRequest);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
