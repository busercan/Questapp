package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.Users;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UsersService usersService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, UsersService usersService, PostService postService) {
		this.commentRepository = commentRepository;
		this.usersService = usersService;
		this.postService = postService;
	}

	public List<Comment> getAllComment(Optional<Long> usersId, Optional<Long> postId) {
		if(usersId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUsersIdAndPostId(usersId.get(), postId.get());
		}else if(usersId.isPresent()) {
			return commentRepository.findByUsersId(usersId.get());
		}else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}else
			return commentRepository.findAll();
	}

	public Comment createOneComment(CommentCreateRequest newCommentRequest) {
		Users user = usersService.getOneUser(newCommentRequest.getUsersId()); 
		Post post = postService.getOnePostById(newCommentRequest.getPostId());
		if(user == null)
			return null;
		if(post == null)
			return null;
		
		Comment toCreate = new Comment();
		toCreate.setId(newCommentRequest.getId());
		toCreate.setText(newCommentRequest.getText());
		toCreate.setUsers(user);
		toCreate.setPost(post);
		
		return commentRepository.save(toCreate);
	}

	public Comment getOneCommentById(@NonNull Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment updateOneCommentById(@NonNull Long commentId, CommentUpdateRequest commentUpdateRequest) {
		Optional<Comment>comment = commentRepository.findById(commentId);
		if(comment.isPresent())
		{
			Comment toUpdate = comment.get();
			toUpdate.setText(commentUpdateRequest.getText());
			commentRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOneCommentById(@NonNull Long commentId) {
		commentRepository.deleteById(commentId);
		
	}

}
