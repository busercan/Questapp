package com.project.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUsersId(Long usersId);

	List<Comment> findByUsersIdAndPostId(Long usersId, Long postId);

	List<Comment> findByPostId(Long usersId);

}
