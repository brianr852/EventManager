/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanager;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author brian
 */
public class email {
    
   private String user = "eventmanager852";
   private String pass = "eventmanager";
   
   email (String to, String sub, String msg)
   {
       Properties prop = new Properties();
       prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       prop.put("mail.smtp.auth", true);
       prop.put("mail.smtp.starttls.enable", true);
       prop.put("mail.smtp.host", "smtp.gmail.com");
       prop.put("mail.smtp.port", "587");
       
       Session session = Session.getInstance(prop, new javax.mail.Authenticator()
       {
           protected javax.mail.PasswordAuthentication getPasswordAuthentication()
           {
               return new javax.mail.PasswordAuthentication(user, pass);
           }
       });
       
       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress("no-reply@gmail.com"));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
           message.setSubject(sub);
           message.setText(msg);
           Transport.send(message);
           }
       
       catch (Exception ex)
       {
           System.out.print(ex);
       }
   }
    public static boolean isValidEmailAddress(String email)
    {
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        return result;
     }

    email() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
