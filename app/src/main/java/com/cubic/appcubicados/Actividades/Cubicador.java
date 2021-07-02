package com.cubic.appcubicados.Actividades;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.cubic.appcubicados.Clases.ZoomOutPageTransformer;
import com.cubic.appcubicados.Clases.cubic;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_1;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_2;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_3;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_4;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_result;
import com.cubic.appcubicados.R;

import java.util.ArrayList;
import java.util.List;

public class Cubicador extends AppCompatActivity {
    //Esta lista recibe como parametros fragment
    List<Fragment> fragmentList = new ArrayList<>();
    private FragmentStateAdapter pagerAdapter;
    public static ViewPager2 pager2;
    public static cubic cubicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubicador);
        //Añado los fragment a la lista
        fragmentList.add(new Fragment_cubic_1());
        fragmentList.add(new Fragment_cubic_2());
        fragmentList.add(new Fragment_cubic_3());
        fragmentList.add(new Fragment_cubic_4());
        fragmentList.add(new Fragment_cubic_result());
        //View pager con clase adaptadora
        pager2 = findViewById(R.id.vpCubic);
        pagerAdapter = new ScreenSlideAdapter(this);
        pager2.setAdapter(pagerAdapter);
        pager2.setPageTransformer(new ZoomOutPageTransformer());
        pager2.setUserInputEnabled(false);

    }

    //Este metodo es para cuando el cliente presiona el boton atras en el smartphone
    @Override
    public void onBackPressed() {
        if (pager2.getCurrentItem() == 0) {
            //Si el usuario está mirando actualmente el primer paso, permite que el sistema maneje el
            //  Botón de retroceso. Esto llama a finish () en esta actividad y abre la pila de actividades.
            super.onBackPressed();
        } else {
            cubicar = new cubic();
            pager2.setCurrentItem(pager2.getCurrentItem() - 1);

        }
    }

    /*Adaptador que realiza el conteo de elementos que tiene la lista fragmentlist
     /*y crea los fragment
   */
    private class ScreenSlideAdapter extends FragmentStateAdapter {
        public ScreenSlideAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}