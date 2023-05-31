package com.stackroute.productservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@NodeEntity
//@Node("Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String condition;
    private Integer ageInDays;
    private String address;
    private String city;
    private String state;
    private Long pinCode;
    private String date;
    private List<String> images;

    @Transient
    private User seller;
//    @Relationship(type = "SELLER")

    @Relationship(type = "OWNS", direction = Relationship.Direction.INCOMING)
    private OwnsRelationship ownsRelationship;
}
