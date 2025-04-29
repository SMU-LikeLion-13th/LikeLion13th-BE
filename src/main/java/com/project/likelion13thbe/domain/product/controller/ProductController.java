package com.project.likelion13thbe.domain.product.controller;


import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 관련", description = "상품 관련 API")
@RestController
public class ProductController {

    //상품 목록 조회
    @Operation(summary = "상품 목록 조회 API", description = "상품 목록 조회 API입니다.")
    @GetMapping("/api/v1/users/{userId}/products")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ProductResDTO.ProductResponseDTO getProductList(@PathVariable long userId) { return null;}

    //상품 상세 조회
    @Operation(summary = "상품 상세 조회 API", description = "상품 상세 조회 API입니다.")
    @GetMapping("/api/v1/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ProductResDTO.ProductResponseDTO getProduct(@PathVariable long productId){ return null;}

    //상품 추가
    @Operation(summary = "상품 추가 API", description = "상품 추가 API입니다.")
    @PostMapping("/api/v1/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ProductResDTO.ProductResponseDTO postProduct(@PathVariable long productId) { return null;}

    //상품 삭제
    @Operation(summary = "상품 삭제 API", description = "상품 삭제 API")
    @DeleteMapping("/api/v1/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ProductResDTO.ProductResponseDTO deleteProduct(@PathVariable long productId) { return null;}
}
