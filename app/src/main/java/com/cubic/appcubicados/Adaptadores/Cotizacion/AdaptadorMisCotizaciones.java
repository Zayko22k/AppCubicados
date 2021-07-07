package com.cubic.appcubicados.Adaptadores.Cotizacion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdaptadorMisCotizaciones extends RecyclerView.Adapter<AdaptadorMisCotizaciones.ViewHolder> {
    List<DetalleCotizacion> detalleCotizacionList;
    Context context;

    public AdaptadorMisCotizaciones(List<DetalleCotizacion> detalleCotizacionList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.detalleCotizacionList = detalleCotizacionList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AdaptadorMisCotizaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cotizaciones_main, parent, false);
        return new AdaptadorMisCotizaciones.ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdaptadorMisCotizaciones.ViewHolder holder, int position) {
        DetalleCotizacion detalleCotizacion;
        detalleCotizacion = detalleCotizacionList.get(position);
        holder.nombreCotizacion.setText(detalleCotizacion.getNombreCoti());
        String s = new SimpleDateFormat("MM/dd/yyyy").format(detalleCotizacion.getCreated_at());
        holder.fechaCotizacion.setText(s);
        holder.totalCotizacion.setText("$ "+detalleCotizacion.getTotal());

    }
    @Override
    public int getItemCount() {
        return detalleCotizacionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreCotizacion;
        TextView fechaCotizacion;
        TextView totalCotizacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreCotizacion = itemView.findViewById(R.id.verNombreMiCoti);
            fechaCotizacion = itemView.findViewById(R.id.txtVerFechaMiCoti);
            totalCotizacion =  itemView.findViewById(R.id.txtTotalMiCoti);
        }
    }
}
