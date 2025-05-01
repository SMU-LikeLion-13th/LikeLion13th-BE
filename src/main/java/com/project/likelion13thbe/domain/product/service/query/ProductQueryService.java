package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;

public interface ProductQueryService {
    public ProductResDTO.ProductDetailResDTO getProduct(Long productId);
}
