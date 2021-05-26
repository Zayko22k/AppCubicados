package com.cubic.appcubicados.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.R;

public class MainActivity extends AppCompatActivity {

    Button btnIngreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngreso= findViewById(R.id.btnIngreso);

        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

    }
    private void  iniciarSesion(){
        Intent i = new Intent(MainActivity.this, VistaUsuario.class);
        startActivity(i);
    }
}
//for (int i = 0; listaTipoUser.size() > i; i++)