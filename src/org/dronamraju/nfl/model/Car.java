package org.dronamraju.nfl.model;


/**
 * Created by mdronamr on 9/21/16.
 */
public class Car {
    String id;
    String brand;
    Integer year;
    String color;
    Integer price;
    Boolean state;

    public Car() {
    }

    public Car(String id, String brand, Integer year, String color, Integer price, Boolean state) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
