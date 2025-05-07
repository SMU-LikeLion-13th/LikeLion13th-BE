package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.convert.ReviewConvert;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateRequestDTO) {


        Member member = memberRepository.findById(reviewCreateRequestDTO.memberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음."));


        Review review = ReviewConvert.toReview(reviewCreateRequestDTO, member);

        reviewRepository.save(review);

        return ReviewConvert.toReviewResDTO(review);
    }
}
