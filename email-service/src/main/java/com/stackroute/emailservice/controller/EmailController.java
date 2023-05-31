package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.dto.MailResponse;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService service;

    @PostMapping("/sendingEmail")
    public MailResponse sendMail(@RequestBody MailRequest request){
        Map<String,Object> model = new HashMap<>();
        model.put("productName","Samsung Galaxy F5jd model-903");
        return service.sendEmail(request,model);

    }

    @GetMapping("/email")
    public ResponseEntity<?> mailService(){
        return new ResponseEntity<>("In email service ", HttpStatus.ACCEPTED);
    }
}
