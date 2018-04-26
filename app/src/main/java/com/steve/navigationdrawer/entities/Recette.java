package com.steve.navigationdrawer.entities;

public class Recette {

    private int id;
    private String titre;
    private String ingredients;
    private String preparation;
    private int img;

    public Recette(int id, String titre, String ingredients, String preparation, int img) {
        this.id = id;
        this.titre = titre;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
