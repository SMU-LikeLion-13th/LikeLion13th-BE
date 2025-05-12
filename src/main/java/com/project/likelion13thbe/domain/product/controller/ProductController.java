package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(summary = "상품 상세 조회")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProductResDTO.ProductDetailResDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Not Found",
//                    content = @Content(mediaType = "application/json"))
//    })
    @GetMapping("/{productId}")
    public CustomResponse<ProductResDTO.ProductDetailResDTO> getProduct(@PathVariable Long productId) {
        return CustomResponse.onSuccess(productQueryService.getProduct(productId));
    }

    @Operation(summary = "상품 목록 조회")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProductResDTO.ProductListResDTO.class)))
//    })
    @GetMapping
    public CustomResponse<ProductResDTO.ProductListResDTO> getProductList() {
        return CustomResponse.onSuccess(productQueryService.getProductList());
    }

    @Operation(summary = "상품 추가")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "Created",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "400", description = "Bad Request",
//                    content = @Content(mediaType = "application/json"))
//    })
    @PostMapping
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> addProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(productCreateReqDTO));
    }

    @Operation(summary = "상품 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return null;
    }
}
