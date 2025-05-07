package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.dto.ProductReviewDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public ProductResDTO.ProductPreviewResDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("productId에 해당하는 product가 존재하지 않습니다."));

        Double ratingAvg = reviewRepository.findRatingAvgByProductId(productId);
        Long reviewCount = reviewRepository.findReviewCountByProductId(productId);

        return ProductConverter.toProductPreviewResDTO(product,
                ratingAvg != null ? ratingAvg : 0.0,
                reviewCount);
    }

    @Override
    public ProductResDTO.ProductListResDTO getProductList() {
        List<ProductReviewDTO> productReviewDTOs = productRepository.findAllProductsWithReviewStats();

        List<ProductResDTO.ProductPreviewResDTO> productPreviewResDTOList = productReviewDTOs.stream()
                .map(dto -> ProductConverter.toProductPreviewResDTO(
                        dto.getProduct(), dto.getRatingAvg(), dto.getReviewCount()))
                .collect(Collectors.toList());

        return ProductConverter.toProductPreviewResponseDTOList(productPreviewResDTOList);
    }

}
