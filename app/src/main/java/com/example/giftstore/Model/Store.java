package com.example.giftstore.Model;

public class Store {
    private int id;
    private String categoryName;
    private int group;
    private int unit;
    private double unitPrice;
    private double totalPrice;
    private double totalAll;
    private String timesTamp;

    public Store(int id) {
        this.id = id;
    }

    public Store(int id, String categoryName, int group, int unit, double unitPrice, double totalPrice, double totalAll, String timesTamp) {
        this.id = id;
        this.categoryName = categoryName;
        this.group = group;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.totalAll = totalAll;
        this.timesTamp = timesTamp;
    }

    public Store(String categoryName, int group, int unit, double unitPrice, double totalPrice, double totalAll, String timesTamp) {
        this.categoryName = categoryName;
        this.group = group;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.totalAll = totalAll;
        this.timesTamp = timesTamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(double totalAll) {
        this.totalAll = totalAll;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }
}
