package com.cubic.appcubicados.Fragments.Cubicacion;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cubic.appcubicados.R;

import java.text.DecimalFormat;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_calculo_sup extends Fragment {

   private TextView txtLargo;
   private TextView txtm3;
   private TextView txtAncho;
   private TextView txtAlto;
   private Button btnCubic;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_calculo_sup, container, false);

        txtAlto = rootView.findViewById(R.id.txtAlto);
        txtAncho = rootView.findViewById(R.id.txtAncho);
        txtLargo = rootView.findViewById(R.id.txtLargo);
        txtm3 = rootView.findViewById(R.id.txtm3);
        btnCubic = rootView.findViewById(R.id.btnIniciarCubic);
        CalcularM3();
        btnCubic.setOnClickListener(v -> calcularCubic());
        return rootView;
    }
    public void calcularCubic() {

        if (txtLargo.getText().toString().isEmpty() ||
                txtAncho.getText().toString().isEmpty() ||
                txtAlto.getText().toString().isEmpty() ||
        txtm3.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Ingresa los campos vacios", Toast.LENGTH_LONG).show();
        } else {
            double largo = Double.parseDouble(txtLargo.getText().toString());
            cubicar.setLargo(largo);
            double ancho = Double.parseDouble(txtAncho.getText().toString());
            cubicar.setAncho(ancho);
            int alto = Integer.parseInt(txtAlto.getText().toString());
            cubicar.setAlto(alto);
            double m3 = Double.parseDouble(txtm3.getText().toString());
            System.out.println(alto);
            double area = largo * ancho;
            cubicar.setArea(area);
            System.out.println(area);
            double divAlto = (double) alto/100;
            System.out.println(divAlto);

            if(cubicar.getIdConstrucciones() == 1){
                double cal = (double) 180 * m3;
                double sacos = (double) cal/25;
                cubicar.setGravillaOcupar(9.0);
                cubicar.setArenaOcupar(10.0);
                cubicar.setAguaOcupar(1.5);
                cubicar.setDosificacion(7);
                cubicar.setSacosCemento(sacos);

                System.out.println(1);
            } else if(cubicar.getIdConstrucciones() == 2){
                double cal = (double) 270 * m3;
                double sacos = (double) cal/25;
                cubicar.setGravillaOcupar(6.0);
                cubicar.setArenaOcupar(7.0);
                cubicar.setAguaOcupar(1.0);
                cubicar.setDosificacion(10);
                cubicar.setSacosCemento(sacos);
                System.out.println(2);
            } else if(cubicar.getIdConstrucciones() == 3){
                double cal = (double) 370 * m3;
                double sacos = (double) cal/25;
                cubicar.setGravillaOcupar(4.0);
                cubicar.setArenaOcupar(4.0);
                cubicar.setAguaOcupar(1.0);
                cubicar.setDosificacion(15);
                cubicar.setSacosCemento(sacos);
                System.out.println(3);
            }
            cubicar.setM3(m3);
            if(m3 == 0){
                Toast.makeText(getContext(), "No se puede dejar los m3 en 0", Toast.LENGTH_SHORT).show();
            } else{
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                Fragment_cubic_result fragment1 = new Fragment_cubic_result();
                manager.beginTransaction()
                        .replace(R.id.activity_cubicar, fragment1)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

        }
    }
    private void CalcularM3(){
        //Evento alto text
        txtAlto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(txtLargo.getText().toString().isEmpty() ||
                        txtAncho.getText().toString().isEmpty() ||
                txtAlto.getText().toString().isEmpty()){
                    txtm3.setText(""+0.0);
                } else{
                    double param1 = Double.parseDouble(txtLargo.getText().toString());
                    double param2 = Double.parseDouble(txtAncho.getText().toString());
                    double param3 = Double.parseDouble(txtAlto.getText().toString());
                    double area  = param1 * param2;
                    double m3 = area/param3;
                    DecimalFormat formato2 = new DecimalFormat("#.##");
                    System.out.println(formato2.format(m3));
                  String m3Ajust =  formato2.format(m3);
                    txtm3.setText(m3Ajust);
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
