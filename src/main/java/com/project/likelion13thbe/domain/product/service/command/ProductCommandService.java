package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;

public interface ProductCommandService {
    ProductResponseDTO.ProductCreateResponseDTO createProduct(ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO);

    void deleteProduct(Long productId);
}
