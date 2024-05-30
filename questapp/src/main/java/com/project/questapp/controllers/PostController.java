package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Post;
import com.project.questapp.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController{ 


	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllPost(userId);
	}
	
	@PostMapping 
	public Post createOnePost(@RequestBody @NonNull Post newPost) {
		return postService.saveOnePost(newPost);
	}
	
	
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable @NonNull Long userId){
		return postService.getOnePostById(userId);
	}
	
	

}
