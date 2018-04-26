package com.steve.navigationdrawer.entities;

public class Produit {

    private int id;
    private String nom;
    private int categorie;
    private int qte;
    private int img;
    private int codeBar;
    private int disponible;

    public Produit(String nom, int categorie, int qte, int disponible) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.qte = qte;
        this.img = img;
        this.codeBar = codeBar;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(int codeBar) {
        this.codeBar = codeBar;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
