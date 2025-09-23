package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.ProductMapper;
import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.models.Products;
import com.gtelant.commerce_service.repositories.CategoryRepository;
import com.gtelant.commerce_service.requests.ProductRequest;
import com.gtelant.commerce_service.responses.CategoryResponse;
import com.gtelant.commerce_service.responses.ProductResponse;
import com.gtelant.commerce_service.services.CategoryService;
import com.gtelant.commerce_service.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Products",description = "Product management APIs.")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper,
                             CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all Products.",description = "Return a list of all products.")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<Products> productsList = productService.getAllProducts();
        return ResponseEntity.ok(productsList.stream()
                .map(products -> {
                    ProductResponse response = new ProductResponse(products);
                    response.setCategory(new CategoryResponse(products.getCategories()));
                    return response;
                }).toList());
    }

    @Operation(summary = "Get all Products pagination", description = "Returns a page of Products")
    @GetMapping("/pageForProducts")
    public Page<ProductResponse> getAllProductsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer stockFrom,
            @RequestParam(required = false) Integer stockTo
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getAllProducts(query,categoryId ,stockFrom, stockTo, pageRequest).map(productMapper::toProductResponse);
    }

    @Operation(summary = "Get product by ID",description = "Returns a single product by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved product."),
            @ApiResponse(responseCode = "404",description = "Product not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id){
        Optional<Products> products = productService.getProductById(id);
        if(products.isPresent()){
            ProductResponse response = new ProductResponse(products.get());
            response.setCategory(new CategoryResponse(products.get().getCategories()));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
        //方法二
//        return users.map(value -> ResponseEntity.ok(userMapper.toUserResponse(value)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new product.",description = "Creates a new product and returns the created product.")
    @PostMapping
    public ResponseEntity<ProductResponse> createUser(@RequestBody ProductRequest productRequest){
        Categories categories = categoryService.getCategoryById(productRequest.getCategoryId());
        Products products = productMapper.toProduct(productRequest,null, categories);
        Products createdProduct = productService.createProduct(products);
        return ResponseEntity.ok(productMapper.toProductResponse(createdProduct));
    }

    @Operation(summary = "Update an existing product.",description = "Updates an existing product by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved product."),
            @ApiResponse(responseCode = "404",description = "Product not found.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest){
        Categories categories = categoryService.getCategoryById(productRequest.getCategoryId());
        Products existingProduct = productService.getProductById(id).orElseThrow(() -> new RuntimeException("找不到產品 ID: " + id));
        Products products = productMapper.toProduct(productRequest,existingProduct,categories);
        Products updatedProduct = productService.updatedProduct(id,products);
        System.out.println("== existingProduct = " + existingProduct.getProductId());
        System.out.println("== dto categoryId = " + productRequest.getCategoryId());
        System.out.println("== mapped categoryId = " + products.getCategories().getCategoryId());

        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toProductResponse(updatedProduct));
    }

    @Operation(summary = "Delete a product.",description = "Deletes a product by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successfully deleted product."),
            @ApiResponse(responseCode = "404",description = "Product not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
