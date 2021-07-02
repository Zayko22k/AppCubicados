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

import com.cubic.appcubicados.Adaptadores.AdaptadorCons;
import com.cubic.appcubicados.Modelos.Construcciones;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_3 extends Fragment {

    RecyclerView rvCons;
    LinearLayoutManager lym;
    AdaptadorCons adaptadorCons;
    List<Construcciones> construccionesList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_3, container, false);
        rvCons = rootView.findViewById(R.id.rvCons);
        verCons();
        return rootView;
    }
    private void verCons(){
        Call<List<Construcciones>> construccionesCall = RetrofitBuilder.construccionesService.indexConstrucciones();
        construccionesCall.enqueue(new Callback<List<Construcciones>>() {
            @Override
            public void onResponse(Call<List<Construcciones>> call, Response<List<Construcciones>> response) {
                if(response.isSuccessful()){
                    construccionesList = response.body();
                    lym = new LinearLayoutManager(getContext());
                    lym.setOrientation(LinearLayoutManager.VERTICAL);
                    adaptadorCons = new AdaptadorCons(construccionesList, getContext());
                    rvCons.setLayoutManager(lym);
                    rvCons.setAdapter(adaptadorCons);
                    rvListener();
                }else{
                    Toast.makeText(getContext(),"Construcciones vacios", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Construcciones>> call, Throwable t) {
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
        rvCons.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvCons.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvCons.getChildAdapterPosition(child);
                        Toast.makeText(getContext(), "Item id: " + construccionesList.get(position).getIdConstrucciones(), Toast.LENGTH_SHORT).show();
                        String data = construccionesList.get(position).getNomConstr();
                        cubicar.setNomConstruccionSelect(data);
                        cubicar.setIdConstrucciones(construccionesList.get(position).getIdConstrucciones());
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        Fragment_cubic_4 fragment1 = new Fragment_cubic_4();

                        manager.beginTransaction()
                                .replace(R.id.activity_cubicar, fragment1)
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
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