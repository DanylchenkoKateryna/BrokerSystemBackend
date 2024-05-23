package com.broker.brokersystem.data.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rooms;
    private double area;
    private int floor;
    private String district;
    private String imgUrl;
    public Apartment(int id,int rooms, double area, int floor, String district,String imgUrl) {
        this.id=id;
        this.rooms = rooms;
        this.area = area;
        this.floor = floor;
        this.district = district;
        this.imgUrl=imgUrl;
    }
    public Apartment(int rooms, double area, int floor, String district,String imgUrl) {
        this.rooms = rooms;
        this.area = area;
        this.floor = floor;
        this.district = district;
        this.imgUrl=imgUrl;
    }

    public Apartment() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Rooms: " + rooms +
                ", Area: " + area +
                ", Floor: " + floor +
                ", District: " + district+
                ", ImgUrl: " + imgUrl;
    }
}
