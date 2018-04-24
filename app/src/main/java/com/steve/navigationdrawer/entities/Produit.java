package com.steve.navigationdrawer.entities;

public class Produit {

    private String name, category;
    private int quantity, thumbnail;

    public Produit() {
    }

    public Produit(String name, String category, int quantity, int thumbnail) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
