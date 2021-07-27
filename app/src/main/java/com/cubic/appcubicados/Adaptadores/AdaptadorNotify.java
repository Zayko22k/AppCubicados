package com.cubic.appcubicados.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Modelos.RespuestAsistencia;
import com.cubic.appcubicados.R;

import java.util.List;

public class AdaptadorNotify extends RecyclerView.Adapter<AdaptadorNotify.ViewHolder> {
    List<RespuestAsistencia> respuestAsistenciaList;
    Context context;
    public AdaptadorNotify(List<RespuestAsistencia> respuestAsistenciasList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.respuestAsistenciaList = respuestAsistenciasList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.cubic.appcubicados.Adaptadores.AdaptadorNotify.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_respuesta_main, parent, false);
        return new com.cubic.appcubicados.Adaptadores.AdaptadorNotify.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.AdaptadorNotify.ViewHolder holder, int position){
        RespuestAsistencia respuestAsistencia = respuestAsistenciaList.get(position);

        if(respuestAsistencia.getVisto() == 1){
            holder.respuesta.setText(respuestAsistencia.getConsulta());
            System.out.println(respuestAsistencia.getConsulta());
            holder.respuesta.setTypeface(null, Typeface.BOLD);
            holder.resp1.setTypeface(null, Typeface.BOLD);
            holder.resp2.setTypeface(null, Typeface.BOLD);
            holder.resp1.setTextColor(Color.BLACK);
            holder.resp2.setTextColor(Color.BLACK);
            holder.respuesta.setTextColor(Color.BLACK);
        } else if(respuestAsistencia.getVisto() == 2){
            holder.respuesta.setText(respuestAsistencia.getConsulta());
            holder.respuesta.setTypeface(null, Typeface.NORMAL);
            holder.resp1.setTypeface(null, Typeface.NORMAL);
            holder.resp2.setTypeface(null, Typeface.NORMAL);
            holder.resp1.setTextColor(Color.GRAY);
            holder.resp2.setTextColor(Color.GRAY);
            holder.respuesta.setTextColor(Color.GRAY);
        }




    }
    @Override
    public int getItemCount(){return respuestAsistenciaList.size();}
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView respuesta;
        private TextView resp1;
        private TextView resp2;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
             resp1 = itemView.findViewById(R.id.txtresp1);
             resp2 = itemView.findViewById(R.id.txtresp2);
             respuesta = itemView.findViewById(R.id.txtpregunta);
        }
    }
}

