package com.project.questapp.request;

import lombok.Data;

@Data
public class LikeCreateRequest {
	
	Long id;
	Long usersId;
	Long postId;
}
