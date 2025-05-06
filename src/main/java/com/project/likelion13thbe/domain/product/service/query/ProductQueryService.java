package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;

public interface ProductQueryService {

    ProductResponseDTO.ProductDetailResponseDTO getProduct(Long productId);

    ProductResponseDTO.ProductListResponseDTO getProducts();
}
