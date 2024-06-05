package com.example.notificationservice.dto.response;

import com.example.notificationservice.model.Notification;
import lombok.Data;

import java.util.List;
@Data
public class GetUserNotificationResponse {
    private List<Notification> notifications;
}
