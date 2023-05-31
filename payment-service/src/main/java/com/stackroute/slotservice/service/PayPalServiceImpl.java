package com.stackroute.slotservice.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalServiceImpl implements PayPalService {
    @Autowired
    private APIContext apiContext;

    // method to create payments
    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {
        System.out.println("In payment service");
        Amount amount = new Amount();


        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f",total));

        System.out.println("total amount "+total);


        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        System.out.println("transaction is "+transaction);


        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        System.out.println("payment is here");
        System.out.println(payment);
        payment.setTransactions(transactionList);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        System.out.println("we are here"+payment.create(apiContext));
        return payment.create(apiContext);
    }


    public Payment executePayment(String paymentId,String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution  paymentExecution  = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        System.out.println("payment execution");
        System.out.println(paymentExecution);
        return payment.execute(apiContext,paymentExecution);
    }

}
