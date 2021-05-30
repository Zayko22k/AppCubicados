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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.AdaptadorInmueble;

import com.cubic.appcubicados.Modelos.Inmueble;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitInmueble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.pager2;

public class Fragment_cubic_1 extends Fragment implements Serializable {

     RecyclerView rvInmueble;
     List<Inmueble> listaInmueble = new ArrayList<>();
     AdaptadorInmueble adaptadorInmueble;
     LinearLayoutManager lym;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_1, container, false);

        rvInmueble = rootView.findViewById(R.id.rvInmueble);
        verInmueble();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //verInmueble();
    }
 private void verInmueble() {
    Call<List<Inmueble>> callInmueble = RetrofitInmueble.getApiService().indexInmueble();
    callInmueble.enqueue(new Callback<List<Inmueble>>() {
        @Override
        public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
            if(response.isSuccessful()){
                listaInmueble = response.body();
                lym = new LinearLayoutManager(getContext());
                lym.setOrientation(LinearLayoutManager.VERTICAL);
                adaptadorInmueble = new AdaptadorInmueble(listaInmueble, getContext());
                rvInmueble.setLayoutManager(lym);
                rvInmueble.setAdapter(adaptadorInmueble);
                rvListener();
            } else {
                Toast.makeText(getContext(),"Inmuebles Vacios", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "Item id: " + listaInmueble.get(position).getIdInmueble(), Toast.LENGTH_SHORT).show();
                        String data =listaInmueble.get(position).getNomInmueble().trim();
                        System.out.println(data);
                       pager2.setCurrentItem(1);

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