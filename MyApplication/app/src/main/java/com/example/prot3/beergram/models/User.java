package com.example.prot3.beergram.models;

public class User {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    public User() {
    }

    public User(String email, String firstName, String lastName, String address) {
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
