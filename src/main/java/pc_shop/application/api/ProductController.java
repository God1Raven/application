package pc_shop.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pc_shop.application.api.dto.CreateProductRequest;
import pc_shop.application.api.dto.ProductResponse;
import pc_shop.application.api.dto.UpdateProductRequest;
import pc_shop.application.api.mapper.ProductMapper;
import pc_shop.application.service.ProductService;
import pc_shop.application.shared.ProductType;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(mapper
                .modelToResponse(productService
                        .createProduct(mapper
                                .createRequestToModel(request))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(mapper
                .modelToResponse(productService
                        .updateProduct(
                                id, mapper.updateRequestToModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts().stream().map(mapper::modelToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.modelToResponse(productService.getProductById(id)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductResponse>> getProductsByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByType(ProductType.valueOf(type.toUpperCase())).stream().map(mapper::modelToResponse).toList());
    }
}