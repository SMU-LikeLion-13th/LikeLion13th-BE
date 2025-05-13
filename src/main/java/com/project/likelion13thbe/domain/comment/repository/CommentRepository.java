package com.project.likelion13thbe.domain.comment.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 커서기반 페이지네이션 게시물마다 댓글을 분리하기 위해 reviewId추가
    Slice<Comment> findByReviewIdAndIdLessThanOrderByIdDesc(Long reviewId, Long cursorId, Pageable pageable);
}
