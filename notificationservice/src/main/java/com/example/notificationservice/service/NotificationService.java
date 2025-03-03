package com.example.notificationservice.service;

import com.example.notificationservice.dto.NotificationRequest;
import com.example.notificationservice.dto.NotificationResponse;
import com.example.notificationservice.model.Notification;

import java.util.List;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest request);

    List<Notification> getUserNotifications(String userId);

    List<Notification> getAllNotifications();


}
