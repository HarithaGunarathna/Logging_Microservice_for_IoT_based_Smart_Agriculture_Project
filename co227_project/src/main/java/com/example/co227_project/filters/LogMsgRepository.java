package com.example.co227_project.filters;

import com.example.co227_project.Audit.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMsgRepository extends MongoRepository<LogMsg,Long> {
}
