package com.cubic.appcubicados.Actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorMisCotizaciones;
import com.cubic.appcubicados.Clases.Util;
import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.Modelos.Users;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.cubic.appcubicados.databinding.ActivityMiCotizacionBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCotizacion extends AppCompatActivity {
    ActivityMiCotizacionBinding bi;

    ActionMode actionMode;
    ActionCallback actionCallback;
    private RecyclerView rvCotizaciones;
    private List<DetalleCotizacion> detalleCotizacionList = new ArrayList<>();
    private AdaptadorMisCotizaciones adaptadorMisCotizaciones;
    private LinearLayoutManager lym;
    Users user = new Users();

    /**
     * @param savedInstanceState
     * @Autor Pablo Rodriguez
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cotizacion);
        rvCotizaciones = findViewById(R.id.rvCotizaciones);
        user = (Users) getIntent().getSerializableExtra("user");
        bi = DataBindingUtil.setContentView(this, R.layout.activity_mi_cotizacion);
        verMisCotizaciones();

    }
    private void init() {

        actionCallback = new ActionCallback();

        adaptadorMisCotizaciones = new AdaptadorMisCotizaciones(detalleCotizacionList,this);
        bi.rvCotizaciones.setLayoutManager(new LinearLayoutManager(this));
        bi.rvCotizaciones.setHasFixedSize(true);
        bi.rvCotizaciones.setAdapter(adaptadorMisCotizaciones);
        adaptadorMisCotizaciones.notifyDataSetChanged();
        adaptadorMisCotizaciones.setItemClick(new AdaptadorMisCotizaciones.OnItemClick() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(View view, DetalleCotizacion detalleCotizacion, int position) {
                if (adaptadorMisCotizaciones.selectedItemCount() > 0) {
                    toggleActionBar(position);
                } else {
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
                    if (detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 1) {
                        Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion.class);
                        final CardView cardView = findViewById(R.id.cwMiCotizacion);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                         detalleCotizacion = detalleCotizacionList.get(position);
                        intent.putExtra("detalleCoti", detalleCotizacion);
                        startActivity(intent, options.toBundle());
                    } else if (detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 2) {
                        Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion_Muro.class);
                        final CardView cardView = findViewById(R.id.cwMiCotizacion);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                        detalleCotizacion = detalleCotizacionList.get(position);
                        intent.putExtra("detalleCoti", detalleCotizacion);
                        startActivity(intent, options.toBundle());
                    }
                }

            }

            @Override
            public void onLongPress(View view, DetalleCotizacion detalleCotizacion, int position) {

                toggleActionBar(position);

            }
        });
        adaptadorMisCotizaciones.notifyDataSetChanged();

    }
    private void toggleActionBar(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionCallback);
        }
        toggleSelection(position);
    }
    private void toggleSelection(int position) {
        adaptadorMisCotizaciones.toggleSelection(position);
        int count = adaptadorMisCotizaciones.selectedItemCount();
        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }
    private void deleteInbox() {
        try{
            List<Integer> selectedItemPositions = adaptadorMisCotizaciones.getSelectedItems();
            verMisCotizaciones();

            for (int i = 0; i <selectedItemPositions.size(); i++) {
                System.out.println("dddd "+detalleCotizacionList.get(i).getIdDetalleCoti());
                adaptadorMisCotizaciones.removeItems(selectedItemPositions.get(i));

            }
            adaptadorMisCotizaciones.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }


        Toast.makeText(MiCotizacion.this, "Cotización borrada",Toast.LENGTH_SHORT).show();


    }

    private class ActionCallback implements ActionMode.Callback {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Util.toggleStatusBarColor(MiCotizacion.this, R.color.blue_grey_700);
            mode.getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delteItem:
                    deleteInbox();
                    verMisCotizaciones();
                    mode.finish();
                    return true;
            }
            return false;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adaptadorMisCotizaciones.clearSelection();
            actionMode = null;
            Util.toggleStatusBarColor(MiCotizacion.this, R.color.colorPrimary);

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Metodo que carga las cotizaciones asociadas
     * al id de usuario que segun pase por
     * parametro
     */
    private void  verMisCotizaciones() {
        try {
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("identificadorCl", MODE_PRIVATE);
            String userSave = prefs.getString("userID", null);
            int idUs = Integer.parseInt(userSave);
            Call<List<DetalleCotizacion>> detalleCotiCall = RetrofitBuilder.detalleCotizacionService.getMiCotizacion(idUs);
            detalleCotiCall.enqueue(new Callback<List<DetalleCotizacion>>() {
                @Override
                public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                    if (response.isSuccessful()) {
                        detalleCotizacionList = new ArrayList<>();
                        detalleCotizacionList = response.body();
                        if (detalleCotizacionList.size() == 0) {
                            Toast.makeText(MiCotizacion.this, "No tiene cotizaciones", Toast.LENGTH_SHORT).show();
                        } else {
                           // rvListener();
                            init();
                        }
                    } else {
                        Toast.makeText(MiCotizacion.this, "Error en cotizaciones", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            Toast.makeText(MiCotizacion.this, "No se pudo cargar la cotizacion", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * cuando se presiona atras vuelve a la actividad de VistaUsuario
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MiCotizacion.this, VistaUsuario.class);
        startActivity(i);
    }
    /**
     * RecyclerView.Listener
     * Trae la posicion donde se marco en la pantalla
     * segun cual sea se redireccionara a distintas cotizacion
     */
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
                        if (detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 1) {
                            Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion.class);
                            final CardView cardView = findViewById(R.id.cwMiCotizacion);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                            DetalleCotizacion detalleCotizacion = detalleCotizacionList.get(position);
                            intent.putExtra("detalleCoti", detalleCotizacion);
                            startActivity(intent, options.toBundle());
                        } else if (detalleCotizacionList.get(position).getTipoConstruccion_idTipoConstruccion() == 2) {
                            Intent intent = new Intent(MiCotizacion.this, Ver_Cotizacion_Muro.class);
                            final CardView cardView = findViewById(R.id.cwMiCotizacion);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MiCotizacion.this, cardView, "Card view");
                            DetalleCotizacion detalleCotizacion = detalleCotizacionList.get(position);
                            intent.putExtra("detalleCoti", detalleCotizacion);
                            startActivity(intent, options.toBundle());
                        }
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