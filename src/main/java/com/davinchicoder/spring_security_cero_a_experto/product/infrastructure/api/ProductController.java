package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Mediator;
import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationQuery;
import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationResult;
import com.davinchicoder.spring_security_cero_a_experto.product.application.command.create.CreateProductRequest;
import com.davinchicoder.spring_security_cero_a_experto.product.application.command.create.CreateProductResponse;
import com.davinchicoder.spring_security_cero_a_experto.product.application.command.delete.DeleteProductRequest;
import com.davinchicoder.spring_security_cero_a_experto.product.application.command.update.UpdateProductRequest;
import com.davinchicoder.spring_security_cero_a_experto.product.application.query.getAll.GetAllProductRequest;
import com.davinchicoder.spring_security_cero_a_experto.product.application.query.getAll.GetAllProductResponse;
import com.davinchicoder.spring_security_cero_a_experto.product.application.query.getById.GetProductByIdRequest;
import com.davinchicoder.spring_security_cero_a_experto.product.application.query.getById.GetProductByIdResponse;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.ProductFilter;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto.CreateProductDto;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto.ProductDto;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto.UpdateProductDto;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product", description = "Product API operations")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping("")
    public ResponseEntity<PaginationResult<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax
    ) {

        log.info("Getting all products");

        PaginationQuery paginationQuery = new PaginationQuery(pageNumber, pageSize, sortBy, direction);

        ProductFilter productFilter = new ProductFilter(name, description, priceMin, priceMax);

        GetAllProductRequest getAllProductRequest = new GetAllProductRequest(paginationQuery, productFilter);

        GetAllProductResponse response = mediator.dispatch(getAllProductRequest);

        PaginationResult<Product> productsPage = response.getProductsPage();

        PaginationResult<ProductDto> productDtoPaginationResult = new PaginationResult<>(
                productsPage.getContent().stream().map(productMapper::mapToProductDto).toList(),
                productsPage.getPage(),
                productsPage.getSize(),
                productsPage.getTotalPages(),
                productsPage.getTotalElements()
        );

        return ResponseEntity.ok(productDtoPaginationResult);
    }

    @Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        log.info("Getting product with id {}", id);

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        log.info("Found product with id {}", id);

        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Save product", description = "Save product")
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {

        log.info("Saving product");

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        CreateProductResponse response = mediator.dispatch(request);

        Product product = response.getProduct();

        log.info("Saved product with id {}", product.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(product.getId().toString()))).build();
    }

    @Operation(summary = "Update product", description = "Update product")
    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid UpdateProductDto productDto) {

        log.info("Updating product with id {}", productDto.getId());

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Updated product with id {}", productDto.getId());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete product", description = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product with id {}", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Deleted product with id {}", id);

        return ResponseEntity.accepted().build();
    }


}
