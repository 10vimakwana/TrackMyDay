package com.project.trackmydayapp.model;

public class UserProfileModel {
    Integer profile_id,pro_reg_id;
    String firstname,gender;
    Double  height,weight,age;

    public UserProfileModel(Integer profile_id, Integer pro_reg_id, String firstname, String gender, Double height, Double weight, Double age) {
        this.profile_id = profile_id;
        this.pro_reg_id = pro_reg_id;
        this.firstname = firstname;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Integer getPro_reg_id() {
        return pro_reg_id;
    }

    public void setPro_reg_id(Integer pro_reg_id) {
        this.pro_reg_id = pro_reg_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}
