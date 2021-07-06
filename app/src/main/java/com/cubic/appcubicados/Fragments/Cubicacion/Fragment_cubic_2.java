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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.AdaptadorTipoCons;
import com.cubic.appcubicados.Modelos.TipoConstruccion;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class Fragment_cubic_2 extends Fragment implements Serializable {

    RecyclerView rvTipoCons;
    List<TipoConstruccion> tipoConstruccionList = new ArrayList<>();
    AdaptadorTipoCons adaptadorTipoCons;
    LinearLayoutManager lym;
    TextView txtInmSelect;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_cubic_2, container, false);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTipoCons = view.findViewById(R.id.rvTipoCons);

        verTipoCons();
    }

    private void verTipoCons(){

        Call<List<TipoConstruccion>> callTipoCons = RetrofitBuilder.tipoConstruccionService.indexTipoCons();
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
                        Toast.makeText(getContext(), "Item " + tipoConstruccionList.get(position).getIdTipoConstruccion(), Toast.LENGTH_SHORT).show();
                        String data = tipoConstruccionList.get(position).getNomTipoCons();
                        cubicar.setNomTipoConstruccionSelect(data);
                        cubicar.setIdTipoConstruccion(tipoConstruccionList.get(position).getIdTipoConstruccion());
                        System.out.println(tipoConstruccionList.get(position).getIdTipoConstruccion());
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        Fragment_cubic_3 fragment1 = new Fragment_cubic_3();
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