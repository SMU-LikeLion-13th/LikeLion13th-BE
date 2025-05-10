package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;

public interface ProductQueryService {

    public ProductResponseDTO.ProductPreviewResDTO getProduct();
    public ProductResponseDTO.ProductListResponseDTO getProductList();
}
