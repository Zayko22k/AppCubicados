package com.cubic.appcubicados.Fragments.Cubicacion;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.AdaptadorTipoCons;
import com.cubic.appcubicados.Clases.InmuebleClass;
import com.cubic.appcubicados.Modelos.TipoConstruccion;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitTipoCons;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.pager2;
import static com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_1.inmuebleBackup;

public class Fragment_cubic_2 extends Fragment {

    RecyclerView rvTipoCons;
    List<TipoConstruccion> tipoConstruccionList = new ArrayList<>();
    AdaptadorTipoCons adaptadorTipoCons;
    LinearLayoutManager lym;

    TextView txtInSelect;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_2, container, false);

            txtInSelect = rootView.findViewById(R.id.txtInSelect);
            String nomInmueble = inmuebleBackup.getNomInmueble();
            rvTipoCons = rootView.findViewById(R.id.rvTipoCons);
            txtInSelect.setText(nomInmueble);

              verTipoCons();
        return rootView;

    }
    private void verTipoCons(){
        Call<List<TipoConstruccion>> callTipoCons = RetrofitTipoCons.getApiService().indexTipoCons();
        callTipoCons.enqueue(new Callback<List<TipoConstruccion>>() {
            @Override
            public void onResponse(Call<List<TipoConstruccion>> call, Response<List<TipoConstruccion>> response) {
                if(response.isSuccessful()){
                    tipoConstruccionList = response.body();
                    lym = new LinearLayoutManager(getContext());
                    lym.setOrientation(LinearLayoutManager.VERTICAL);
                    adaptadorTipoCons = new AdaptadorTipoCons(tipoConstruccionList, getContext());
                    rvTipoCons.setLayoutManager(lym);
                    rvTipoCons.setAdapter(adaptadorTipoCons);
                    rvListener();
                }else{
                    Toast.makeText(getContext(),"Tipo de Construcciones Vacios", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoConstruccion>> call, Throwable t) {
                Toast.makeText(getContext(), "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void rvListener() {
        final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rvTipoCons.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvTipoCons.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvTipoCons.getChildAdapterPosition(child);
                        Toast.makeText(getContext(), "Item id: " + tipoConstruccionList.get(position).getIdTipoConstruccion(), Toast.LENGTH_SHORT).show();
                        inmuebleBackup = new InmuebleClass();
                        inmuebleBackup.setIdInmueble(tipoConstruccionList.get(position).getIdTipoConstruccion());
                        inmuebleBackup.setNomInmueble(tipoConstruccionList.get(position).getNomTipoCons());
                        pager2.setCurrentItem(2);
                           /* listaa.add(listaBot.get(position).getDescripcion());
                            listaa.add(String.valueOf(listaBot.get(position).getValor()));
                            listaa.add(String.valueOf(listaBot.get(position).getStock()));
                            listaa.add(listaBot.get(position).getCategoria());
                            listaa.add(String.valueOf(listaBot.get(position).getTipo_Alchol_idTipoA()));
                            listaa.add(String.valueOf(listaBot.get(position).getId()));
                            listaa.add(listaBot.get(position).getUrlImagen());
                            System.out.println("" + listaBot.get(position).getUrlImagen());
                            inte.putExtra("botel", listaa);
                            startActivity(inte);*/
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