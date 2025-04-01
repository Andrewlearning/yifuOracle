package com.example.javawebserver.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SurveyDataModel {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @JsonProperty(required = true)
    public final int age;

    @JsonProperty(required = true)
    public final String gender;

    @JsonProperty(required = true)
    public final String region;

    @JsonProperty(required = true)
    public final String surveyID;

    @JsonProperty(required = true)
    public final int score;

    @JsonCreator
    public SurveyDataModel(
            @JsonProperty(value = "age", required = true) int age,
            @JsonProperty(value = "gender", required = true) String gender,
            @JsonProperty(value = "region", required = true) String region,
            @JsonProperty(value = "surveyID", required = true) String surveyID,
            @JsonProperty(value = "score", required = true) int score) {
        
        if (gender == null || region == null || surveyID == null) {
            throw new IllegalArgumentException("All fields must be non-null");
        }
        
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.surveyID = surveyID;
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("age=%d, gender=%s, region=%s, surveyID=%s, score=%d",
                age, gender, region, surveyID, score);
    }

    public String toJsonString() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Failed to serialize object\"}";
        }
    }
}
