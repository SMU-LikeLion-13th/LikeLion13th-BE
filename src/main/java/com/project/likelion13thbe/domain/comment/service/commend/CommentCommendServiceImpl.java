package com.project.likelion13thbe.domain.comment.service.commend;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
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
@Transactional
@RequiredArgsConstructor
public class CommentCommendServiceImpl implements CommentCommendService{
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResDTO.CommentCreateResponseDTO createComment(CommentReqDTO.CommentCreateRequestDTO commentCreateRequestDTO) {
        Member member = memberRepository.findById(commentCreateRequestDTO.memberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Review review = reviewRepository.findById(commentCreateRequestDTO.reviewId())
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다."));

        Comment comment = CommentConverter.toComment(commentCreateRequestDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentCreateResponseDTO(comment);
    }

}
