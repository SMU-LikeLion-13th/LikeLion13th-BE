package com.project.likelion13thbe.domain.review.dto.response;

import lombok.Getter;
import lombok.Setter;

public class ReviewResDTO {
    public record Test1DTO(
            Long id,
            String content
    ){
    }

    @Getter
    @Setter
    public class Test2DTO{
        private Long id;
        private String content;
    }

}
