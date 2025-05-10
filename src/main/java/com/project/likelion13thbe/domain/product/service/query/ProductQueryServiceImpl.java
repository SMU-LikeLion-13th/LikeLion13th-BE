package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO.ProductDetailResponseDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        return ProductConverter.toProductDetailResponseDTO(product);
    }

    // 갑자기 든 생각인데 productType에 따른 목록 조회는 ... 흠
    @Override
    public ProductResponseDTO.ProductListResponseDTO getProducts() {
        List<ProductResponseDTO.ProductDetailResponseDTO> products = productRepository.findAll().stream()
                .map(ProductConverter::toProductDetailResponseDTO).toList();

        return ProductResponseDTO.ProductListResponseDTO.builder().productList(products).build();
    }

}
