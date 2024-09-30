package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.repository.UserRepository;
import org.example.hrsystem.service.UnlockUserTaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UnlockUserTaskServiceImpl implements UnlockUserTaskService {
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void unlockExpiredUsers() {
        List<User> lockedUsers = userRepository.findAllByLockExpirationTimeBefore(LocalDateTime.now());
        for (User user: lockedUsers) {
            user.setLockExpirationTime(null);
            userRepository.save(user);
        }
    }
}
