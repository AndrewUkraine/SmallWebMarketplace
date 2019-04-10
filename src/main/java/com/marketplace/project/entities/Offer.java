package com.marketplace.project.entities;

import com.marketplace.project.entities.enums.ConditionType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 120)
    @Column(name = "TITLE", length = 120, nullable = false)
    private String title;

    @NotNull
    @Column(name = "PRICE", nullable = false, scale = 2)
    private BigDecimal price;

    @NotBlank
    @Size(min = 2, max = 1000)
    @Column(name = "`DESCRIPTION`", length = 1000, nullable = false)
    private String offerDescription;


    @Column(name = "`CONDITION`", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionType condition;

    @Column(name = "`STATUS`", nullable = false)
    private Boolean status;

    @Column(name = "`CREATION_TIME`", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh.mm.ss")
    private LocalDateTime creationTimeAndDate;

    @ManyToOne
//  @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "imageOffer", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    //@Fetch(value = FetchMode.SUBSELECT)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public LocalDateTime getCreationTimeAndDate() {
        return creationTimeAndDate;
    }

    public void setCreationTimeAndDate(LocalDateTime creationTimeAndDate) {
        this.creationTimeAndDate = creationTimeAndDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Column(name = "createdTime")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date created;


    @Column(name = "updatedTime")
    private Date updated;

    @PrePersist
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    protected void onCreate() {
        created = new Date();
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }


}
