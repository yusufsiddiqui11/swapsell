package com.stackroute.slotservice.service;

import com.paypal.api.payments.Payment;
import com.stackroute.slotservice.domain.User;

public interface UserService {
    User saveUserData(User user);
    void UpdateUserTransaction(String userId,Payment payment);
}
