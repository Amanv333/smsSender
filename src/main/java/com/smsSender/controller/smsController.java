package com.smsSender.controller;

import com.smsSender.payload.smsDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class smsController {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody smsDto dto){
        Twilio.init(accountSid, authToken);
        Message.creator(
                        new PhoneNumber(dto.getTo()),
                        new PhoneNumber(fromPhoneNumber),
                        dto.getMessage())
                .create();

        return new ResponseEntity<>("message sent succesfully", HttpStatus.CREATED);
    }
}
