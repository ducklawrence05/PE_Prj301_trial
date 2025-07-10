/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class ElectronicDto {
    //-----            your code here   --------------------------------
    private String id;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private boolean status;
    private LocalDate dateTest;
    private LocalTime timeTest;
    private LocalDateTime dateTimeTest;

    public ElectronicDto() {
    }

    public ElectronicDto(String id, String name, String description, float price, int quantity, boolean status, LocalDate dateTest, LocalTime timeTest, LocalDateTime dateTimeTest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.dateTest = dateTest;
        this.timeTest = timeTest;
        this.dateTimeTest = dateTimeTest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDateTest() {
        return dateTest;
    }

    public void setDateTest(LocalDate dateTest) {
        this.dateTest = dateTest;
    }

    public LocalTime getTimeTest() {
        return timeTest;
    }

    public void setTimeTest(LocalTime timeTest) {
        this.timeTest = timeTest;
    }

    public LocalDateTime getDateTimeTest() {
        return dateTimeTest;
    }

    public void setDateTimeTest(LocalDateTime dateTimeTest) {
        this.dateTimeTest = dateTimeTest;
    }

    
}

