package com.reddit.redditclone.service;

import org.springframework.stereotype.Service;


public interface MailContentBuilder {
    String build(String message);
}
