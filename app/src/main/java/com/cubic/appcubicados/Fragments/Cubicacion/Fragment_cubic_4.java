package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cubic.appcubicados.R;

public class Fragment_cubic_4 extends Fragment {

    TextView txtLargo;
    TextView txtm3;
    TextView txtAncho;
    TextView txtAlto;
    Button btnCubic;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_4, container, false);

        txtAlto = rootView.findViewById(R.id.txtAlto);
        txtAncho = rootView.findViewById(R.id.txtAncho);
        txtLargo = rootView.findViewById(R.id.txtLargo);
        txtm3 = rootView.findViewById(R.id.txtm3);
        btnCubic = rootView.findViewById(R.id.btnIniciarCubic);
        CalcularM3();
        IniciarCubic();
        return rootView;
    }
    private void IniciarCubic(){
        btnCubic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalcularCubic();
            }
        });
    }
    private void CalcularCubic(){

        if(txtLargo.getText().toString().isEmpty() ||
        txtAncho.getText().toString().isEmpty() ||
        txtAlto.getText().toString().isEmpty()){
            Toast.makeText(null,"Ingresa los campos vacios", Toast.LENGTH_LONG).show();
        } else{

           int largo = Integer.parseInt(txtLargo.getText().toString());
           int ancho = Integer.parseInt(txtAncho.getText().toString());
           int alto = Integer.parseInt(txtAlto.getText().toString());

            //Calculo para los sacos de cemento
           int area = largo * ancho;
           int altoR = alto /100;
           int m3 = area * altoR;
           int cal = 340 * m3;
           int sacos = cal/25;
           //Gravilla
            int cal2 = 1095 * m3;
            double gravilla = cal2 * 0.72;
            //Arena
            int cal3 = 715 * m3;
            double agua = cal3 * 0.5;

        }
    }
    private void CalcularM3(){
        txtAlto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(txtLargo.getText().toString().isEmpty() ||
                        txtAncho.getText().toString().isEmpty() ||
                txtAlto.getText().toString().isEmpty()){
                    txtm3.setText("0,0");
                } else{
                    double param1 = Double.parseDouble(txtLargo.getText().toString());
                    double param2 = Double.parseDouble(txtAncho.getText().toString());
                    double param3 = Double.parseDouble(txtAlto.getText().toString());
                    double area  = param1 * param2;
                    double m3 = area/param3;
                    txtm3.setText(""+m3);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtLargo.getText().toString().isEmpty() ||
                        txtAncho.getText().toString().isEmpty() ||
                txtAlto.getText().toString().isEmpty()){
                    txtm3.setText("0,0");
                }
            }
        });
    }
}
