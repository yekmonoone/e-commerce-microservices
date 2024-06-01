package com.example.notificationservice.service;

import com.example.notificationservice.dto.request.GetUserNotificationRequest;
import com.example.notificationservice.dto.request.GetUserNotificationRequest;
import com.example.notificationservice.dto.request.SendNotificationRequest;
import com.example.notificationservice.dto.response.SendNotificationResponse;
import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public SendNotificationResponse sendNotification(SendNotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setStatus("SENT");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        notificationRepository.save(notification);

        SendNotificationResponse response = new SendNotificationResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Notification sent successfully.");

        return response;
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        if (existingNotification.isPresent()) {
            Notification updatedNotification = existingNotification.get();
            updatedNotification.setMessage(notification.getMessage());
            updatedNotification.setType(notification.getType());
            updatedNotification.setStatus(notification.getStatus());
            updatedNotification.setUpdatedAt(LocalDateTime.now());
            return notificationRepository.save(updatedNotification);
        } else {
            throw new RuntimeException("Notification not found with id " + id);
        }
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getNotifications(GetUserNotificationRequest request) {
        if (request.getUserId() != null && request.getStatus() != null) {
            return notificationRepository.findByUserIdAndStatus(request.getUserId(), request.getStatus());
        } else if (request.getUserId() != null) {
            return notificationRepository.findByUserId(request.getUserId());
        } else if (request.getStatus() != null) {
            return notificationRepository.findByStatus(request.getStatus());
        } else {
            return notificationRepository.findAll();
        }
    }
    public Notification updateNotificationStatus(Long id, String status) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setStatus(status);
            return notificationRepository.save(notification);
        }
        throw new ResourceNotFoundException("Notification not found with id " + id);
    }
}

