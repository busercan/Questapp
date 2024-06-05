package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Like;
import com.project.questapp.request.LikeCreateRequest;
import com.project.questapp.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
	
	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@GetMapping
	public List<Like> getAllLikes(@RequestParam Optional<Long> userId){
		return likeService.getAllPost(userId);
	}
	
	@PostMapping
	public Like createOneLike (@RequestBody LikeCreateRequest newLike) {
		return likeService.createOneLike(newLike);
	}
	
	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable @NonNull Long likeId) {
		return likeService.getOneLike(likeId);
	}
	
	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable @NonNull Long likeId) {
		likeService.deleteOneLike(likeId);
	}

}
