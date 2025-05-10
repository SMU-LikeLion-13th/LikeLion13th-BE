package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO.ProductCreateResponseDTO createProduct(ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO) {
        // DTO -> Product
        Product product = ProductConverter.toProduct(productCreateRequestDTO);

        // Product 엔티티 Db에 저장
        productRepository.save(product);

        // 응답 DTO로 변환 후 return
        return ProductConverter.toProductResponseDTO(product);
    }


}
