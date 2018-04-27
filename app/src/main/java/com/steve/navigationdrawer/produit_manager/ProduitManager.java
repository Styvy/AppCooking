package com.steve.navigationdrawer.produit_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.steve.navigationdrawer.entities.Produit;
import com.steve.navigationdrawer.services.ConnectionBd;

import java.util.ArrayList;

public class ProduitManager {


    public static ArrayList<Produit> getAll(Context ctx) {
        ArrayList<Produit> retour = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
        String getAll = " select * from produits ";
        Cursor c = bd.rawQuery(getAll, null);
        while (c.moveToNext()) {
            retour.add(new Produit(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6)));
        }
        bd.close();
        return retour;
    }

    public static ArrayList<Produit> getAllFrigo(Context ctx) {
        ArrayList<Produit> retour = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
        String getAllFrigo = " select * from produits where categorie " + " = " + 0;
        Cursor c = bd.rawQuery(getAllFrigo, null);
        while (c.moveToNext()) {
            retour.add(new Produit(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6)));
        }
        return retour;
    }

//    private static String getAllFrigo = " select * from produits where categorie = 0 ";
//    public static ArrayList<Produit> getAllFrigo(Context ctx) {
//        ArrayList<Produit> retour = new ArrayList<>();
//        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
//        Cursor c = bd.rawQuery(getAllFrigo, null);
//        while (c.moveToNext()) {
//            retour.add(new Produit(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6)));
//        }
//        return retour;
//    }
//
//    private static String getAllFrigo = " select * from produits where categorie = 0 ";
//    public static ArrayList<Produit> getAllFrigo(Context ctx) {
//        ArrayList<Produit> retour = new ArrayList<>();
//        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
//        Cursor c = bd.rawQuery(getAllFrigo, null);
//        while (c.moveToNext()) {
//            retour.add(new Produit(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6)));
//        }
//        return retour;
//    }

    public static boolean add(Context ctx, Produit produitToAdd) {
        ContentValues cv = new ContentValues();
        cv.put("id", produitToAdd.getId());
        cv.put("nom", produitToAdd.getNom());
        cv.put("categorie", produitToAdd.getCategorie());
        cv.put("qte", produitToAdd.getQte());
        cv.put("img", produitToAdd.getImg());
        cv.put("code_bar", produitToAdd.getCodeBar());
        cv.put("disponible", produitToAdd.getDisponible());
        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
        long retour = bd.insert("produits", null, cv);

        bd.close();
        return retour > 1;
    }

    public static void delete(Context ctx, int id) {
        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
        bd.delete("produits", "id = ?", new String[]{String.valueOf(id)});
        bd.close();
    }

    public static void update(Context ctx, Produit etuToMod) {
        ContentValues value = new ContentValues();
        value.put("id", etuToMod.getId());
        value.put("nom", etuToMod.getNom());
        value.put("categorie", etuToMod.getCategorie());
        value.put("qte", etuToMod.getQte());
        value.put("img", etuToMod.getImg());
        value.put("code_bar", etuToMod.getCodeBar());
        value.put("disponible", etuToMod.getDisponible());

        SQLiteDatabase bd = ConnectionBd.getBd(ctx);
        bd.update("produits", value, "id = ?", new String[]{String.valueOf(etuToMod.getId())});
        bd.close();
    }


}