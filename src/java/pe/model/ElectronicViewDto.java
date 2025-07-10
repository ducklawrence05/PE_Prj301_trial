/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class ElectronicViewDto {
    //-----            your code here   --------------------------------
    private String id;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private boolean status;
    private String dateTest;
    private String timeTest;
    private String dateTimeTest;
    private String dateTimeTestDisplay;

    public ElectronicViewDto() {
    }

    public ElectronicViewDto(String id, String name, String description, float price, int quantity, boolean status, String dateTest, String timeTest, String dateTimeTest, String dateTimeTestDisplay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.dateTest = dateTest;
        this.timeTest = timeTest;
        this.dateTimeTest = dateTimeTest;
        this.dateTimeTestDisplay = dateTimeTestDisplay;
    }
    
    // map from original
    public static ElectronicViewDto mapFromDto(ElectronicDto dto) {
        ElectronicViewDto view = new ElectronicViewDto();
        view.id = dto.getId();
        view.name = dto.getName();
        view.description = dto.getDescription();
        view.price = dto.getPrice();
        view.quantity = dto.getQuantity();
        view.status = dto.isStatus();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter dateTimeDisplayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (dto.getDateTest() != null) {
            view.dateTest = dto.getDateTest().format(dateFormatter);
        }

        if (dto.getTimeTest() != null) {
            view.timeTest = dto.getTimeTest().format(timeFormatter);
        }

        if (dto.getDateTimeTest() != null) {
            view.dateTimeTest = dto.getDateTimeTest().format(dateTimeFormatter);
            view.dateTimeTestDisplay = dto.getDateTimeTest().format(dateTimeDisplayFormatter);
        }

        return view;
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

    public String getDateTest() {
        return dateTest;
    }

    public void setDateTest(String dateTest) {
        this.dateTest = dateTest;
    }

    public String getTimeTest() {
        return timeTest;
    }

    public void setTimeTest(String timeTest) {
        this.timeTest = timeTest;
    }

    public String getDateTimeTest() {
        return dateTimeTest;
    }

    public void setDateTimeTest(String dateTimeTest) {
        this.dateTimeTest = dateTimeTest;
    }

    public String getDateTimeTestDisplay() {
        return dateTimeTestDisplay;
    }

    public void setDateTimeTestDisplay(String dateTimeTestDisplay) {
        this.dateTimeTestDisplay = dateTimeTestDisplay;
    }
    
}

