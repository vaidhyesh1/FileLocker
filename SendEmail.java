package encrypter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class SendEmail {
private static String USER_NAME = "*****@gmail.com"; 
private static String PASSWORD = "****";
public static void send(String RECIPIENT,String key) {
    String from = USER_NAME;
    String pass = PASSWORD;
    String[] to = { RECIPIENT };
    String subject = "Your password!";
    String body = "Your password to access the file is:"+key;
    sendFromGMail(from, pass, to, subject, body);
}
private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
    Properties props = System.getProperties();
  String host = "smtp.gmail.com";
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);
    try {
        message.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];
        for( int i = 0; i < to.length; i++ ) {
            toAddress[i] = new InternetAddress(to[i]);
        }
        for( int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }
        message.setSubject(subject);
        message.setText(body);
        Transport transport = session.getTransport("smtp");


        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (AddressException ae) {
        ae.printStackTrace();
    }
    catch (MessagingException me) {
        me.printStackTrace();
   }}} 
