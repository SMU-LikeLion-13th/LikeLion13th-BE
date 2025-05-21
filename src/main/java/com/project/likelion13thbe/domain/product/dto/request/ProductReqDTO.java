package com.project.likelion13thbe.domain.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ProductReqDTO {

    @Builder
    public record ProductCreateReqDTO(
            @NotBlank(message = "제목은 필수 입력값입니다.")
            String title,
            @NotBlank(message = "내용은 필수 입력값입니다.")
            String content,
            @NotNull(message = "가격은 필수 입력값입니다.")
            Integer price,
            @NotNull(message = "수량은 필수 입력값입니다.")
            @Min(value = 0, message = "최소 수량은 0 입니다.")
            Integer quantity,
            String Image
    ){
    }
}
