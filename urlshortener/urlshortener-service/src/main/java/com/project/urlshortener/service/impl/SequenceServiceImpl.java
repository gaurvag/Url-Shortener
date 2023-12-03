package com.project.urlshortener.service.impl;

import com.project.urlshortener.model.Sequence;
import com.project.urlshortener.repository.SequenceRepository;
import com.project.urlshortener.service.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceServiceImpl implements SequenceService {
    private final SequenceRepository sequenceRepository;
    @Override
    public Sequence getSequenceById(String sequenceId) {
        Sequence sequence = sequenceRepository.findById(sequenceId)
                                              .orElse(Sequence.builder()
                                                              .sequenceId(sequenceId)
                                                              .counterValue(0L)
                                                              .build());
        sequence.setCounterValue(sequence.getCounterValue() + 1);
        return sequenceRepository.save(sequence);
    }

}
