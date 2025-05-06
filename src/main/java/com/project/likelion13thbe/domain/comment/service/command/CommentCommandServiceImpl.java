package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResponseDTO.CommentCreateResponseDTO createComment(CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO) {
        Member member = memberRepository.findById(commentCreateRequestDTO.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Review review = reviewRepository.findById(commentCreateRequestDTO.reviewId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Comment comment = CommentConverter.toComment(commentCreateRequestDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentResponseDTO(comment);
    }
}
