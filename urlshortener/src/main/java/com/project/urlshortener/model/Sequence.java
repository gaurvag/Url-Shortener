package com.project.urlshortener.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Sequence {
    @Id
    private String sequenceId;
    private Long counterValue;
}
