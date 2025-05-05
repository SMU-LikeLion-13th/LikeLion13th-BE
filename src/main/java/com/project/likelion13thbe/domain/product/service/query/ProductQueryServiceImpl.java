package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.ProductDetailDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    @Override
    public ProductResDTO.ProductDetailResDTO getProduct(Long productId) {
        ProductDetailDTO productDetailDTO = productRepository.findProductWithReviewStats(productId)
                .orElseThrow(() -> new RuntimeException("Product가 존재하지 않음"));

        return ProductConverter.toProductDetailResDTO(
                productDetailDTO.product(), productDetailDTO.ratingAvg(), productDetailDTO.reviewCount().intValue());
    }

    @Override
    public ProductResDTO.ProductListResDTO getProductList() {
        List<ProductDetailDTO> productDetailDTOList = productRepository.findAllProductsWithReviewStats();

        List<ProductResDTO.ProductDetailResDTO> productDetailResDTOList =
                productDetailDTOList.stream()
                        .map(productDetailDTO -> ProductConverter.toProductDetailResDTO(
                                productDetailDTO.product(), productDetailDTO.ratingAvg(), productDetailDTO.reviewCount().intValue()))
                        .toList();

        return ProductConverter.toProductListResDTO(productDetailResDTOList);
    }

}
