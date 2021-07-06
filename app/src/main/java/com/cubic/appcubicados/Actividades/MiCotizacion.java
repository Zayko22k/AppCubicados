package com.cubic.appcubicados.Actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorMisCotizaciones;
import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCotizacion extends AppCompatActivity {
    private RecyclerView rvCotizaciones;
    private List<DetalleCotizacion> detalleCotizacionList = new ArrayList<>();
    private AdaptadorMisCotizaciones adaptadorMisCotizaciones;
    private LinearLayoutManager lym;
    private EditText buscador;
    private TextView textLista;
    Users user = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cotizacion);
        rvCotizaciones = findViewById(R.id.rvCotizaciones);
        user = (Users) getIntent().getSerializableExtra("user");
        buscador = findViewById(R.id.buscador);
        textLista = findViewById(R.id.textLista);


        verMisCotizaciones();
    }

    private void verMisCotizaciones() {
        try{
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("identificadorCl", MODE_PRIVATE);
            String userSave = prefs.getString("userID", null);
            int idUs = Integer.parseInt(userSave);
            Call<List<DetalleCotizacion>> detalleCotiCall = RetrofitBuilder.detalleCotizacionService.getMiCotizacion(idUs);
            detalleCotiCall.enqueue(new Callback<List<DetalleCotizacion>>() {
                @Override
                public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                    if (response.isSuccessful()) {
                        detalleCotizacionList = response.body();

                        if (detalleCotizacionList.size() == 0) {
                            buscador.setVisibility(View.INVISIBLE);
                            textLista.setVisibility(View.INVISIBLE);
                            Toast.makeText(MiCotizacion.this, "No tiene cotizaciones", Toast.LENGTH_SHORT).show();
                        } else {
                            lym = new LinearLayoutManager(MiCotizacion.this);
                            lym.setOrientation(LinearLayoutManager.VERTICAL);
                            adaptadorMisCotizaciones = new AdaptadorMisCotizaciones(detalleCotizacionList, MiCotizacion.this);
                            rvCotizaciones.setLayoutManager(lym);
                            rvCotizaciones.setAdapter(adaptadorMisCotizaciones);
                            rvListener();
                            buscarCotizacion();
                        }

                    } else {
                        Toast.makeText(MiCotizacion.this, "Error en cotizaciones", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(MiCotizacion.this, "No se pudo cargar la cotizacion", Toast.LENGTH_SHORT).show();
        }



    }
    private void buscarCotizacion(){
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String key = buscador.getText().toString();
                Call<List<DetalleCotizacion>> listCall = RetrofitBuilder.detalleCotizacionService.buscarCotizacion(key);
                listCall.enqueue(new Callback<List<DetalleCotizacion>>() {
                    @Override
                    public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                        if(response.isSuccessful()){
                            detalleCotizacionList = response.body();
                            lym = new LinearLayoutManager(MiCotizacion.this);
                            lym.setOrientation(LinearLayoutManager.VERTICAL);
                            adaptadorMisCotizaciones = new AdaptadorMisCotizaciones(detalleCotizacionList, MiCotizacion.this);
                            rvCotizaciones.setLayoutManager(lym);
                            rvCotizaciones.setAdapter(adaptadorMisCotizaciones);
                            rvListener();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {
                        Log.d(null, "Error:" + t.getMessage());
                    }
                });

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MiCotizacion.this, VistaUsuario.class);
        startActivity(i);
    }
    private void rvListener() {
        final GestureDetector mGestureDetector = new GestureDetector(MiCotizacion.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rvCotizaciones.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvCotizaciones.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvCotizaciones.getChildAdapterPosition(child);
                        Toast.makeText(MiCotizacion.this, "Item " + detalleCotizacionList.get(position).getIdDetalleCoti(), Toast.LENGTH_SHORT).show();
                        String data = detalleCotizacionList.get(position).getImagenMaterial();
                        System.out.println(data);
                        Fade fade = new Fade();
                        View decor = getWindow().getDecorView();
                        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
                        fade.excludeTarget(android.R.id.statusBarBackground, true);
                        fade.excludeTarget(android.R.id.navigationBarBackground, true);
                        getWindow().setEnterTransition(fade);
                        getWindow().setExitTransition(fade);
                        if(detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 1){
                            Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion.class);
                            final CardView cardView = findViewById(R.id.cwMiCotizacion);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                            DetalleCotizacion detalleCotizacion = detalleCotizacionList.get(position);
                            intent.putExtra("detalleCoti", detalleCotizacion);
                            startActivity(intent, options.toBundle());
                        } else if(detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 2){
                            Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion_Muro.class);
                            final CardView cardView = findViewById(R.id.cwMiCotizacion);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                            DetalleCotizacion detalleCotizacion = detalleCotizacionList.get(position);
                            intent.putExtra("detalleCoti", detalleCotizacion);
                            startActivity(intent, options.toBundle());
                        }

                        //pager2.setCurrentItem(1);
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
}