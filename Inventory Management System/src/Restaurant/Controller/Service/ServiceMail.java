/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class ServiceMail {
    public int sendResetCode(String recipientEmail) {
        int randomCode = -1;
        try {
            Random rand = new Random();
            randomCode = rand.nextInt(999999);
            String host = "smtp.gmail.com";
            String user = "poirot2k4@gmail.com"; // Thay thế bằng email của bạn
            String pass = "jkmbeejnlariqnkt"; // Thay thế bằng mật khẩu ứng dụng của bạn
            String subject = "Resetting Code";
            String message = "Your reset code is " + randomCode;

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);
            //JOptionPane.showMessageDialog(null, "Code has been sent to the email");
            
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();  
        }
        return randomCode;
    }
}
