package com.project.likelion13thbe.domain.review.dto.response;


import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    public record ReviewResponseDTO(
            Long id,
            String content
    ){
    }

    public record ReviewListResponseDTO(
            List<ReviewResponseDTO> reviews
    ){
    }
    public record ReviewCreateDTO(
            Long id,
            String content,
            Integer rating,
            LocalDate createdAt
    ){
    }
    public record ReviewUpdateDTO(
            Long id,
            String content
    ){
    }


}
