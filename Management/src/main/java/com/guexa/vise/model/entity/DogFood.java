
package com.guexa.vise.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Guexa
 */
@Entity
@Table(name = "dog_Food")
public class DogFood implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String foodName;
    @Column(nullable = false)
    private String breeds;
    @Column(nullable = false)
    private int weightKG;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private double price;

    public DogFood(Long id, String foodName, String breeds, int weightKG, String brand, double price) {
        this.id = id;
        this.foodName = foodName;
        this.breeds = breeds;
        this.weightKG = weightKG;
        this.brand = brand;
        this.price = price;
    }
    
    public DogFood() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getBreeds() {
        return breeds;
    }

    public void setBreeds(String breeds) {
        this.breeds = breeds;
    }

    public int getWeightKG() {
        return weightKG;
    }

    public void setWeightKG(int weightKG) {
        this.weightKG = weightKG;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private static final long serialVersionUID = 1L;
}
