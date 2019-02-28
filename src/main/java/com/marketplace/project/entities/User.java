package com.marketplace.project.entities;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedNativeQuery(name = User.FIND_USER_BY_CITY, query = "SELECT user.* FROM user WHERE user.city = ?", resultClass = User.class)
public class User {
    public static final String FIND_USER_BY_CITY = "User.findByCity";

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

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<UserRole> userRoles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Offer> sellList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
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


}
