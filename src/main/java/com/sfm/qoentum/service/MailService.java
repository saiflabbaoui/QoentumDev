package com.sfm.qoentum.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public String sendEmail(long sondeId)  {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("no.reply.qoentum@gmail.com");
        message.setTo("qoentum@sfmtechnologies.com");
        message.setSubject("Problème au niveau de la sonde #"+sondeId+"  (No-reply email)");
        message.setText("Bonjour Mr/Mrs AdminSFM,\n" +
                "On vous envoie cet email pour vous informer du disfonctionnement de la sonde #"+sondeId+" .\n" +
                "Veillez la configurer.\n");


        javaMailSender.send(message);
        return "Mail send successufully";
    }
    public boolean diffDate(Date date1, Date date2) {

        long diff = date2.getTime() - date1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        //System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        //System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        //System.out.println("Time in hours: " + diffHours + " hours.");

        if (diffHours == 4 && diffMinutes > 0 && diffMinutes<6 ) {
            System.out.println("hors service sonde");
            return true;
        } else {
            return false;
        }
    }

}
