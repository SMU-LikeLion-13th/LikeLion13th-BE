package com.project.likelion13thbe.domain.product.dto.response;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ProductResDTO {
    public record ProductResponeseDTO(
            Long Id,
            String content
    ){
    }

    @Builder
    public record ProductCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ProductPreviewResDTO(
            Long id,
            String name,
            String content,
            String image,
            Integer price,
            Integer quantity,
            Double scoreAvg,
            Integer reviewCount,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ProductCursorResDTO(
            List<ProductPreviewResDTO> products,
            Long nextCursor,
            Boolean hasNext
    ){
    }
}
