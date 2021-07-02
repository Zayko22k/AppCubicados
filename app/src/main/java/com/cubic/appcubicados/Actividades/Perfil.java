package com.cubic.appcubicados.Actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Modelos.Arriendo;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {

    private TextView nombrePerfil;
    private TextView apellidoP;
    private TextView apellidoM;
    private TextView email;
    private TextView fecha;
    private TextView celu;
    private TextView nombre_Servicio;
    private TextView precio_Servicio;
    private TextView diasPago;
    List<Arriendo> arriendoList = new ArrayList<>();

    Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nombrePerfil = findViewById(R.id.txtNomPerfil);
        apellidoP = findViewById(R.id.txtAP_Perfil);
        apellidoM = findViewById(R.id.txtAM_Perfil);
        email = findViewById(R.id.txtEmail_Perfil);
        fecha = findViewById(R.id.txtFecha_Perfil);
        celu = findViewById(R.id.txtCelu_Perfil);
        nombre_Servicio = findViewById(R.id.txtNombreServicio);
        precio_Servicio = findViewById(R.id.txtPrecio_Servicio);
        diasPago = findViewById(R.id.txtDiasPago);

        cargarUsuario();
    }
    public void cerrarSesion(View v){

        IsFinish("¿Deseas cerrar sesión?");

    }
    public void editarPerfil(View v){
        String url = "http://www.google.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void IsFinish(String msjAlert) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

    }

    private void cargarUsuario() {
        //Datos Usuario
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("userP", MODE_PRIVATE);
        String userSave = prefs.getString("userperfil", null);
        Gson gson = new Gson();
        Users usr = gson.fromJson(userSave, Users.class);
        System.out.println("Nombre: " + users.getName());
        nombrePerfil.setText(usr.getName());
        email.setText(usr.getEmail());
        apellidoP.setText(usr.getApellidoP());
        apellidoM.setText(usr.getApellidoM());
        celu.setText(usr.getTelefono());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = usr.getFecha_nacimiento();
        fecha.setText(sdf.format(d));
        System.out.println("" + d);

        Call<List<Arriendo>> arriendoCall = RetrofitBuilder.arriendoService.getArriendo(usr.getId());
        arriendoCall.enqueue(new Callback<List<Arriendo>>() {
            @Override
            public void onResponse(Call<List<Arriendo>> call, Response<List<Arriendo>> response) {
                if (response.isSuccessful()) {
                    arriendoList = response.body();
                    Arriendo a = new Arriendo();
                    for (int i = 0; i < arriendoList.size(); i++) {
                        System.out.println("Nombre del servicio: " + arriendoList.get(i).getNombre());
                        nombre_Servicio.setText(arriendoList.get(i).getNombre());
                        precio_Servicio.setText("$ " + arriendoList.get(i).getPrecio() + " X MES");
                        a.setCreated_at(arriendoList.get(i).getCreated_at());
                    }
                    System.out.println(a.getCreated_at());
                }
            }

            @Override
            public void onFailure(Call<List<Arriendo>> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
            }
        });
    }
}