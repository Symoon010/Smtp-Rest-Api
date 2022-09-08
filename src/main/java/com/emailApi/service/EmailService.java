package com.emailApi.service;

import com.emailApi.model.EmailRequest;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//Component
@Service
public class EmailService {
    EmailRequest emailRequest = new EmailRequest();

    public boolean sendEmail(String subject, String message, String to) {
        boolean flag = false;
        String from  = "symoonzadidsub@gmail.com";
        String pass = "kndnudzxdatwdwxb";

        String  host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

         Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,pass);
            }
        });
        //create message
         MimeMessage mimeMessage = new MimeMessage(session);

        try{
            mimeMessage.setFrom(from);
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            flag = true;

        } catch (Exception exception) {
             exception.printStackTrace();
        }
        return flag;
    }
}
