package com.cubic.appcubicados.Fragments.Cubicacion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cubic.appcubicados.Actividades.CotizacionPintura;
import com.cubic.appcubicados.R;

import java.io.Serializable;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_result_rev extends Fragment implements Serializable {
    private TextView m2muro;
    private TextView tipoPintura;
    private TextView cantidadPintura;
    private TextView rendimientoLitro;
    private TextView herramienta;
    private TextView diluyente;
    private TextView nombreDiluyente;
    private Button cotizarPintura;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_result_rev, container, false);

        m2muro = rootView.findViewById(R.id.txtMedidaResult_rev);
        tipoPintura = rootView.findViewById(R.id.txtTipodePintura);
        cantidadPintura = rootView.findViewById(R.id.txtLitrosResult);
        rendimientoLitro = rootView.findViewById(R.id.txtRendimientoporLitro);
        herramienta = rootView.findViewById(R.id.txtHerramResult);
        diluyente = rootView.findViewById(R.id.txtCantidadDeDiluyente);
        nombreDiluyente = rootView.findViewById(R.id.txtNombreDiluyente);
        cotizarPintura = rootView.findViewById(R.id.btnCotizarPintura);
        cargarData();

        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void cargarData() {
        try {
            double muro = cubicar.getMuroPorPintar();
            System.out.println(cubicar.getMuroPorPintar());
            cubicar.setMuroPorPintar(muro);
            System.out.println("Tipo de pintura = "+cubicar.getTipoPintura());
            m2muro.setText(muro + " M²");
            if (cubicar.getTipoPintura() == 0) {
                tipoPintura.setText("Esmalte al agua");
                nombreDiluyente.setText("Agua");

            } else if (cubicar.getTipoPintura() == 1) {
                tipoPintura.setText("Látex");
                nombreDiluyente.setText("Agua");
            } else if (cubicar.getTipoPintura() == 2) {
                tipoPintura.setText("Esmalte sintetico");
                nombreDiluyente.setText("Aguarrás o diluyente sintático");
            } else if (cubicar.getTipoPintura() == 3) {
                tipoPintura.setText("Óleo");
                nombreDiluyente.setText("Aguarrás o diluyente sintático");

            }
            System.out.println("Litros sin aproximar"+cubicar.getLitrosPintura());
            cantidadPintura.setText((double)Math.round(cubicar.getLitrosPintura() * 100d) / 100d+ " litros  aprox necesitas");
            rendimientoLitro.setText(cubicar.getRendimientoPintura() + " mts2 / litro");
            if (cubicar.getHerramienta() == 0) {
                herramienta.setText("Brocha o rodillo");
                System.out.println((double)Math.round(cubicar.getCantidadDiluyente() * 100d) / 100d);
 /* Salida : 1.42*/
                diluyente.setText((double)Math.round(cubicar.getCantidadDiluyente() * 100d) / 100d+ " equivale al 5% de diluyente por la cantidad de pintura");
            } else if (cubicar.getHerramienta() == 1) {
                herramienta.setText("Pistola");
                diluyente.setText(cubicar.getCantidadDiluyente() + " equivale al 10% de diluyente por la cantidad de pintura");

            }
            cotizarPintura.setOnClickListener(v -> {
                Intent i = new Intent(getContext(), CotizacionPintura.class);
                startActivity(i);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
