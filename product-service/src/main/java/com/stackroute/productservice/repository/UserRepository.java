package com.stackroute.productservice.repository;

import com.stackroute.productservice.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "User", path = "User")
public interface UserRepository extends Neo4jRepository<User, Long> {

    // create a relation user -> product
    @Query(value = "MATCH (a:User {email: $email}), (b:Product)\n" +
            "WHERE ID(b) = $productId\n" +
            "CREATE (a)-[r:OWNS]->(b)")
    @Transactional
    void createOwnsRelationship(@Param("email") String firstName, @Param("productId") Long productId);

    @Query("MATCH (u:User)-[:OWNS]->(p:Product) WHERE ID(p) = $productId RETURN u")
    User findSellerByProductId(@Param("productId") Long productId);

    // delete a user
    @Query(value = "MATCH (u:User {email: $email}) DETACH DELETE u")
    @Transactional
    User deleteUserByEmail(@Param("email") String email);

}
