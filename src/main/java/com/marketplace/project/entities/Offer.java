package com.marketplace.project.entities;

import com.marketplace.project.DateTimeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TITLE", length = 500)
    private String title;
    @Column(name = "PRICE", nullable = false, scale = 2)
    private BigDecimal price;
    @Column(name = "`DESCRIPTION`", length = 1000)
    private String offerDescription;
    @Column(name = "`CONDITION`", length = 30)
    private String condition;
    @Column(name = "`STATUS`", length = 30)
    private Boolean status;

    @Column(name = "`CREATION_TIME`", nullable = false)
    //@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    //@Temporal(TemporalType.DATE)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @CreationTimestamp
    private LocalDateTime creationTimeAndDate;

    @ManyToOne
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "imageOffer", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Image> images;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
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
