package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequestMapping("api/v1")
public class ProductController {

    @Operation(summary = "상품 목록 조회")
    @GetMapping("/products")
    public ProductResDTO.ProductResponeseDTO getProductList(@PathVariable Long ProductId)
    {
        return null;
    }// 얘도 list써야하나

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/products/{productId}")
    public ProductResDTO.ProductResponeseDTO getProduct(@PathVariable Long ProductId){
        return null;
    }

    @Operation(summary = "상품 등록")
    @PostMapping("/products")
    public ProductResDTO.ProductResponeseDTO postProduct(@PathVariable Long ProductId, @RequestBody ProductReqDTO productReqDTO ){
        return null;
    } // requestbody는 아직 구현 안됌

    @Operation(summary = "상품 삭제")
    @DeleteMapping("products/{productId}")
    public ProductResDTO.ProductResponeseDTO deleteProduct(@PathVariable Long ProductId){
        return null;
    }

}
