package com.example.notificationservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateNotificationStatusResponse {
    private Long id;
    private Long userId;
    private String message;
    private String type;
    private String status;
    private LocalDateTime createdAt;
}
