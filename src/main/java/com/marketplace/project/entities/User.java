package com.marketplace.project.entities;


import com.marketplace.project.entities.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
//@NamedNativeQuery(name = User.FIND_USER_BY_CITY, query = "SELECT user.* FROM user WHERE user.city = ?", resultClass = User.class)
public class User implements UserDetails {
    //public static final String FIND_USER_BY_CITY = "User.findByCity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "FIRST_NAME", length = 30)
    private String firsName;
    @Column(name = "LAST_NAME", length = 30)
    private String secondName;
    @Column(name = "EMAIL", unique = true, nullable = false, length = 60)
    private String email;
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Column(name = "PHONE", length = 30)
    private String phone;
    @Column(name = "CITY", length = 60)
    private String city;

    @Column(name = "`ACTIVE`", length = 30)
    private boolean active;

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Offer> sellList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Offer> purchasedItems;

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

}
