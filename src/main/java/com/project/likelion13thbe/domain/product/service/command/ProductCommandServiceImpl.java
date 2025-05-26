package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductResDTO.ProductCreateResDTO createProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        Product product = ProductConverter.toProduct(productCreateReqDTO);
        productRepository.save(product);
        return ProductConverter.toProductResDTO(product);
    }

    public void updateProduct(Long id, ProductReqDTO.ProductUpdateReqDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getName() != null) {
            product.setName(dto.getName());
        }
        if (product.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (product.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (product.getStock() != null) {
            product.setStock(dto.getStock());
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
}
