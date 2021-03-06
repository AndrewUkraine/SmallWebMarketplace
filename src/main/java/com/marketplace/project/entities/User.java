package com.marketplace.project.entities;

import com.marketplace.project.entities.enums.RoleType;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    @NotBlank (message = "Must by not empty")
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


    @Column(name = "PHONE", length = 30, unique = true, nullable = false)
    @Pattern(regexp="(^$|[0-9]{10})", message = "Input phone in correct format")
    @NotBlank(message = "Must by not empty")
    private String phone;

    @Column(name = "CITY", length = 60, nullable = false)
    @NotBlank(message = "Must by not empty")
    private String city;

    @Column(name = "`ACTIVE`", length = 30)
    private boolean active;

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @BatchSize(size = 200) //get results partly (200)
    private List<Offer> sellList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Offer> purchasedItems;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private Image userAvatar;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private PasswordResetToken passwordResetToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private EmailToken emailToken;

    @Column(name = "createdTime", updatable = false)
    private Date created;


    @Column(name = "updatedTime")
    private Date updated;


    @AssertTrue
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
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


    public List<Offer> getSellList() {
        return sellList;
    }

    public void setSellList(List<Offer> sellList) {
        this.sellList = sellList;
    }

    public List<Offer> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<Offer> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public EmailToken getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(EmailToken emailToken) {
        this.emailToken = emailToken;
    }

    public Image getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Image userAvatar) {
        this.userAvatar = userAvatar;
    }
}
