/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MAHE
 */
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class sendEmail {

    public static void main(String[] args) {
// if unable to login even though right password 
//https://stackoverflow.com/questions/35347269/javax-mail-authenticationfailedexception-535-5-7-8-username-and-password-not-ac
        final String username = "************";
        final String password = "********";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("a97tulsani@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("a97tulsani@gmail.com"));
            message.setSubject("New PlayPal Message");
            message.setText("hello user,"
                + "\n\n Hello From PlayPal!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}