package com.steve.navigationdrawer.lestabsfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.adapter.ProduitAdapter;
import com.steve.navigationdrawer.entities.Produit;
import com.steve.navigationdrawer.produit_manager.ProduitManager;

import java.util.ArrayList;


public class FrigoFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frigo_fragment, container, false);

        ListView lvFrigo = view.findViewById(R.id.lvFrigo);
        ArrayList<Produit> produits = ProduitManager.getAll(getContext());
        ProduitAdapter produitAdapter = new ProduitAdapter(getContext(), R.layout.list_view_produit, produits);

        lvFrigo.setAdapter(produitAdapter);

        return view;
    }




}


