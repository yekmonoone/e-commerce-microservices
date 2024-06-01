package com.example.notificationservice.service;

import com.example.notificationservice.dto.request.GetUserNotificationRequest;
import com.example.notificationservice.dto.request.SendNotificationRequest;
import com.example.notificationservice.dto.response.SendNotificationResponse;
import com.example.notificationservice.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    SendNotificationResponse sendNotification(SendNotificationRequest request);

    List<Notification> getUserNotifications(Long userId);

    List<Notification> getAllNotifications();

    Optional<Notification> getNotificationById(Long id);

    Notification createNotification(Notification notification);

    Notification updateNotification(Long id, Notification notification);

    void deleteNotification(Long id);

    List<Notification> getNotifications(GetUserNotificationRequest request);
    Notification updateNotificationStatus(Long id, String status);
}
