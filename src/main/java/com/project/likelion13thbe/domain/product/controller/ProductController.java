package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Product", description = "상품 관련 API")
public class ProductController {
    @Operation(description = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "Ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResDTO.ProductDTO.class)
                    )
            )
    })
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductResDTO.ProductDTO> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "상품 목록 조회")
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductResDTO.ProductListDTO> getProducts(){
        return ResponseEntity.ok(null);
    }

    @Operation(description = "상품 생성")
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResDTO.ProductDTO> createProduct(
            @RequestBody ProductReqDTO.CreateProductDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "상품 삭제")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @DeleteMapping("/api/v1/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(null);
    }
}
