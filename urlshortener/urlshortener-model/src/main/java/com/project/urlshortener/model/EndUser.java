package com.project.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EndUser {
    @Id
    private String userId;
    private String name;
    private String email;
    private String password;
    private String isVerified;
}
