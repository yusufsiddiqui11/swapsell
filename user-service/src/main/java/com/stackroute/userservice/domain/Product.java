package com.stackroute.userservice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {
    private Long id;
    private String name;
    private String title;
    private String description;
    private byte image;
    private double price;
    private String category;
    private String condition;
    private int ageInDays;
    private boolean exchangeable;
    private String state;
    private String city;
    private LocalDateTime addPostedOnDate;

}
