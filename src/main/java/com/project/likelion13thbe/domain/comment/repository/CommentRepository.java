package com.project.likelion13thbe.domain.comment.repository;


import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 소프트 delete
    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId AND c.deletedAt IS null ")
    Optional<Comment> findByIdNotDeleted(@Param("commentId") Long commentId);

    Page<Comment> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
