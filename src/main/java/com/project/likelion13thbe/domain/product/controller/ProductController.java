package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name="Product", description = "Product 관련 API입니다.")
public class ProductController {
    private final ProductCommandService productCommandService;

    @PostMapping
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(productCreateReqDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductReqDTO.ProductUpdateReqDTO dto
    ) {
        productCommandService.updateProduct(id, dto);
        return ResponseEntity.ok("Product Updated Successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productCommandService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted Successfully.");
    }

}
