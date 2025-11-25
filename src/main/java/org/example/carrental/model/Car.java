package org.example.carrental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "features")
    private String features;

    @Column(name = "photo")
    private String photo;

    @Column(name = "carPrice")
    private int carPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CarStatus status = CarStatus.AVAILABLE;

    @Column(name = "location")
    private String location;

    public Car() {
    }

    public Car(Long id, User user, String model, int year, String features, int carPrice) {
        this.id = id;
        this.user = user;
        this.model = model;
        this.year = year;
        this.features = features;
        this.carPrice = carPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
