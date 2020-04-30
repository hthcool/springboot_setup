package com.hth.springboot.bean.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class AuthorInfo {

    @Value("${test.user.info.name}")
    private String authorName;

    @Value("${test.user.info.email}")
    private String authorEmail;

    @Value("${test.user.info.uuid}")
    private UUID authorUUID;

}
