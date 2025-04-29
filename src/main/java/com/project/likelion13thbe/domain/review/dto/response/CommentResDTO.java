package com.project.likelion13thbe.domain.review.dto.response;


import lombok.Getter;

@Getter
public class CommentResDTO {

    public record CommentResponseDTO(
            Long id,
            String content
    ) {
    }

}
