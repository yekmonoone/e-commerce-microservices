package com.example.notificationservice.dto.response;

import lombok.Data;

@Data
public class SendNotificationResponse {

    private Long id;
    private Long userId;
    private String message;
    private String type;
    private String status;
    private String createdAt;
}
