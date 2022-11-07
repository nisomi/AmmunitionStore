package Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ViaMail {

    public static void sendMessage(String error){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\lab_2\\Ammunition\\src\\main\\java\\Logger\\mail.properties"));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("erreta.les@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("erreta.les@gmail.com"));
            message.setSubject("Critical Error occurred");
            message.setText(error);

            Transport transport = mailSession.getTransport();
            transport.connect("erreta.les@gmail.com",getPassword());
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();


        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String getPassword() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("password.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner.next();
    }
}

