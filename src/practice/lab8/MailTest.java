package practice.lab8;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class MailTest
{
   public static void main(String[] args) throws MessagingException, IOException
   {
      // read properties
      Properties props = new Properties();
      try (InputStream in = Files.newInputStream(Paths.get("F:\\Sustech-CS209-java2\\src\\practice\\lab8\\mail.properties")))
      {
         props.load(in);
      }

      // read message info
      List<String> lines = Files.readAllLines(Paths.get("F:\\Sustech-CS209-java2\\src\\practice\\lab8\\message.txt"), StandardCharsets.UTF_8);

      String from = lines.get(0);
      String to = lines.get(1);
      String subject = lines.get(2);

      StringBuilder builder = new StringBuilder();
      for (int i = 3; i < lines.size(); i++)
      {
         builder.append(lines.get(i));
         builder.append("\n");
      }


      // read password for your email account
      System.out.println("Password: ");
      Scanner scanner = new Scanner(System.in);
      String password = scanner.next();


      Session mailSession = Session.getDefaultInstance(props);
      MimeMessage message = new MimeMessage(mailSession);
      // TODO 1: check the MimeMessage API to figure out how to set the sender, receiver, subject, and email body
      message.setFrom(new InternetAddress(from)); //发件人
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); //收件人
      message.setSubject(subject); //标题
      message.setText(builder.toString()); //消息体
      // TODO 2: check the Session API to figure out how to connect to the mail server and send the message
      Transport ts = mailSession.getTransport();
      ts.connect("smtp.sustech.edu.cn", "11912021@mail.sustech.edu.cn", password);
      ts.sendMessage(message, message.getAllRecipients());
      ts.close();
   }
}