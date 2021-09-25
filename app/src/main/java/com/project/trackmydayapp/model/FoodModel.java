package com.project.trackmydayapp.model;

public class FoodModel {
    Integer foodId;
    String foodName,commonName,description,samplingDetails;
    Double nitrogen,fat;
    Integer specificGravity,analysedPortion,unanalyPortion;

    public FoodModel(Integer foodId, String foodName, String commonName, String description, String samplingDetails, Double nitrogen, Double fat, Integer specificGravity, Integer analysedPortion, Integer unanalyPortion) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.commonName = commonName;
        this.description = description;
        this.samplingDetails = samplingDetails;
        this.nitrogen = nitrogen;
        this.fat = fat;
        this.specificGravity = specificGravity;
        this.analysedPortion = analysedPortion;
        this.unanalyPortion = unanalyPortion;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSamplingDetails() {
        return samplingDetails;
    }

    public void setSamplingDetails(String samplingDetails) {
        this.samplingDetails = samplingDetails;
    }

    public Double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Integer getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(Integer specificGravity) {
        this.specificGravity = specificGravity;
    }

    public Integer getAnalysedPortion() {
        return analysedPortion;
    }

    public void setAnalysedPortion(Integer analysedPortion) {
        this.analysedPortion = analysedPortion;
    }

    public Integer getUnanalyPortion() {
        return unanalyPortion;
    }

    public void setUnanalyPortion(Integer unanalyPortion) {
        this.unanalyPortion = unanalyPortion;
    }
}
