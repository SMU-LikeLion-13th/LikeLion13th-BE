package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    @Override
    public ProductResDTO.ProductCreateResDTO createProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        Product product = ProductConverter.toProduct(productCreateReqDTO);

        productRepository.save(product);

        return ProductConverter.toProductResDTO(product);
    }
}
