package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.repository.UserRepository;
import org.example.hrsystem.service.AdminService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;

    @Override
    public void lockUser(Long userId, int hoursToLock) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLockExpirationTime(LocalDateTime.now().plusHours(hoursToLock));
            userRepository.save(user);
        }
    }

    @Override
    public void unlockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLockExpirationTime(null);
            userRepository.save(user);
        }
    }
}
