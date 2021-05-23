package br.com.zup.orangetalents.vehicle;

import br.com.zup.orangetalents.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle")
    private int id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "day_rotation")
    private String dayRotation;

    @Column(name = "active_rotation")
    private Boolean activeRotation = false;

    @ManyToOne
    @JoinColumn (name = "id_user", nullable = false)
    @JsonBackReference
    private UserEntity user;

    public VehicleEntity() {
    }

    public VehicleEntity(int id, String brand, String model, String year, String value, String dayRotation, Boolean activeRotation, UserEntity user) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.value = value;
        this.dayRotation = dayRotation;
        this.activeRotation = activeRotation;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDayRotation() {
        return dayRotation;
    }

    public void setDayRotation(String dayRotation) {
        this.dayRotation = dayRotation;
    }

    public Boolean getActiveRotation() {
        return activeRotation;
    }

    public void setActiveRotation(Boolean activeRotation) {
        this.activeRotation = activeRotation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
