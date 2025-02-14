package com.tradeplatform.tradeanalytics.repository;

import com.tradeplatform.tradeanalytics.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUid(String uid);  // ✅ Allow searching by uid
    List<User> findByUidIn(List<String> uids);
}