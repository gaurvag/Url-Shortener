package com.project.urlshortener.repository;

import com.project.urlshortener.model.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence,String> {
}
