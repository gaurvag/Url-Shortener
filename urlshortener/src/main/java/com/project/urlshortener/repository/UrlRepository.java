package com.project.urlshortener.repository;

import com.project.urlshortener.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url,Long> {
    Url findByShortKey(String shortKey);
}
