package com.project.likelion13thbe.domain.product.dto.request;

import com.project.likelion13thbe.domain.product.entity.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductReqDTO {

    public record ProductCreateReqDTO(
            @NotBlank(message = "상품 이름은 필수 입력값입니다.")
            String name,
            @NotBlank(message = "상품 설명은 필수 입력값입니다.")
            String description,
            @NotBlank(message = "상품 사진은 필수 입력값입니다.")
            String image,
            @NotNull(message = "상품 가격은 필수 입력값입니다.")
            Double price,
            @NotNull(message = "상품 타입은 필수 입력값입니다.")
            ProductType productType,
            @NotNull(message = "상품 수량은 필수 입력값입니다.")
            Integer productQuantity
    ) {
    }
}
