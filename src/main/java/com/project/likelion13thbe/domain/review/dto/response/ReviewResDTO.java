package com.project.likelion13thbe.domain.review.dto.response;


import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record ReviewResponseDTO(
            Long id,
            String content
    ){
    }

    @Builder
    public record ReviewPreviewResDTO(
            Long id,
            String username,
            String image,
            String content,
            Double score,
            LocalDateTime createdAt
    ){
    }//상품 id도 있어야하나

    //리뷰 생성
    @Builder
    public record ReviewCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }


    //리뷰 목록 조회
    @Builder
    public record ReviewCursorResDTO(
            List<ReviewPreviewResDTO> reviews,
            boolean hasNext,
            Long nextCursor
    ) {
    }


}
