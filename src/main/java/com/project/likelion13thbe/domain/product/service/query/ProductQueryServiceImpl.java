package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.convert.ProductConvert;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService{
    private final ProductRepository productRepository;

    // 상품 목록 조회
    @Override
    public ProductResDTO.ProductListResDTO getProducts() {
        List<ProductResDTO.ProductDetailResDTO> product=productRepository.findAll().stream()
                .map(ProductConvert::toProductDetailResponse)
                .collect(Collectors.toList());
        return ProductResDTO.ProductListResDTO.builder().productList(product).build();

    }
    // 상품 상세 조회
    @Override
    public ProductResDTO.ProductDetailResDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
        return ProductConvert.toProductDetailResponse(product);
    }
}
