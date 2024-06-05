package com.example.notificationservice.controller;

import com.example.notificationservice.dto.request.GetUserNotificationRequest;
import com.example.notificationservice.dto.request.SendNotificationRequest;
import com.example.notificationservice.dto.request.UpdateNotificationStatusRequest;
import com.example.notificationservice.dto.response.GetUserNotificationResponse;
import com.example.notificationservice.dto.response.SendNotificationResponse;
import com.example.notificationservice.dto.response.UpdateNotificationStatusResponse;
import com.example.notificationservice.model.Notification;
import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/")
    public ResponseEntity<SendNotificationResponse> sendNotification(@RequestBody SendNotificationRequest request) {
        SendNotificationResponse response = notificationService.sendNotification(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        Notification updatedNotification = notificationService.updateNotification(id, notification);
        return ResponseEntity.ok(updatedNotification);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UpdateNotificationStatusResponse> updateNotificationStatus(
            @PathVariable Long id,
            @RequestBody UpdateNotificationStatusRequest request) {

        Notification updatedNotification = notificationService.updateNotificationStatus(id, request.getStatus());

        UpdateNotificationStatusResponse response = new UpdateNotificationStatusResponse();
        response.setId(updatedNotification.getId());
        response.setUserId(updatedNotification.getUserId());
        response.setMessage(updatedNotification.getMessage());
        response.setType(updatedNotification.getType());
        response.setStatus(updatedNotification.getStatus());
        response.setCreatedAt(updatedNotification.getCreatedAt());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
