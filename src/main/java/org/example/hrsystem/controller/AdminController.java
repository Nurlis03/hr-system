package org.example.hrsystem.controller;


import lombok.AllArgsConstructor;
import org.example.hrsystem.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/lock/{userId}")
    public void lockUser(
            @PathVariable Long userId,
            @RequestParam int hours
    ) {
        adminService.lockUser(userId, hours);
    }

    @PostMapping("/unlock/{userId}")
    public void unlockUser(@PathVariable Long userId) {
        adminService.unlockUser(userId);
    }
}
