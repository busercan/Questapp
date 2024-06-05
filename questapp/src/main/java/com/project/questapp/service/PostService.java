package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.Users;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UsersService usersService;

	public PostService(PostRepository postRepository, UsersService usersService) {
		this.postRepository = postRepository;
		this.usersService = usersService;
	}

	public List<Post> getAllPost(Optional<Long> userId) {
		if(userId.isPresent())
			return postRepository.findByUsersId(userId.get());
		return postRepository.findAll();
	}

	public Post getOnePostById(@NonNull Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
		Users user = usersService.getOneUser(newPostRequest.getUsersId());
		if(user == null)
			return null;
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUsers(user);
		
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(@NonNull Long postId, PostUpdateRequest postUpdateRequest) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent())
		{
			Post toUpdate = post.get();
			toUpdate.setText(postUpdateRequest.getText());
			toUpdate.setTitle(postUpdateRequest.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(@NonNull Long postId) {
		postRepository.deleteById(postId);
	}
	
	

}
