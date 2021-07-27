package com.cubic.appcubicados.Actividades;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.AdaptadorNotify;
import com.cubic.appcubicados.Modelos.RespuestAsistencia;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notificacion extends AppCompatActivity {
    private ImageView imgAtras;
    private RecyclerView rvNoti;
    private AdaptadorNotify adaptadorNotify;
    private LinearLayoutManager lym;
    List<RespuestAsistencia> respuestAsistenciaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        imgAtras = findViewById(R.id.arrowAtras);
        rvNoti = findViewById(R.id.rvNotificacion);
        cargarNoti();
        imgAtras.setOnClickListener( v ->{
            Intent i = new Intent(Notificacion.this, VistaUsuario.class);
            startActivity(i);
        });

    }

    private void cargarNoti(){
        //Datos Usuario
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("userP", MODE_PRIVATE);
        String userSave = prefs.getString("userperfil", null);
        Gson gson = new Gson();
        Users usr = gson.fromJson(userSave, Users.class);
        Call<List<RespuestAsistencia>> respuestaCall = RetrofitBuilder.respuestAsistenciaService.getRespuesta(usr.getId());
        respuestaCall.enqueue(new Callback<List<RespuestAsistencia>>() {
            @Override
            public void onResponse(@NotNull Call<List<RespuestAsistencia>> call, @NotNull Response<List<RespuestAsistencia>> response) {
                if(response.isSuccessful()){
                    respuestAsistenciaList = response.body();
                    lym = new LinearLayoutManager(Notificacion.this);
                    lym.setOrientation(LinearLayoutManager.VERTICAL);
                    adaptadorNotify = new AdaptadorNotify(respuestAsistenciaList, Notificacion.this);
                    rvNoti.setLayoutManager(lym);
                    rvNoti.setAdapter(adaptadorNotify);
                    rvListener();
                    System.out.println("Contador "+respuestAsistenciaList.size());

                }else{
                    Toast.makeText(Notificacion.this, "Error de respuestas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<RespuestAsistencia>> call, @NotNull Throwable t) {
                Log.d("Error: ",t.getMessage());
            }
        });
    }
    private void rvListener() {
        final GestureDetector mGestureDetector = new GestureDetector(Notificacion.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rvNoti.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvNoti.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvNoti.getChildAdapterPosition(child);
                        String data = respuestAsistenciaList.get(position).getConsulta().trim();
                        System.out.println(data);
                        Dialog dialog = new Dialog(Notificacion.this);
                        dialog.setContentView(R.layout.dialog_notify);
                        int witdh = WindowManager.LayoutParams.MATCH_PARENT;
                        int height = WindowManager.LayoutParams.WRAP_CONTENT;
                        dialog.getWindow().setLayout(witdh, height);
                        TextView deUser = dialog.findViewById(R.id.txtDeUsuario);
                        TextView paraUser = dialog.findViewById(R.id.txtParaUsuario);
                        TextView fechaRes = dialog.findViewById(R.id.txtFechaRespuesta);
                        TextView consulta = dialog.findViewById(R.id.txtConsultaSelect);
                        TextView nomRegion = dialog.findViewById(R.id.txtNomRegion);
                        TextView res = dialog.findViewById(R.id.txtResp);
                        deUser.setText(respuestAsistenciaList.get(position).getName());
                        paraUser.setText(respuestAsistenciaList.get(position).getEmail());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        fechaRes.setText(sdf.format(respuestAsistenciaList.get(position).getCreated_at()));
                        consulta.setText(respuestAsistenciaList.get(position).getConsulta());
                        nomRegion.setText(respuestAsistenciaList.get(position).getNomRegion());
                        res.setText(respuestAsistenciaList.get(position).getRespuesta());
                        dialog.show();
                        vistoNoti(respuestAsistenciaList.get(position).getIdRespuestAsistencia());
                        return true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }
    private void vistoNoti(int idNoti){
        System.out.println("Funciono");
     Call<RespuestAsistencia> respuestAsistenciaCall = RetrofitBuilder.respuestAsistenciaService.vistoNoti(idNoti);
     respuestAsistenciaCall.enqueue(new Callback<RespuestAsistencia>() {
         @Override
         public void onResponse(@NotNull Call<RespuestAsistencia> call, @NotNull Response<RespuestAsistencia> response) {
             if(response.isSuccessful()){
                 System.out.println("Funciono");
                cargarNoti();
             }
         }

         @Override
         public void onFailure(@NotNull Call<RespuestAsistencia> call, @NotNull Throwable t) {
                 Log.d("ErrorNoti: ",t.getMessage());
         }
     });
    }
    @Override
    public void onBackPressed() {
      Intent i = new Intent(Notificacion.this,VistaUsuario.class);
      startActivity(i);

    }
}