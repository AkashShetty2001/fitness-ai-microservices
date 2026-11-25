package com.fitness.aiService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitness.aiService.schema.Recommendation;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

    List<Recommendation> findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(Long userId);

    Optional<Recommendation> findFirstByActivityIdAndDeletedAtIsNullOrderByCreatedAtDesc(String activityId);
}
