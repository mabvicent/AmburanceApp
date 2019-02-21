package com.example.amburanceapp.model;

public class User {

    private String email,password,ambulance_name,phone,district,hospital ;

    public User() {
    }

    public User(String email, String password, String ambulance_name, String phone, String district, String hospital) {
        this.email = email;
        this.password = password;
        this.ambulance_name = ambulance_name;
        this.phone = phone;
        this.district = district;
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAmbulance_name() {
        return ambulance_name;
    }

    public void setAmbulance_name(String ambulance_name) {
        this.ambulance_name = ambulance_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
