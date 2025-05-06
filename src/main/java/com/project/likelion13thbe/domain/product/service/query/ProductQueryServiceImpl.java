package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO.ProductPreviewResDTO getProduct() {
        // DB에서 pk가 1인 Product 조회
        Product product = productRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return ProductConverter.toProductPreviewResponseDTO(product);
    }

    public ProductResponseDTO.ProductListResponseDTO getProductList() {
        List<Product> productList = productRepository.findAll();

        return ProductConverter.toProductListResponseDTO(productList);
    }
}
