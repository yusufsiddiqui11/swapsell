package com.stackroute.productservice.repository;

import com.stackroute.productservice.domain.Product;
import com.stackroute.productservice.domain.ProductWithSellerDTO;
import com.stackroute.productservice.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RepositoryRestResource(collectionResourceRel = "Product", path = "Product")
public interface
ProductRepository extends Neo4jRepository<Product, Long> {

    // create a relationship user->product
    @Query(value = "MATCH (a:User {email: $email}), (b:Product)\n" +
            "WHERE ID(b) = $productId\n" +
            "CREATE (a)-[r:OWNS]->(b)")
    @Transactional
    void createOwnsRelationship(@Param("email") String email, @Param("productId") Long productId);

    // fetch products with seller/owner
    @Query("MATCH (u:User)-[:OWNS]->(p:Product) RETURN p, u")
    List<ProductWithSellerDTO> findAllWithSeller();

    @Query("MATCH (u:User)-[:OWNS]->(p:Product) WHERE ID(p) = $productId RETURN u")
    User findSellerByProductId(@Param("productId") Long productId);

    // fetch products for a user
    @Query("MATCH (u:User)-[:OWNS]->(p:Product) WHERE u.email = $email RETURN p")
    List<Product> findAllProductsForUser(@Param("email") String email);

    // search a product by name
    @Query(value = "MATCH (p:Product)\n" +
            "WHERE toLower(p.name) CONTAINS toLower($keyword)\n" +
            "RETURN p")
    List<Product> searchProduct(@Param("keyword") String keyword);

    // delete a product by id
    @Query("MATCH (p:Product) WHERE id(p) = $productId DETACH DELETE p")
    Product deleteProductById(@Param("productId") Long productId);
}