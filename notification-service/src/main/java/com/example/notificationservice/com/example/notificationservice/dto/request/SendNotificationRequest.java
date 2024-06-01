package com.example.notificationservice.dto.request;


import lombok.Data;

@Data
public class SendNotificationRequest {

    private Long userId;
    private String message;
    private String type;




}
