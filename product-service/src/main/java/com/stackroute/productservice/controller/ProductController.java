package com.stackroute.productservice.controller;

import com.stackroute.productservice.domain.Product;
import com.stackroute.productservice.domain.ProductDTO;
import com.stackroute.productservice.domain.User;
import com.stackroute.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProductsWithSeller();
    }

    // temp
    @GetMapping("/products2")
    public List<Product> getAllProducts2() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{email}")
    public List<Product> getAllProductsForUser(@PathVariable String email) {
        return productService.getProductsForUser(email);
    }

    @GetMapping("/user/{productId}")
    public User getSeller(@PathVariable Long productId) {
        return productService.getSeller(productId);
    }

    @PostMapping("/product")
    public Product addNewProduct(@RequestBody ProductDTO productDTO) {
        return productService.addNewProduct(productDTO);
    }

    @PostMapping("/products")
    public List<Product> addNewProducts(@RequestBody List<ProductDTO> productDTOList) {
        return productService.addNewProducts(productDTOList);
    }

    @PostMapping("/user")
    public User addNewUser(@RequestBody User user) {
        return productService.addNewUser(user);
    }

    @GetMapping("/product/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @DeleteMapping("/user/{email}")
    public User deleteUser(@PathVariable String email) {
        return productService.deleteUserByEmail(email);
    }

}