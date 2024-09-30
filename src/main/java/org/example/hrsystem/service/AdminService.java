package org.example.hrsystem.service;

public interface AdminService {
    void lockUser(Long userId, int hoursToLock);
    void unlockUser(Long userId);
}
