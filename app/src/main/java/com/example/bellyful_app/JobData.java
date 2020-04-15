package com.example.bellyful_app;


public class JobData {
    private String name;
    private String address;
    private String phone;
    private String food;

    public JobData(String name, String address, String phone, String food) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.food = food;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFood() {
        return food;
    }
    public void setFood(String id) {
        this.food = food;
    }
}


