package com.project.likelion13thbe.domain.product.controller;


import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandServiceImpl;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryServiceImpl;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
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
@Tag(name="Product",description = "상품 API")
public class ProductController {

    private final ProductCommandServiceImpl productCommandServiceImpl;
    private final ProductQueryServiceImpl productQueryServiceImpl;

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
        return ResponseEntity.ok(productQueryServiceImpl.getProducts());
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
        return ResponseEntity.ok(productQueryServiceImpl.getProduct(productId));
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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandServiceImpl.addProduct(productCreateReqDTO));
    }
    @DeleteMapping("/products/{productId}")
    @Operation(description = "상품 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    public CustomResponse<String> deleteProduct(@PathVariable Long productId ) {
        productCommandServiceImpl.deleteProduct(productId);
        return CustomResponse.onSuccess("상품 삭제 성공");
    }

}
