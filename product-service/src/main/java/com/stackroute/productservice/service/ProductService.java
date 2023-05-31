package com.stackroute.productservice.service;

import com.stackroute.productservice.domain.Product;
import com.stackroute.productservice.domain.ProductDTO;
import com.stackroute.productservice.domain.User;

import java.util.List;

public interface ProductService {
    Product addNewProduct(ProductDTO productDTO);
    List<Product> addNewProducts(List<ProductDTO> productDTOList);
    User addNewUser(User user);
    List<Product> getAllProducts();
    List<Product> getAllProductsWithSeller();
    User getSeller(Long productId);
    List<Product> getProductsByName(String name);
    List<Product> getProductsForUser(String email);
    Product deleteProductById(Long id);
    User deleteUserByEmail(String email);
}
