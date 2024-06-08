package com.example.notificationservice.service;

import com.example.notificationservice.dto.NotificationRequest;
import com.example.notificationservice.dto.NotificationResponse;
import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    public WebClient.Builder webClientBuilder;

    private static final String USER_SERVICE_URL = "https://d42da972-e369-4f3a-9fad-829134fc2587.mock.pstmn.io/users/";

    @SneakyThrows
    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {
        try {
            // Fetch user info from User Service and extract the email
            String userResponse = webClientBuilder.build()
                    .get()
                    .uri(USER_SERVICE_URL + request.getUserId())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(userResponse);
            String userEmail = root.path("email").asText();

            Notification notification = new Notification();
            notification.setUserId(request.getUserId());
            notification.setMessage(request.getMessage());
            notification.setType(request.getType());
            notification.setStatus("PENDING");
            notification.setCreatedAt(LocalDateTime.now());

            System.out.println("|||||| EMAIL ||||||"+ userEmail);
            if ("email".equalsIgnoreCase(request.getType())) {
                sendEmail(userEmail, request.getMessage());
                notification.setStatus("SENT");
            }

            notificationRepository.save(notification);

            NotificationResponse response = new NotificationResponse();
            if(notification.getStatus().equals("SENT")){
                response.setStatus("SUCCESS");
                response.setMessage("Notification sent successfully.");

            }
            else{
                response.setStatus("FAIL");
                response.setMessage("Notification can't be sent.");
            }

            return response;

        } catch (Exception e) {
            e.printStackTrace();

            NotificationResponse response = new NotificationResponse();
            response.setStatus("FAILURE");
            response.setMessage("Notification failed to send.");

            return response;
        }
    }

    public void sendEmail(String to, String message_content) throws MessagingException {
        final String username = "ecommercemsproject@outlook.com";
        final String password = "ecommerce2024";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Testing Outlook SMTP");
            message.setText(message_content);

            Transport.send(message);

            System.out.println("Mail sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}