package com.fitness.activityService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitness.activityService.schema.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>{

    
} 
