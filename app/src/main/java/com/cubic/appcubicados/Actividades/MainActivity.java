package com.cubic.appcubicados.Actividades;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Modelos.Arriendo;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Arriendo> arriendoList = new ArrayList<>();
    Users us = new Users();
    private EditText txtCorreo;
    private EditText txtPass;
    private TextView txtRegistro;
    Users u = new Users();
    Button btnIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngreso = findViewById(R.id.btnIngreso);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPass = findViewById(R.id.txtPass);
        setupHyperlink();
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }

    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.txtRegistro);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void iniciarSesion() {
        if (txtCorreo.getText().toString().isEmpty() ||
                txtPass.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            u.setEmail(txtCorreo.getText().toString());
            u.setPassword(txtPass.getText().toString());
            Call<Users> usersCall = RetrofitBuilder.usersService.loginResponse(u);
            usersCall.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful()) {
                        // Toast.makeText(MainActivity.this, response.body().getToken(),Toast.LENGTH_SHORT).show();
                        System.out.println(response.body().getToken());

                        getUser(response.body().getToken());

                    } else {
                        Toast.makeText(MainActivity.this, "Usuario no encontrado o no registrado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Log.e("Error: ", t.getMessage());
                }
            });
        }
    }

    private void getUser(String token) {
        Call<Users> getUserCall = RetrofitBuilder.usersService.getSecret("Bearer " + token);
        getUserCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users u = response.body();
                    int id = u.getId();
                    Intent intent = new Intent(MainActivity.this, VistaUsuario.class);
                    intent.putExtra("usuario", u);
                    SharedPreferences.Editor editor = getSharedPreferences("identificadorCl", MODE_PRIVATE).edit();
                    editor.putString("userID", String.valueOf(id));
                    editor.apply();
                    SharedPreferences.Editor editor2 = getSharedPreferences("userP", MODE_PRIVATE).edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(u);
                    editor2.putString("userperfil", json);
                    editor2.apply();
                    editor.apply();
                    servicioVencido(id,intent);

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    private void servicioVencido(int id, Intent intent) {

       Call<List<Arriendo>>  arriendoCall = RetrofitBuilder.arriendoService.getArriendo(id);
       arriendoCall.enqueue(new Callback<List<Arriendo>>() {
           @Override
           public void onResponse(Call<List<Arriendo>> call, Response<List<Arriendo>> response) {
               if(response.isSuccessful()){
                    arriendoList = response.body();
                   for (int i = 0; i < arriendoList.size(); i++ ){
                       if(arriendoList.get(i).getActivo() == 1){
                           System.out.println("Estado: "+arriendoList.get(i).getActivo());
                           startActivity(intent);
                       } else {
                           Dialog dialog = new Dialog(MainActivity.this);
                           dialog.setContentView(R.layout.dialog_cliente_bloq);
                           int witdh = WindowManager.LayoutParams.MATCH_PARENT;
                           int height = WindowManager.LayoutParams.WRAP_CONTENT;
                           dialog.getWindow().setLayout(witdh, height);
                           Button btnRenovar = dialog.findViewById(R.id.btnRenovarPago);
                           Button btnCancelar = dialog.findViewById(R.id.btnCancelar);
                           dialog.show();
                           btnRenovar.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   String url = "http://www.google.com";
                                   Intent i = new Intent(Intent.ACTION_VIEW);
                                   i.setData(Uri.parse(url));
                                   startActivity(i);
                               }
                           });
                           btnCancelar.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   dialog.dismiss();
                               }
                           });

                       }
                   }
               } else{
               }
           }

           @Override
           public void onFailure(Call<List<Arriendo>> call, Throwable t) {
                Log.d("Error", t.getMessage());
           }
       });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("¿Deseas salir de la app?");
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

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

    }

}


