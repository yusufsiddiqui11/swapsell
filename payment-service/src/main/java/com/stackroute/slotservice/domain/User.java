package com.stackroute.slotservice.domain;

import com.paypal.api.payments.Payment;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class User {
    @MongoId
    private String emailId;
    List<Payment> paymentList;
}
