package com.emailApi.controller;

import com.emailApi.model.EmailRequest;
import com.emailApi.service.EmailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping("/email")
    public String solve(){
        return "Hello World boys";
    }
    @RequestMapping(value = "/mail",method = RequestMethod.POST)
    public ResponseEntity<?> sentEmail(@RequestBody EmailRequest request) {
        boolean result = this.emailService.sendEmail(request.getSubject(),request.getMessage(), request.getTo());
       if(result==true){
           return  ResponseEntity.ok("Done");
       }
        else   {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
       }
    }

}
