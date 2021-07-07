package com.cubic.appcubicados.Actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_calculo_rev2;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_calculo_rev_nw;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_calculo_sup;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_result;
import com.cubic.appcubicados.Fragments.Cubicacion.Fragment_cubic_result_rev;
import com.cubic.appcubicados.R;

import java.util.ArrayList;
import java.util.List;

public class Cubicador extends AppCompatActivity {
    //Esta lista recibe como parametros fragment
    List<Fragment> fragmentList = new ArrayList<>();
    private FragmentStateAdapter pagerAdapter;
    public static ViewPager2 pager2;
    public static cubic cubicar;
    /**
     * @param savedInstanceState
     * @Autor Pablo Rodriguez
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubicador);
        //Añado los fragment a la lista
        fragmentList.add(new Fragment_cubic_1());
        fragmentList.add(new Fragment_cubic_2());
        fragmentList.add(new Fragment_cubic_3());
        fragmentList.add(new Fragment_cubic_calculo_rev_nw());
        fragmentList.add(new Fragment_cubic_calculo_rev2());
        fragmentList.add(new Fragment_cubic_calculo_sup());
        fragmentList.add(new Fragment_cubic_result());
        fragmentList.add(new Fragment_cubic_result_rev());
        //View pager con clase adaptadora
        pager2 = findViewById(R.id.vpCubic);
        pagerAdapter = new ScreenSlideAdapter(this);
        pager2.setAdapter(pagerAdapter);
        pager2.setPageTransformer(new ZoomOutPageTransformer());
        pager2.setUserInputEnabled(false);

    }

    /**
     *  Este metodo es para cuando el cliente presiona el boton atras en el smartphone
     */
    @Override
    public void onBackPressed() {

        IsFinish("¿Volver a inicio?");

        }

    /**
     * Metodo que despliega un AlertDialog
     * que pregunta si quiere cerrar la app
     * switch.case.positive = redirecciona a la vista usuario
     * switch.case.negative = cierra el alertDialog
     * @param msjAlert
     */
    public void IsFinish(String msjAlert) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent i = new Intent(Cubicador.this, VistaUsuario.class);
                        startActivity(i);
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(Cubicador.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

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