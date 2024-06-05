package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.Users;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.request.LikeCreateRequest;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UsersService usersService;
	private PostService postService;

	public LikeService(LikeRepository likeRepositoy,UsersService usersService,
			PostService postService) {
		this.likeRepository = likeRepositoy;
		this.usersService = usersService;
		this.postService = postService;
	}
	
	public List<Like> getAllPost(Optional<Long> userId) {
		if(userId.isPresent())
			return likeRepository.findByUsersId(userId.get());
		return likeRepository.findAll();
	}

	public Like createOneLike(LikeCreateRequest newLike) {
		Users user = usersService.getOneUser(newLike.getUsersId()); 
		Post post = postService.getOnePostById(newLike.getPostId());
		if(user == null)
			return null;
		if(post == null)
			return null;
		
		Like toCreate = new Like();
		toCreate.setId(newLike.getId());
		toCreate.setUsers(user);
		toCreate.setPost(post);
		
		return likeRepository.save(toCreate);
	}
	
	public Like getOneLike(@NonNull Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}


	public void deleteOneLike(@NonNull Long likeId) {
		likeRepository.deleteById(likeId);
	}

	
}
