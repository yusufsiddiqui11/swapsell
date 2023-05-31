package com.stackroute.productservice.service;

import com.stackroute.productservice.config.MessageConfiguration;
import com.stackroute.productservice.config.UserDTO;
import com.stackroute.productservice.domain.Product;
import com.stackroute.productservice.domain.ProductDTO;
import com.stackroute.productservice.domain.ProductWithSellerDTO;
import com.stackroute.productservice.domain.User;
import com.stackroute.productservice.repository.ProductRepository;
import com.stackroute.productservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // save a product
    @Override
    public Product addNewProduct(ProductDTO productDTO) {
        Product product1 = new Product();
        product1.setName(productDTO.getName());
        product1.setTitle(productDTO.getTitle());
        product1.setDescription(productDTO.getDescription());
        product1.setCategory(productDTO.getCategory());
        product1.setPrice(productDTO.getPrice());
        product1.setAgeInDays(productDTO.getAgeInDays());
        product1.setCondition(productDTO.getCondition());
        product1.setAddress(productDTO.getAddress());
        product1.setCity(productDTO.getCity());
        product1.setState(productDTO.getState());
        product1.setPinCode(productDTO.getPinCode());
        product1.setDate(productDTO.getDate());
        product1.setImages(productDTO.getImages());

        Product newProduct = productRepository.save(product1);

        userRepository.createOwnsRelationship(productDTO.getEmail(), newProduct.getId());

        return newProduct;
    }

    // save an array of products
    @Override
    public List<Product> addNewProducts(List<ProductDTO> productDTOList) {
        List<Product> newProducts = new ArrayList<>();

        for (ProductDTO productDTO : productDTOList) {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setTitle(productDTO.getTitle());
            product.setDescription(productDTO.getDescription());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setAgeInDays(productDTO.getAgeInDays());
            product.setCondition(productDTO.getCondition());
            product.setAddress(productDTO.getAddress());
            product.setCity(productDTO.getCity());
            product.setState(productDTO.getState());
            product.setPinCode(productDTO.getPinCode());
            product.setDate(productDTO.getDate());
            product.setImages(productDTO.getImages());

            Product newProduct = productRepository.save(product);

            userRepository.createOwnsRelationship(productDTO.getEmail(), newProduct.getId());

            newProducts.add(newProduct);
        }

        return newProducts;
    }


    @Override
    public User addNewUser(User user) {
        User user1 = userRepository.save(user);

        return user1;
    }

    @RabbitListener(queues = MessageConfiguration.queueName1)
    public void userDataFromAuthService(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        addNewUser(user);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products;
    }

    @Override
    public List<Product> getAllProductsWithSeller() {
        List<ProductWithSellerDTO> result = productRepository.findAllWithSeller();
        List<Product> products = new ArrayList<>();

        System.out.println(result);
        System.out.println(productRepository.findAllWithSeller());

        for (ProductWithSellerDTO dto : result) {
            Product product = dto.getProduct();
            User seller = dto.getSeller();

            if (product != null) {
                product.setSeller(seller);
                products.add(product);
            }
        }

        return products;
    }

    @Override
    public User getSeller(Long productId) {
        return userRepository.findSellerByProductId(productId);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> products = productRepository.searchProduct(name);

        return products;
    }

    @Override
    public List<Product> getProductsForUser(String email) {
        return productRepository.findAllProductsForUser(email);
    }

    @Override
    public Product deleteProductById(Long id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public User deleteUserByEmail(String email) {
        return userRepository.deleteUserByEmail(email);
    }
}
