package com.reddit.redditclone.service.impl;

import com.reddit.redditclone.dto.RegisterRequest;
import com.reddit.redditclone.model.NotificationEmail;
import com.reddit.redditclone.model.User;
import com.reddit.redditclone.model.VerificationToken;
import com.reddit.redditclone.repository.UserRepository;
import com.reddit.redditclone.repository.VerificationTokenRepository;
import com.reddit.redditclone.service.AuthService;
import com.reddit.redditclone.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthServiceImpl  implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Override
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your account",
                user.getEmail(),"Thank you for signing up to Spring Reddit, " +
                "please click on the url below to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/"+ token));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
