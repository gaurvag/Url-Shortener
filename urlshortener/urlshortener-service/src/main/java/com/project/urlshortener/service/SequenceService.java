package com.project.urlshortener.service;

import com.project.urlshortener.model.Sequence;

public interface SequenceService {

    Sequence getSequenceById(String sequenceId);
}
