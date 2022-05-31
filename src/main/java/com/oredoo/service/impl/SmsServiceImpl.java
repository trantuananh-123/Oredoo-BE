package com.oredoo.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SmsServiceImpl {
    private final Logger LOGGER = LogManager.getLogger(SmsServiceImpl.class);

    @Value(value = "${account.sid}")
    private String accountSID;

    @Value(value = "${auth.token}")
    private String authToken;

    @Value(value = "${phone.number}")
    private String phoneNumber;

    public void sendMessage(String username, String phone, String password) {
        try {
            Twilio.init(accountSID, authToken);

            String text = "Hello, " + username + ". This is your new password: " + " password";

            PhoneNumber phoneNumberReceiver = new PhoneNumber(phone);
            PhoneNumber phoneNumberSender = new PhoneNumber(phoneNumber);

            MessageCreator
                creator = Message.creator(phoneNumberReceiver, phoneNumberSender, text);
            Message create = creator.create();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
