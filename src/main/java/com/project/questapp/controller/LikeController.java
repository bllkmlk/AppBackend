package com.project.questapp.controller;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;


    public LikeController(LikeService likeService) {
        this.likeService = likeService;

    }
    @GetMapping
    public List<LikeResponse> getAllLike(@RequestParam Optional<Long>userId, @RequestParam Optional<Long>postId){
        return likeService.getAllLikeWithParam(userId,postId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }
    @GetMapping("{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @DeleteMapping("{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
