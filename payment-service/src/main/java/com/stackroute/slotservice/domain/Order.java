package com.stackroute.slotservice.domain;

import lombok.*;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class Order {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
}
