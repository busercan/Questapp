package com.project.questapp.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
	
	Long id;
	String text;
	Long usersId;
	Long postId;

}
