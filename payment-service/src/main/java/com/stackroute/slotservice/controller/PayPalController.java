package com.stackroute.slotservice.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.slotservice.domain.Order;
import com.stackroute.slotservice.service.PayPalService;
import com.stackroute.slotservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin
public class PayPalController {

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    private final PayPalService payPalService;
//    private final UserService userService;

    @Autowired
    public PayPalController(PayPalService payPalService, UserService userService) {
        this.payPalService = payPalService;
//        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/pay/payment")
    public ResponseEntity<String> payment(@RequestBody Order order) {
        try {
            Payment payment = payPalService.createPayment(order.getPrice(), order.getCurrency(), order.getIntent(), order.getMethod(), order.getDescription(), "http://localhost:8084/" + CANCEL_URL, "http://localhost:8084/" + SUCCESS_URL);

            for (Links link : payment.getLinks()) {
                System.out.println(link);
                if (link.getRel().equals("approval_url")) {
                    System.out.println("in if of the controller");
//                    return "redirect: "+link.getHref();
                    String redirectUrl = link.getHref();
                    System.out.println(redirectUrl);
//                    return ResponseEntity.ok().body(redirectUrl);
                    return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
                }

            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body("{\"redirectUrl\": \"}");
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping("/pay/success")
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException, IOException {

        System.out.println("in the success pay method");
        Payment payment = payPalService.executePayment(paymentId, payerId);
        System.out.println(payment.toJSON());
        String email = payment.getPayer().getPayerInfo().getEmail();

        if (payment.getState().equals("approved")) {
//            userService.UpdateUserTransaction(email, payment);
            InputStream inputStream = getClass().getResourceAsStream("/success.html");
            assert inputStream != null;
            String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            inputStream.close();
            System.out.println("At the end payment is approved and html is shown");
            return ResponseEntity.ok().body(htmlContent);
        }
//        System.out.println();
        return new ResponseEntity<>(payment, HttpStatus.ACCEPTED);
    }

    @GetMapping("pay/target-page")
    public ResponseEntity<?> pageAfterSendingEmail(){
        InputStream inputStream = getClass().getResourceAsStream("/target-page.html");
        assert inputStream != null;
        String htmlContent ;
        try {
            htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(htmlContent);
    }


}
