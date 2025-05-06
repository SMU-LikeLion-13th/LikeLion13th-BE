package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandServiceImpl;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductCommandServiceImpl productCommandServiceImpl;
    private final ProductQueryServiceImpl productQueryServiceImpl;

    @Operation(summary = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.ProductDetailResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductResponseDTO.ProductDetailResponseDTO> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok(productQueryServiceImpl.getProduct(productId));
    }

    @Operation(summary = "상품 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.ProductListResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductResponseDTO.ProductListResponseDTO> getProductList() {
        return ResponseEntity.ok(productQueryServiceImpl.getProducts());
    }

    @Operation(summary = "상품 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.ProductCreateResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponseDTO.ProductCreateResponseDTO> createProduct(@RequestBody ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandServiceImpl.createProduct(productCreateRequestDTO));
    }

    @Operation(summary = "상품 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/api/v1/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return null;
    }
}
