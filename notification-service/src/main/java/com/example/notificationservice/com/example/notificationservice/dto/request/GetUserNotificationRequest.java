package com.example.notificationservice.dto.request;

import lombok.Data;

@Data
public class GetUserNotificationRequest {
    private Long userId;
    private String status;
}
