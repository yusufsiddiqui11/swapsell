package com.stackroute.emailservice.dto;

import lombok.*;

@Data
public class MailRequest {
    private String name;
    private String to;
    private  String from;
    private String subject;
}
