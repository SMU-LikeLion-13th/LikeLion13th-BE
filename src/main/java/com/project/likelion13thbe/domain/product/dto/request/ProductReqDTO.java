package com.project.likelion13thbe.domain.product.dto.request;

public class ProductReqDTO {

    public record ProductCreateReqDTO(
          String productName,
          Long price,
          Long stars
    ) {}

    public record ProductDeleteDTO(
            Long productId
    ) {}



}
