package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResponseDTO.ReviewCreateResponseDTO createReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {

        // 임시로 추가
        Member member = memberRepository.findById(reviewCreateRequestDTO.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));


        Review review = ReviewConverter.toReview(reviewCreateRequestDTO, member);

        reviewRepository.save(review);

        return ReviewConverter.toReviewResponseDTO(review);
    }
}
