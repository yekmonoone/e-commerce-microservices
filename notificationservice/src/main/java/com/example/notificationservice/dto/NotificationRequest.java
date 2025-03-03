package com.example.notificationservice.dto;


import lombok.Data;

@Data
public class NotificationRequest {

    private String userId;
    private String message;
    private String type;




}
