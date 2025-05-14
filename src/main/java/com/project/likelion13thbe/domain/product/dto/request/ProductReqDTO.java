package com.project.likelion13thbe.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

public class ProductReqDTO {

    @Builder
    public record ProductCreateReqDTO(

            @NotBlank(message = "상품 이름은 필수 입력 항목입니다.")
            String name,
            String content,

            @NotNull(message = "가격을 입력해야 합니다.")
            @Positive(message = "가격은 0보다 커야 합니다.")
            Integer price,

            @NotNull(message = "재고를 입력해야 합니다.")
            @Positive(message = "재고는 0 이상이어야 합니다.")
            Integer quantity,
            String image,
            Long memberId
    ) {
    }

}
