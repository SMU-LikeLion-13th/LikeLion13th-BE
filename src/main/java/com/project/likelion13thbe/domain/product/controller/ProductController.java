package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
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
@Tag(name="Product",description = "상품 API")
public class ProductController {

    @Operation(description = "상품 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResDTO.ProductListResDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/products")
    public ResponseEntity<ProductResDTO.ProductListResDTO> getProductList() {
        return null;
    }

    @Operation(description = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResDTO.ProductDetailResDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResDTO.ProductDetailResDTO> getProductDetail(@PathVariable Long productId) {
        return null;
    }

    @Operation(description = "상품 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResDTO.ProductCreateResDTO.class))),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/products")
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(@RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return null;
    }

    @Operation(description = "상품 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return null;
    }
}
