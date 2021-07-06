package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cubic.appcubicados.R;

import java.io.Serializable;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_calculo_rev_w extends Fragment implements Serializable {
    private TextView tituloVentana1;
    private TextView tituloVentana2;
    private EditText largoMuro;
    private EditText anchoMuro;
    private EditText largoVentana1;
    private EditText largoVentana2;
    private EditText anchoVentana1;
    private EditText anchoVentana2;
    private LinearLayout linearVentana1;
    private LinearLayout linearVentana2;
    int cantidadVentanas;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = null;
        if (inflater != null) {
            rootView = (ViewGroup) inflater
                    .inflate(R.layout.fragment_cubic_calculo_rev_w, container, false);
        }
        assert rootView != null;
        tituloVentana1 = rootView.findViewById(R.id.textTituloVentana1);
        tituloVentana2 = rootView.findViewById(R.id.textTituloVentana2);
        linearVentana1 = rootView.findViewById(R.id.lyVentana1);
        linearVentana2 = rootView.findViewById(R.id.lyVentana2);
        Button btnSiguiente = rootView.findViewById(R.id.btnSiguienteCubicRev);
        largoMuro = rootView.findViewById(R.id.txtLargoMuro);
        anchoMuro = rootView.findViewById(R.id.txtAnchoMuro);
        largoVentana1 = rootView.findViewById(R.id.txtLargoVentana1);
        largoVentana2 = rootView.findViewById(R.id.txtLargoVentana2);
        anchoVentana1 = rootView.findViewById(R.id.txtAnchoVentana1);
        anchoVentana2 = rootView.findViewById(R.id.txtAnchoVentana2);
        tituloVentana1.setVisibility(View.INVISIBLE);
        tituloVentana2.setVisibility(View.INVISIBLE);
        linearVentana1.setVisibility(View.INVISIBLE);
        linearVentana2.setVisibility(View.INVISIBLE);
        Spinner spCantidadVentanas = rootView.findViewById(R.id.spVentanas);
        spCantidadVentanas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                switch (position) {
                    case 0:
                        try {
                            tituloVentana1.setVisibility(View.VISIBLE);
                            linearVentana1.setVisibility(View.VISIBLE);
                            tituloVentana2.setVisibility(View.INVISIBLE);
                            linearVentana2.setVisibility(View.INVISIBLE);
                            cantidadVentanas = 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case 1:
                        try {

                            tituloVentana1.setVisibility(View.VISIBLE);
                            linearVentana1.setVisibility(View.VISIBLE);
                            tituloVentana2.setVisibility(View.VISIBLE);
                            linearVentana2.setVisibility(View.VISIBLE);
                            cantidadVentanas = 2;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnSiguiente.setOnClickListener(v -> calcularCubicMuro());
        return rootView;
    }
    private void calcularCubicMuro() {
        if(anchoMuro.getText().toString().isEmpty() || largoMuro.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Uno o mas campos vacios", Toast.LENGTH_SHORT).show();
        } else{
            double ancho = Double.parseDouble(anchoMuro.getText().toString());
            double largo = Double.parseDouble(largoMuro.getText().toString());
            double area = ancho * largo;
            cubicar.setAncho(ancho);
            cubicar.setLargo(largo);
            if(cantidadVentanas == 1){
                if(anchoVentana1.getText().toString().isEmpty() || largoVentana1.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Uno o mas campos vacios", Toast.LENGTH_SHORT).show();
                }else{
                    double ventanaAncho = Double.parseDouble(anchoVentana1.getText().toString());
                    double ventanaLargo = Double.parseDouble(largoVentana1.getText().toString());
                    double areaVentana1 = ventanaAncho * ventanaLargo;
                    double result = area - areaVentana1;
                    cubicar.setMuroPorPintar(result);
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    Fragment_cubic_calculo_rev2 fragment1 = new Fragment_cubic_calculo_rev2();
                    manager.beginTransaction()
                            .replace(R.id.activity_cubicar, fragment1)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }

            }else if(cantidadVentanas == 2){
                if(anchoVentana1.getText().toString().isEmpty() || largoVentana1.getText().toString().isEmpty()||
                        anchoVentana2.getText().toString().isEmpty() || largoVentana2.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Uno o mas campos vacios", Toast.LENGTH_SHORT).show();
                }else{
                    double ventanaAncho = Double.parseDouble(anchoVentana1.getText().toString());
                    double ventanaLargo = Double.parseDouble(largoVentana1.getText().toString());
                    double areaVentana1 = ventanaAncho * ventanaLargo;
                    double ventanaAncho2 = Double.parseDouble(anchoVentana2.getText().toString());
                    double ventanaLargo2 = Double.parseDouble(largoVentana2.getText().toString());
                    double areaVentana2 = ventanaAncho2 * ventanaLargo2;
                    double sum = areaVentana1 + areaVentana2;
                    double resulta = area - sum;
                    cubicar.setMuroPorPintar(resulta);
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    Fragment_cubic_calculo_rev2 fragment1 = new Fragment_cubic_calculo_rev2();
                    manager.beginTransaction()
                            .replace(R.id.activity_cubicar, fragment1)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }

            }
        }



    }
}
