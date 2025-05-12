package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;

public interface ProductQueryService {
    ProductResDTO.ProductDetailResDTO getProduct(Long productId);

    ProductResDTO.ProductListResDTO getProductList();

    ProductResDTO.ProductCursorResDTO getProductCursor(Long cursor, Integer size);
}
