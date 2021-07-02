package com.cubic.appcubicados.Actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Modelos.Region;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Consulta extends AppCompatActivity {

    private Spinner spRegion;
    private List<Region> regionList = new ArrayList<>();
    private List<String> nomRegionList = new ArrayList<>();
    private EditText emailAsis;
    private com.cubic.appcubicados.Modelos.Asistencia asis = new com.cubic.appcubicados.Modelos.Asistencia();
    private EditText consultaAsis;
    private Button btnEnviarAsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        spRegion = findViewById(R.id.spRegion);
        emailAsis = findViewById(R.id.txtEmailAsis);
        consultaAsis = findViewById(R.id.txtConsultaAsis);
        btnEnviarAsis = findViewById(R.id.btnEnviarAsist);
        inicioAsistencia();
        btnEnviarAsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearAsistencia();
            }
        });
    }

    private void crearAsistencia() {
        if (emailAsis.getText().toString().isEmpty() || consultaAsis.getText().toString().isEmpty()) {
            Toast.makeText(this, "No deje campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            asis.setConsulta(consultaAsis.getText().toString());
            asis.setEmail(emailAsis.getText().toString());

            TimeZone myTimeZone = TimeZone.getTimeZone("America/Santiago");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(myTimeZone);
            String dateTime = simpleDateFormat.format(new Date());
            System.out.println("Hora chile: " + dateTime);

            //asis.setFecha_creacion(dd);

            //Call<Consulta> asistenciaCall = RetrofitAsistencia.getApiService().insertAsistencia()
        }
    }

    private void inicioAsistencia() {
        Call<List<Region>> regionCall = RetrofitBuilder.regionService.getRegion();
        regionCall.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                if (response.isSuccessful()) {
                    regionList = response.body();
                    for (int i = 0; i < regionList.size(); i++) {
                        nomRegionList.add(regionList.get(i).getNomRegion());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nomRegionList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRegion.setAdapter(adapter);
                    }


                    spRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                            // TODO Auto-generated method stub
                            Toast.makeText(getApplicationContext(), "Item: " + regionList.get(position).getIdRegion(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Region>> call, Throwable t) {

            }
        });
    }

}