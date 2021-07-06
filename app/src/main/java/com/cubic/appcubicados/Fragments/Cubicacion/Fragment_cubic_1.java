package com.cubic.appcubicados.Fragments.Cubicacion;


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.AdaptadorInmueble;
import com.cubic.appcubicados.Clases.cubic;
import com.cubic.appcubicados.Modelos.Inmueble;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;


public class Fragment_cubic_1 extends Fragment implements Serializable {

    private RecyclerView rvInmueble;
    private List<Inmueble> listaInmueble = new ArrayList<>();
    private AdaptadorInmueble adaptadorInmueble;
    private LinearLayoutManager lym;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_1, container, false);

        rvInmueble = rootView.findViewById(R.id.rvInmueble);
        verInmueble();
        return rootView;
    }

    //Metodos
    private void verInmueble() {
        Call<List<Inmueble>> callInmueble = RetrofitBuilder.inmuebleService.indexInmueble();


        callInmueble.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaInmueble = response.body();
                    lym = new LinearLayoutManager(getContext());
                    lym.setOrientation(LinearLayoutManager.VERTICAL);
                    adaptadorInmueble = new AdaptadorInmueble(listaInmueble, getContext());
                    rvInmueble.setLayoutManager(lym);
                    rvInmueble.setAdapter(adaptadorInmueble);
                    rvListener();
                } else {
                    Toast.makeText(getContext(), "Inmuebles Vacios", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
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
        rvInmueble.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvInmueble.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvInmueble.getChildAdapterPosition(child);
                        String data = listaInmueble.get(position).getNomInmueble().trim();
                        System.out.println(data);
                        cubicar = new cubic();
                        if(listaInmueble.get(position).getIdInmueble()==1){
                            cubicar.setIdInmueble(listaInmueble.get(position).getIdInmueble());
                            cubicar.setInmuebleSelect(data);
                            FragmentManager manager = getActivity().getSupportFragmentManager();
                            Fragment_cubic_2 fragment1 = new Fragment_cubic_2();
                            manager.beginTransaction()
                                    .replace(R.id.activity_cubicar, fragment1)
                                    .addToBackStack(null)
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .commit();
                        }else if(listaInmueble.get(position).getIdInmueble() == 2){
                            Toast.makeText(getContext(), "Aun estamos en Beta no te muevas", Toast.LENGTH_SHORT).show();
                        }else if(listaInmueble.get(position).getIdInmueble() == 3){
                            Toast.makeText(getContext(), "Aun estamos en Beta no te muevas", Toast.LENGTH_SHORT).show();
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