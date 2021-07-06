package com.cubic.appcubicados.Actividades;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VistaUsuario extends AppCompatActivity {
    private ImageButton imgCubicador;
    private ImageButton imgCotiz;
    private ImageButton imgAsis;
    private ImageButton imgPerfil;
    Users users = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_usuario);
        imgCubicador = findViewById(R.id.imgCubic);
        imgCotiz = findViewById(R.id.imgMiCoti);
        imgAsis = findViewById(R.id.imgAsistencia);
        imgPerfil = findViewById(R.id.imgPerfil);
        users = (Users) getIntent().getSerializableExtra("usuario");
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("userP", MODE_PRIVATE);
        String userSave = prefs.getString("userperfil", null);
        Gson gson = new Gson();
        Users usr = gson.fromJson(userSave, Users.class);
        cargarDiasRestantes(usr.getId());

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
                Intent i = new Intent(VistaUsuario.this, Consulta.class);
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
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("¿Deseas cerrar sesión?");
    }

    public void IsFinish(String msjAlert) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                       Intent i = new Intent(VistaUsuario.this, MainActivity.class);
                       startActivity(i);
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(VistaUsuario.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

    }
    private void cargarDiasRestantes(int usID){
        Call<Integer> integerCall = RetrofitBuilder.arriendoService.getDias(usID);
        integerCall.enqueue(new Callback<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    int diasRest =  response.body();
                    if(diasRest == 31){
                        Intent i = new Intent(VistaUsuario.this, MainActivity.class);
                        startActivity(i);
                    }
                    System.out.println("Dias restantes"+response.body());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}