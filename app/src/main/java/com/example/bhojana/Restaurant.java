package com.example.bhojana;

public class Restaurant {

    String restaurant_name;
    String restaurant_address;
    String restaurant_type;
    String restaurant_rating;
    String restaurant_time;
    String restaurant_service;

    public Restaurant(String restaurant_name, String restaurant_address, String restaurant_type, String restaurant_rating, String restaurant_time, String restaurant_service) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.restaurant_type = restaurant_type;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_time = restaurant_time;
        this.restaurant_service = restaurant_service;
    }

    public Restaurant(){

    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public String getRestaurant_type() {
        return restaurant_type;
    }

    public void setRestaurant_type(String restaurant_type) {
        this.restaurant_type = restaurant_type;
    }

    public String getRestaurant_rating() {
        return restaurant_rating;
    }

    public void setRestaurant_rating(String restaurant_rating) {
        this.restaurant_rating = restaurant_rating;
    }

    public String getRestaurant_time() {
        return restaurant_time;
    }

    public void setRestaurant_time(String restaurant_time) {
        this.restaurant_time = restaurant_time;
    }

    public String getRestaurant_service() {
        return restaurant_service;
    }

    public void setRestaurant_service(String restaurant_service) {
        this.restaurant_service = restaurant_service;
    }
}
