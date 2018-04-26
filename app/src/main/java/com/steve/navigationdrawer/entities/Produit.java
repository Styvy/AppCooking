package com.steve.navigationdrawer.entities;

public class Produit {

    private int id;
    private String nom;
    private String categorie;
    private int qte;
    private int img;
    private int code_bar;
    private String disponible;

    public Produit(int id, String nom, String categorie, int qte, int img, int code_bar, String disponible) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.qte = qte;
        this.img = img;
        this.code_bar = code_bar;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
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
        return code_bar;
    }

    public void setCodeBar(int codeBar) {
        this.code_bar = code_bar;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
}
