package com.msut.repository;

import com.msut.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mariusz on 10.02.17.
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
