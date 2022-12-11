package com.reddit.redditclone.service;

import com.reddit.redditclone.dto.RegisterRequest;
import org.springframework.stereotype.Service;


public interface AuthService {
    void signup(RegisterRequest registerRequest);
}
