package com.project.trackmydayapp.model;

public class RecipeModel {
    Integer recipeId,userid,foodId,calories;
    String recipeName,recipeDate;

    public RecipeModel(Integer recipeId, Integer userid, Integer foodId, Integer calories, String recipeName, String recipeDate) {
        this.recipeId = recipeId;
        this.userid = userid;
        this.foodId = foodId;
        this.calories = calories;
        this.recipeName = recipeName;
        this.recipeDate = recipeDate;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDate() {
        return recipeDate;
    }

    public void setRecipeDate(String recipeDate) {
        this.recipeDate = recipeDate;
    }
}
