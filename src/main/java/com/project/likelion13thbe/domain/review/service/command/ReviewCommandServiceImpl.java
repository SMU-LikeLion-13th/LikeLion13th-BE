package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.ReviewCreateResDTO createReview(String email, ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Long productId) {
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        Review review = ReviewConverter.toReview(reviewCreateReqDTO, member, product);

        reviewRepository.save(review);

        return ReviewConverter.toReviewCreateResDTO(review);
    }

    @Override
    public void updateReview(String email, ReviewReqDTO.ReviewUpdateReqDTO reviewUpdateReqDTO, Long reviewId) {
        Review review = reviewRepository.findByReviewIdAndNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        log.info("reviewemail={}, email={}", review.getMember().getEmail(), email);
        if (review.getMember().getEmail().equals(email)) {
            review.updateReview(reviewUpdateReqDTO.rating(), reviewUpdateReqDTO.content());
            return;
        }
        throw new ReviewException(ReviewErrorCode.REVIEW_ACCESS_DENIED);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findByReviewIdAndNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        review.delete();
    }

    @Scheduled(cron = "0 0 6 * * *")
    @Transactional
    public void cleanupDeletedReview() {
        log.info("삭제된 리뷰를 제거하는 스케쥴 시작");

        // 일주일 전 날짜 계산
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);

        // 일주일 전 이전에 소프트 딜리트된 리뷰 조회
        List<Review> reviewsToDelete = reviewRepository.findDeletedReviewsBefore(oneWeekAgo);

        if (reviewsToDelete.isEmpty()) {
            log.info("제거할 리뷰가 없습니다.");
            return;
        }
        for (Review review : reviewsToDelete) {
            try {
                log.info("리뷰 삭제 시도: id={}", review.getReviewId());
                reviewRepository.delete(review);
                log.info("리뷰 삭제 성공: id={}", review.getReviewId());
            } catch (Exception e) {
                log.error("리뷰 삭제 실패: id={}, 이유={}", review.getReviewId(), e.getMessage());
            }
        }
        log.info("삭제가 완료되었습니다.\n");
    }
}
