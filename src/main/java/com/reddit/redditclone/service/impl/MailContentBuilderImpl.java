package com.reddit.redditclone.service.impl;

import com.reddit.redditclone.service.MailContentBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Service
public class MailContentBuilderImpl implements MailContentBuilder {

    private final TemplateEngine templateEngine;
    @Override
    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("MailTemplate", context);
    }
}
