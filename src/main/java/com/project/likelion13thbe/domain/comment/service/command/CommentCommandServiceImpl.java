package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentCreateResDTO createComment(Long productId, Long reviewId, CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {
        Member member = memberRepository.findById(commentCreateReqDTO.memberId())
                .orElseThrow(() -> new RuntimeException("memberId에 해당하는 member가 존재하지 않습니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("productId에 해당하는 product가 존재하지 않습니다."));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("reviewId에 해당하는 review가 존재하지 않습니다."));

        Comment comment = CommentConverter.toComment(commentCreateReqDTO, member, product, review);
        commentRepository.save(comment);
        return CommentConverter.toCommentResponseDTO(comment);
    }
}
