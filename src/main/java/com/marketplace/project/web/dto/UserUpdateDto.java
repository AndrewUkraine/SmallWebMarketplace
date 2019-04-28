package com.marketplace.project.web.dto;

import com.marketplace.project.constraint.FieldMatch;
import com.marketplace.project.entities.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;


public class UserUpdateDto {

    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    @NotBlank(message = "Must by not empty")
    private String firsName;

    @Column(name = "LAST_NAME", length = 30)
    @NotBlank (message = "Must by not empty")
    private String secondName;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 60)
    @NotBlank (message = "Must by not empty")
    private String email;

    private String password;

    private String matchingPassword;

    @Column(name = "PHONE", length = 30, unique = true, nullable = false)
    @Pattern(regexp="(^$|[0-9]{10})")
    @NotBlank(message = "Must by not empty. And has format: 0(XX)xxxxxxx")
    private String phone;

    @Column(name = "CITY", length = 60, nullable = false)
    @NotBlank(message = "Must by not empty")
    private String city;


    @Column(name = "updatedTime")
    private Date updated;


    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

}
