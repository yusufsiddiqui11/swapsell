package com.stackroute.emailservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MailResponse {
    private String message;
    private boolean status;
}
