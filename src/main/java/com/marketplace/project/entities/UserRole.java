package com.marketplace.project.entities;

import com.marketplace.project.entities.enums.RoleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "user_role")
//public class UserRole {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(name = "ROLE_TYPE", length = 20, nullable = false, unique = true)
//    private String roleType = RoleType.BUYER.getRoleType();
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(String roleType) {
//        this.roleType = roleType;
//    }
//}
