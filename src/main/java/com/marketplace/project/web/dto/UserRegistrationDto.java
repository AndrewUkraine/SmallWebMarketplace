package com.marketplace.project.web.dto;

import com.marketplace.project.constraint.FieldMatch;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.PasswordResetToken;
import com.marketplace.project.entities.enums.RoleType;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match"),
})
public class UserRegistrationDto {

    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    @NotBlank(message = "Must by not empty")
    private String firsName;

    @Column(name = "LAST_NAME", length = 30)
    @NotBlank (message = "Must by not empty")
    private String secondName;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 60)
    @NotBlank (message = "Must by not empty")
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    @NotBlank (message = "Must by not empty")
    private String password;

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles;

    @NotBlank (message = "Must by not empty")
    private String matchingPassword;

    //@NotBlank (message = "Must by not empty")
    private String updateNewPassword;

    @Column(name = "PHONE", length = 30, unique = true, nullable = false)
    @Pattern(regexp="(^$|[0-9]{10})")
    @NotBlank(message = "Must by not empty. And has format: 0(XX)xxxxxxx")
    private String phone;

    @Column(name = "CITY", length = 60, nullable = false)
    @NotBlank(message = "Must by not empty")
    private String city;

    @Column(name = "`ACTIVE`", length = 30)
    private boolean active;

    @Column(name = "createdTime", updatable = false)
    private Date created;


    @Column(name = "updatedTime")
    private Date updated;


    @AssertTrue (message = "Please, read and be agree with our conditions")
    private Boolean terms;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }

    public String getUpdateNewPassword() {
        return updateNewPassword;
    }

    public void setUpdateNewPassword(String updateNewPassword) {
        this.updateNewPassword = updateNewPassword;
    }

}
