package com.example.sakuraboutique.Models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String FullName,Password,Email,Address,PhoneNumber;

    public UserModel(String fullName, String password, String email, String address, String phoneNumber) {
        FullName = fullName;
        Password = password;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;
    }

    public UserModel() {
    }

    public String getFullName() {
        return FullName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
