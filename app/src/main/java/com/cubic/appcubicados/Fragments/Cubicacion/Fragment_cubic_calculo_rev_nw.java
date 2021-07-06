package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cubic.appcubicados.R;

import java.io.Serializable;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_calculo_rev_nw extends Fragment implements Serializable {

    private TextView anchoMuronw;
    private TextView largoMuronw;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert inflater != null;
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_calculo_rev_nw, container, false);
        anchoMuronw = rootView.findViewById(R.id.txtAnchoMuroNW);
        largoMuronw = rootView.findViewById(R.id.txtLargoMuroNW);
        Button btnSiguiente = rootView.findViewById(R.id.btnSiguienteCubicRevNW);

        btnSiguiente.setOnClickListener(v -> calcularm2());

        return rootView;
    }

    public void calcularm2() {
        if (anchoMuronw.getText().toString().isEmpty() || largoMuronw.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Uno o mas campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            double ancho = Double.parseDouble(anchoMuronw.getText().toString());
            double largo = Double.parseDouble(largoMuronw.getText().toString());
            double area = ancho * largo;
            cubicar.setMuroPorPintar(area);
            cubicar.setAncho(ancho);
            cubicar.setLargo(largo);
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
