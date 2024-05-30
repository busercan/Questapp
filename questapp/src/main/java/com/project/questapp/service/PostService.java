package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Post;
import com.project.questapp.repos.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> getAllPost(Optional<Long> userId) {
		if(userId.isPresent())
			return postRepository.findByUserId(userId.get());
		return postRepository.findAll();
	}

	public Post getOnePostById(@NonNull Long userId) {
		return postRepository.findById(userId).orElse(null);
	}

	public Post saveOnePost(@NonNull Post newPost) {
		return postRepository.save(newPost);
	}

}
