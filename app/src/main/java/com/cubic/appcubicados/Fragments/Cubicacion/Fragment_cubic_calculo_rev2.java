package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cubic.appcubicados.R;

import java.io.Serializable;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_calculo_rev2 extends Fragment implements Serializable {

    private Spinner spPintura;
    private Spinner spHerramienta;
    private TextView txtDiluyente;
    private Button btnCubicar;
    int idPintura;
    int idHerramienta;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_calculo_rev2, container, false);
        spPintura = rootView.findViewById(R.id.spTipoDePintura);
        spHerramienta = rootView.findViewById(R.id.spHerramienta);
        txtDiluyente = rootView.findViewById(R.id.txtDiluyente);
        btnCubicar = rootView.findViewById(R.id.btnCubicarRev);
        spPintura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                try {
                    switch (position) {
                        case 0:
                            txtDiluyente.setText("Agua");
                            idPintura = 0;
                            break;
                        case 1:
                            txtDiluyente.setText("Agua");
                            idPintura = 1;
                            break;
                        case 2:
                            txtDiluyente.setText("Aguarras o diluyente sintetico");
                            idPintura = 2;
                            break;
                        case 3:
                            txtDiluyente.setText("Aguarras o diluyente sintetico");
                            idPintura = 3;
                            break;
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spHerramienta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                try {
                    switch (position) {
                        case 0:
                            idHerramienta = 0;
                            break;
                        case 1:
                            idHerramienta = 1;
                            break;
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnCubicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularPintura();
            }
        });

        return rootView;
    }

    private void calcularPintura() {
        try{
            double porPintar = cubicar.getMuroPorPintar();
            System.out.println(cubicar.getMuroPorPintar() + "Fragment_cubic_calculo_rev2");
            cubicar.setMuroPorPintar(porPintar);
            if (idPintura == 0) {
                double result = porPintar / 12;
                cubicar.setRendimientoPintura(12);
                cubicar.setLitrosPintura(result);
                cubicar.setTipoPintura(idPintura);
            } else if (idPintura == 1) {
                cubicar.setRendimientoPintura(10);
                double result = porPintar / 10;
                cubicar.setLitrosPintura(result);
                cubicar.setTipoPintura(idPintura);
            } else if (idPintura == 2) {
                cubicar.setRendimientoPintura(13);
                double result = porPintar / 13;
                cubicar.setLitrosPintura(result);
                cubicar.setTipoPintura(idPintura);
            } else if (idPintura == 3) {
                cubicar.setRendimientoPintura(12);
                double result = porPintar / 12;
                cubicar.setLitrosPintura(result);
                cubicar.setTipoPintura(idPintura);
            }
            if (idHerramienta == 0) {
                double mult = cubicar.getLitrosPintura() * 5;
                double cantidadDiluy = mult / 100;
                cubicar.setHerramienta(idHerramienta);
                cubicar.setCantidadDiluyente(cantidadDiluy);

            } else if (idHerramienta == 1) {
                cubicar.setHerramienta(idHerramienta);
                double mult = cubicar.getLitrosPintura() * 10;
                double cantidadDiluy = mult / 100;
                cubicar.setCantidadDiluyente(cantidadDiluy);
            }
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            Fragment_cubic_result_rev fragment1 = new Fragment_cubic_result_rev();
            manager.beginTransaction()
                    .replace(R.id.activity_cubicar, fragment1)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
