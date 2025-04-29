package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    @Operation(summary = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResDTO.ProductDetailResDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/product/{productId}")
    public ResponseEntity<ProductResDTO.ProductDetailResDTO> getProduct(@PathVariable Long productId) {
        return null;
    }

    @Operation(summary = "상품 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResDTO.ProductListResDTO.class)))
    })
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductResDTO.ProductListResDTO> getProducts() {
        return null;
    }

    @Operation(summary = "상품 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("api/v1/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return null;
    }

    @Operation(summary = "상품 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("api/v1/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return null;
    }
}
