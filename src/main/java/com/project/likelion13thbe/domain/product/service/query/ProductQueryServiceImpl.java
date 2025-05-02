package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ProductResDTO.ProductDetailResDTO getProduct(Long productId) {

        Product product = productRepository.findById(productId).get();

        List<Review> reviewList = reviewRepository.findAll();

        // 뭔가 서비스에 과한 연산을 주는 것 같아 레포지토리에 쿼리로 하는 방법을 해봐야 할 것 같습니다
        Double ratingAvg = reviewList.stream()
                .filter(review -> review.getProduct().getProductId().equals(productId))
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        Integer reviewCount = (int) reviewList.stream()
                .filter(review -> review.getProduct().getProductId().equals(productId))
                .count();

        return ProductConverter.toProductDetailResDTO(product, ratingAvg, reviewCount);
    }

    @Override
    public ProductResDTO.ProductListResDTO getProductList() {
        List<Product> products = productRepository.findAll();

        List<ProductResDTO.ProductDetailResDTO> productDetailResDTOList =
                products.stream()
                        .map(Product::getProductId)
                        .map(this::getProduct)
                        .toList();

        return ProductConverter.toProductListResDTO(productDetailResDTOList);
    }

}
