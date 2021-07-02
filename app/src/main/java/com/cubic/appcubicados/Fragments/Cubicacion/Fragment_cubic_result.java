package com.cubic.appcubicados.Fragments.Cubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cubic.appcubicados.Actividades.CotizacionCemento;
import com.cubic.appcubicados.R;

import java.text.DecimalFormat;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_result extends Fragment {
    private TextView m3;
    private TextView sacos;
    private TextView grava;
    private TextView arena;
    private TextView agua;
    private TextView dosif;
    private Button btnCotizar;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_result, container, false);
        m3 = rootView.findViewById(R.id.txtMedidaResult);
        sacos = rootView.findViewById(R.id.txtsacosResult);
        grava = rootView.findViewById(R.id.txtGravaResult);
        arena = rootView.findViewById(R.id.txtArenaResult);
        agua = rootView.findViewById(R.id.txtAguaResult);
        dosif = rootView.findViewById(R.id.txtDosificacion);
        btnCotizar = rootView.findViewById(R.id.btnCotizar);
        resultados();

        return rootView;
    }

    private void resultados() {
        try{
            DecimalFormat formato1 = new DecimalFormat("#.00");
            m3.setText(formato1.format(cubicar.getM3()) + " M³");
            int sacosAprox = (int) Math.round(cubicar.getSacosCemento());
            sacos.setText(sacosAprox + " sacos de cemento necesitas");
            grava.setText(String.valueOf(cubicar.getGravillaOcupar()));
            arena.setText(String.valueOf(cubicar.getArenaOcupar()));
            agua.setText(String.valueOf(cubicar.getAguaOcupar()));
            dosif.setText(cubicar.getDosificacion()+" (Sacos/M³)");
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
        btnCotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CotizacionCemento.class);
                startActivity(i);
            }
        });

    }
}
