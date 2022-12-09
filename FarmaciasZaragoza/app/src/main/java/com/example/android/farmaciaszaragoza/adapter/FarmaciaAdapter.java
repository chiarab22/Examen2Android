package com.example.android.farmaciaszaragoza.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.farmaciaszaragoza.R;
import com.example.android.farmaciaszaragoza.base.Farmacia;


import java.util.List;

public class FarmaciaAdapter extends ArrayAdapter<Farmacia> {

    private Context contexto;
    private int layoutId;
    private List<Farmacia> datos;

    public FarmaciaAdapter(Context contexto, int layoutId, List<Farmacia> datos) {
        super(contexto, layoutId, datos);

        this.contexto = contexto;
        this.layoutId = layoutId;
        this.datos = datos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup padre) {

        View fila = view;
        ItemRestaurante item = null;

        if (fila == null) {
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            fila = inflater.inflate(layoutId, padre, false);

            item = new ItemRestaurante();
            item.imagen = (ImageView) fila.findViewById(R.id.imagenRestaurante);
            item.nombre = (TextView) fila.findViewById(R.id.nombreRestaurante);
            item.descripcion = (TextView) fila.findViewById(R.id.descripcionRestaurante);
            item.categoria = (TextView) fila.findViewById(R.id.categoriaRestaurante);

            fila.setTag(item);
        }
        else {
            item = (ItemRestaurante) fila.getTag();
        }

        Farmacia restaurante = datos.get(posicion);
        item.imagen.setImageDrawable(contexto.getResources().getDrawable(R.drawable.ic_launcher));
        item.nombre.setText(restaurante.getNombre());
        item.descripcion.setText(restaurante.getDescripcion());
        item.categoria.setText(restaurante.getCategoria());

        return fila;
    }

    static class ItemRestaurante {

        ImageView imagen;
        TextView nombre;
        TextView descripcion;
        TextView categoria;
    }
}