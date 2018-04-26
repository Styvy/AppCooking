package com.steve.navigationdrawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.entities.Produit;

import java.util.List;

public class ProduitAdapter extends ArrayAdapter<Produit> {
    int ressource;


    public ProduitAdapter(Context context, int resource, List<Produit> objects) {
        super(context, resource, objects);
        ressource = resource;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produit e = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(ressource, null);
        }


        ImageView img = convertView.findViewById(R.id.imgViewProduit);
        TextView nom = convertView.findViewById(R.id.nomProduit);
        TextView qteProduit = convertView.findViewById(R.id.qteProduit);

        img.setImageResource(e.getImg());
        nom.setText(e.getNom());
        qteProduit.setText(String.valueOf(e.getQte()));


        return convertView;
    }
}

//    private int id;
//    private String nom;
//    private String categorie;
//    private int qte;
//    private int img;
//    private int code_bar;
//    private String disponible;

