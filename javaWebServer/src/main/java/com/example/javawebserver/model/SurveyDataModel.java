package com.example.javawebserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyDataModel {

    @JsonProperty
    public int age;

    @JsonProperty
    public String gender;

    @JsonProperty
    public String region;

    @JsonProperty
    public String surveyID;

    @JsonProperty
    public int score;

    @Override
    public String toString() {
        return String.format("age=%d, gender=%s, region=%s, surveyID=%s, score=%d",
                age, gender, region, surveyID, score);
    }
}
