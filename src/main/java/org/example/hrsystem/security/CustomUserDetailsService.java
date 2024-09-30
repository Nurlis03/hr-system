package org.example.hrsystem.security;

import lombok.AllArgsConstructor;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.repository.UserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (user.getLockExpirationTime() != null && user.getLockExpirationTime().isAfter(LocalDateTime.now())) {
            throw new LockedException("Account with email: " + email + " is locked until " + user.getLockExpirationTime());
        }

        if (user.getLockExpirationTime() != null && user.getLockExpirationTime().isBefore(LocalDateTime.now())) {
            user.setLockExpirationTime(null);
            userRepository.save(user);
        }

        return user;
    }
}
