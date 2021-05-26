package com.cubic.appcubicados.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.R;

public class VistaUsuario extends AppCompatActivity {
    private ImageButton imgCubicador;
    private ImageButton imgCotiz;
    private ImageButton imgAsis;
    private ImageButton imgPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_usuario);
        imgCubicador = findViewById(R.id.imgCubic);
        imgCotiz = findViewById(R.id.imgMiCoti);
        imgAsis = findViewById(R.id.imgAsistencia);
        imgPerfil = findViewById(R.id.imgPerfil);
        //Metodos
        irCubicar();
        irAsistencia();
        irPerfil();
        irCoti();
    }
  private void irCubicar(){
        imgCubicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VistaUsuario.this, Cubicador.class);
                startActivity(i);
            }
        });
  }
  private void irAsistencia(){
        imgAsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VistaUsuario.this, Asistencia.class);
                startActivity(i);
            }
        });
  }
  private void irPerfil(){
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VistaUsuario.this, Perfil.class);
                startActivity(i);
            }
        });
  }
  private void irCoti(){
         imgCotiz.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(VistaUsuario.this, MiCotizacion.class);
                 startActivity(i);
             }
         });
  }
}