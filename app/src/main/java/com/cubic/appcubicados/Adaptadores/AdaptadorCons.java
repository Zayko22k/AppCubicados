package com.cubic.appcubicados.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cubic.appcubicados.Modelos.Construcciones;
import com.cubic.appcubicados.R;

import java.util.List;

public class AdaptadorCons extends RecyclerView.Adapter<AdaptadorCons.ViewHolder> {
    List<Construcciones> construccionesList;
    Context context;
    public AdaptadorCons(List<Construcciones> construccionesList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.construccionesList = construccionesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.cubic.appcubicados.Adaptadores.AdaptadorCons.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_construcciones_main, parent, false);
        return new com.cubic.appcubicados.Adaptadores.AdaptadorCons.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.AdaptadorCons.ViewHolder holder, int position){
        Construcciones construcciones = construccionesList.get(position);

        holder.txtCons.setText(construcciones.getNomConstr());
        Glide.with(context.getApplicationContext()).load(R.drawable.right_arrow).into(holder.imgArrow);
    }
    @Override
    public int getItemCount(){return construccionesList.size();}
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCons;
        ImageView imgArrow;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCons = itemView.findViewById(R.id.txtCons);
            imgArrow = itemView.findViewById(R.id.imgArrow3);
        }
    }
}
