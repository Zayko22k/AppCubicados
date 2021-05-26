package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cubic.appcubicados.R;

import static com.cubic.appcubicados.Actividades.Cubicador.pager2;

public class Fragment_cubic_4 extends Fragment {

    Button btnCubic;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_4, container, false);

        btnCubic = rootView.findViewById(R.id.btnIniciarCubic);
        IniciarCubic();
        return rootView;
    }
    private void IniciarCubic(){
        btnCubic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager2.setCurrentItem(4);
            }
        });
    }
}
