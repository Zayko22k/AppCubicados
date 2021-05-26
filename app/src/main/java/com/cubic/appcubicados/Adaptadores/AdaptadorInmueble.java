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
import com.cubic.appcubicados.Modelos.Inmueble;
import com.cubic.appcubicados.R;

import java.util.List;

public class AdaptadorInmueble extends RecyclerView.Adapter<AdaptadorInmueble.ViewHolder> {
List<Inmueble> inmuebleList;
Context context;
public AdaptadorInmueble(List<Inmueble> inmuebleList, @NonNull Context context) {
    this.context = context.getApplicationContext();
    this.inmuebleList = inmuebleList;
    notifyDataSetChanged();
}

@NonNull
@Override
    public com.cubic.appcubicados.Adaptadores.AdaptadorInmueble.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_inmueble_main, parent, false);
            return new com.cubic.appcubicados.Adaptadores.AdaptadorInmueble.ViewHolder(view);
}
@Override
    public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.AdaptadorInmueble.ViewHolder holder, int position){
        Inmueble inmueble = inmuebleList.get(position);

        holder.txtInmueble.setText(inmueble.getNomInmueble());
        Glide.with(context.getApplicationContext()).load(R.drawable.right_arrow).into(holder.imgArrow);

}
@Override
    public int getItemCount(){return inmuebleList.size();}
    public class ViewHolder extends RecyclerView.ViewHolder {
           TextView txtInmueble;
           ImageView imgArrow;
    public ViewHolder(@NonNull View itemView){
        super(itemView);
        txtInmueble = itemView.findViewById(R.id.txtInmueble);
        imgArrow = itemView.findViewById(R.id.imgArrow);
    }
    }
}
