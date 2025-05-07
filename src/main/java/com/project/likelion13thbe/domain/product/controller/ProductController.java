package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@Tag(name="Product API", description = "Product 관련 API입니다.")
public class ProductController {

    private final ProductCommandService productCommandService;

    @PostMapping
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(productCreateReqDTO));
    }

    // 상품 목록 조회
    @Operation(summary = "상품 목록 조회")
    @GetMapping("/api/v1/products")
    public ProductResDTO.ProductResponseDTO getProduct() {
        return null;
    }

    // 상품 상세 조회
    @Operation(summary = "상품 상세 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/api/v1/products/{productId}")
    public ProductResDTO.ProductResponseDTO getProduct(
            @PathVariable Long productId
    ) {
        return null;
    }

    // 상품 추가
    @Operation(summary = "상품 추가")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @PostMapping("/api/v1/products/{productId}")
    public ProductResDTO.ProductResponseDTO addProduct(
            @PathVariable Long productId
    ) {
        return null;
    }

    // 상품 삭제
    @Operation(summary = "상품 삭제")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @DeleteMapping("/api/v1/products/{productId}")
    public ProductResDTO.ProductResponseDTO deleteProduct(
            @PathVariable Long productId
    ) {
        return null;
    }
}
