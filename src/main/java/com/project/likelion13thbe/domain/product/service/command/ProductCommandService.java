package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;

public interface ProductCommandService {
    public ProductResDTO.ProductCreateResDTO createProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO);
}
