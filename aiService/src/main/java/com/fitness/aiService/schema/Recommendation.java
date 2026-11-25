package com.fitness.aiService.schema;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document(collection = "recommendations")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Recommendation extends BaseEntity {

    private String activityId;

    private Long userId;

    private List<String> improvements;
    private List<String> safetyMeasures;
    private List<String> suggestions;
}
