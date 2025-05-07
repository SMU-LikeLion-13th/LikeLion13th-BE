package com.project.likelion13thbe.domain.product.dto.request;

import lombok.Builder;

public class ProductReqDTO {

    @Builder
    public record ProductCreateReqDTO(
            String name,
            String content,
            Integer price,
            Integer quantity,
            String image,
            Long memberId
    ) {
    }

}
