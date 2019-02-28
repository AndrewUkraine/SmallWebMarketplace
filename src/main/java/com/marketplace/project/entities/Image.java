package com.marketplace.project.entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "PATH", length = 600)
    private String path;
    @Column(name = "NAME")
    private String name;


    @Lob
    private byte[] data;

    @ManyToOne (fetch = FetchType.EAGER)
    private Offer imageOffer;

    public Image() {
    }

    public Image(String name, byte[] data, Offer imageOffer) {
        this.name = name;
        this.data = data;
        this.imageOffer = imageOffer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Offer getImageOffer() {
        return imageOffer;
    }

    public void setImageOffer(Offer imageOffer) {
        this.imageOffer = imageOffer;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
