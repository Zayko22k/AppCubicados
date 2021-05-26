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
import com.cubic.appcubicados.Modelos.TipoConstruccion;
import com.cubic.appcubicados.R;

import java.util.List;

public class AdaptadorTipoCons extends RecyclerView.Adapter<AdaptadorTipoCons.ViewHolder> {
        List<TipoConstruccion> tipoConstruccionList;
        Context context;
public AdaptadorTipoCons(List<TipoConstruccion> tipoConstruccionList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.tipoConstruccionList = tipoConstruccionList;
        notifyDataSetChanged();
        }

@NonNull
@Override
public com.cubic.appcubicados.Adaptadores.AdaptadorTipoCons.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_tipo_construcciones_main, parent, false);
        return new com.cubic.appcubicados.Adaptadores.AdaptadorTipoCons.ViewHolder(view);
        }
@Override
public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.AdaptadorTipoCons.ViewHolder holder, int position){
        TipoConstruccion tipoConstruccion = tipoConstruccionList.get(position);

        holder.txtTipoCons.setText(tipoConstruccion.getNomTipoCons());
        Glide.with(context.getApplicationContext()).load(R.drawable.right_arrow).into(holder.imgArrow);

        }
@Override
public int getItemCount(){return tipoConstruccionList.size();}
public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTipoCons;
    ImageView imgArrow;
    public ViewHolder(@NonNull View itemView){
        super(itemView);
        txtTipoCons = itemView.findViewById(R.id.txtTipoCons);
        imgArrow = itemView.findViewById(R.id.imgArrow2);
    }
}
}
