package com.cubic.appcubicados.Actividades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Modelos.Asistencia;
import com.cubic.appcubicados.Modelos.Region;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Consulta extends AppCompatActivity {

    private Spinner spRegion;
    private List<Region> regionList = new ArrayList<>();
    private List<String> nomRegionList = new ArrayList<>();
    private EditText emailAsis;
    private EditText consultaAsis;
    private Button btnEnviarAsis;
    Asistencia asistencia = new Asistencia();

    /**
     * Activity para las consultas(Asistencia de la app)
     *
     * @param savedInstanceState
     * @Autor PabloRodriguez
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        spRegion = findViewById(R.id.spRegion);
        emailAsis = findViewById(R.id.txtEmailAsis);
        consultaAsis = findViewById(R.id.txtConsultaAsis);
        btnEnviarAsis = findViewById(R.id.btnEnviarAsist);
        inicioAsistencia();
        /**
         *  Boton listener
         *  llama al metodo crear Asistencia
         */
        btnEnviarAsis.setOnClickListener(v -> crearAsistencia());
    }

    private void crearAsistencia() {
        if (emailAsis.getText().toString().isEmpty() || consultaAsis.getText().toString().isEmpty()) {
            Toast.makeText(this, "No deje campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("userP", MODE_PRIVATE);
            String userSave = prefs.getString("userperfil", null);
            Gson gson = new Gson();
            Users usr = gson.fromJson(userSave, Users.class);
            asistencia.setConsulta(consultaAsis.getText().toString());
            asistencia.setEmail(emailAsis.getText().toString());
            BigInteger bigInteger = new BigInteger(String.valueOf(usr.getId()));
            asistencia.setUsers_id(bigInteger);
            Call<Asistencia> asistenciaCall = RetrofitBuilder.asistenciaService.insertAsistencia(asistencia);
            asistenciaCall.enqueue(new Callback<Asistencia>() {
                @Override
                public void onResponse(Call<Asistencia> call, Response<Asistencia> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(Consulta.this, "Se envio tu consulta exitosamente",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Consulta.this, VistaUsuario.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<Asistencia> call, Throwable t) {

                }
            });
        }
    }

    /**
     * Lista de regiones en spinner
     */
    private void inicioAsistencia() {
        Call<List<Region>> regionCall = RetrofitBuilder.regionService.getRegion();
        regionCall.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                if (response.isSuccessful()) {
                    regionList = response.body();
                    for (int i = 0; i < regionList.size(); i++) {
                        nomRegionList.add(regionList.get(i).getNomRegion());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, nomRegionList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRegion.setAdapter(adapter);
                    }


                    spRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                            // TODO Auto-generated method stub
                         asistencia.setRegion_idRegion(regionList.get(position).getIdRegion());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Region>> call, @NotNull Throwable t) {

            }
        });
    }
}