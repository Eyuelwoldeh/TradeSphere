package com.tradeplatform.tradeanalytics.repository;

import com.tradeplatform.tradeanalytics.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
