package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Comment;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;
import com.project.questapp.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
			@RequestParam Optional<Long> postId){
		return commentService.getAllComment(userId ,postId);
	}
	
	@PostMapping 
	public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest) {
		
		return commentService.createOneComment(newCommentRequest);
	}
	
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable @NonNull Long commentId){
		return commentService.getOneCommentById(commentId);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable @NonNull Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
		return commentService.updateOneCommentById(commentId, commentUpdateRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable @NonNull Long commentId) {
		commentService.deleteOneCommentById(commentId);
	}
}
