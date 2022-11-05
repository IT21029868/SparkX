package com.example.sparkx.Models;

public class UserModel {

    String FullName;
    String Contact;
    String Password;
    String Email;
    String UserType;

    public UserModel() {
    }

    public UserModel(String fullName, String contact, String password, String email, String userType) {
        FullName = fullName;
        Contact = contact;
        Password = password;
        Email = email;
        UserType = userType;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
