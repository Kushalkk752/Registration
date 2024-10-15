package com.registration.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

//pojo class
public class RegistrationDto {
    @Size(min=2,message = "Name should have minimum of atleast 2 characters")
    private String firstName;
    @Email
    private String email;
    @Size(min = 10, message = "Mobile number should have exactly 10 digits")
    private String mobile;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
