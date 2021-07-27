package com.cubic.appcubicados.Actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cubic.appcubicados.Clases.CementoProducto;
import com.cubic.appcubicados.Modelos.Material;
import com.cubic.appcubicados.Modelos.Cubicacion;
import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.Modelos.Tienda;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class CrearCotizacion extends AppCompatActivity {

    private TextView txtNombreCoti;
    private ImageView imgMaterial;
    private TextView txtInmuebleProyecto;
    private TextView txtTCproyecto;
    private TextView txtCproyecto;
    private TextView txtAnchoProyecto;
    private TextView txtLargoProyecto;
    private TextView txtAlturaProyecto;
    private TextView txtM3Proyecto;
    private TextView txtCantSacosProyecto;
    private TextView txtGravaProyecto;
    private TextView txtArenaProyecto;
    private TextView txtAguaProyecto;
    private TextView txtSacosxM3;
    private TextView txtTiendaMaterial;
    private TextView txtMarcaMaterial;
    private TextView txtDescripcionMaterial;
    private TextView txtPrecioMaterial;
    private TextView txtTotalMaterial;
    private TextView txtDespachoMaterial;
    private TextView txtRetiroMaterial;
    private Button btnGuardarCoti;
    private Button btnCancelarCoti;
    private DetalleCotizacion detalleCotizacion;
    private Cubicacion cubicacion;
    private Material material;
    private CementoProducto cementoPrice;
    private String totalProyecto;
    private CementoProducto cementoProducto = new CementoProducto();
    private List<DetalleCotizacion> detalleCotizacionList = new ArrayList<>();
    private ProgressDialog dialog;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cotizacion);


        cementoProducto = (CementoProducto) getIntent().getSerializableExtra("productoSelect");
        //Ficha Tecnica
        txtNombreCoti = findViewById(R.id.txtNombreCoti);
        txtInmuebleProyecto = findViewById(R.id.txtInmProyecto);
        txtTCproyecto = findViewById(R.id.txtTCproyecto);
        txtCproyecto = findViewById(R.id.txtCproyecto);
        txtAnchoProyecto = findViewById(R.id.txtAnchoProyecto);
        txtLargoProyecto = findViewById(R.id.txtLargoProyecto);
        txtAlturaProyecto = findViewById(R.id.txtAltoProyecto);
        txtM3Proyecto = findViewById(R.id.txtM3Proyecto);
        txtCantSacosProyecto = findViewById(R.id.txtsacosProyecto);
        txtGravaProyecto = findViewById(R.id.txtGravaProyecto);
        txtArenaProyecto = findViewById(R.id.txtArenaProyecto);
        txtAguaProyecto = findViewById(R.id.txtAguaProyecto);
        txtSacosxM3 = findViewById(R.id.txtDosificacionProyecto);
        //Material
        imgMaterial = findViewById(R.id.imgMaterial);
        txtTiendaMaterial = findViewById(R.id.txtTiendaMaterial);
        txtMarcaMaterial = findViewById(R.id.txtMarcaMaterial);
        txtDescripcionMaterial = findViewById(R.id.txtDescripcionMaterial);
        txtPrecioMaterial = findViewById(R.id.txtPrecioMaterial);
        txtTotalMaterial = findViewById(R.id.txtTotalMaterial);
        txtDespachoMaterial = findViewById(R.id.txtDespachoMaterial);
        txtRetiroMaterial = findViewById(R.id.txtRetiroMaterial);
        btnGuardarCoti = findViewById(R.id.btnCrearCoti);
        btnCancelarCoti = findViewById(R.id.btnCancelarCoti);

        cargarFicha();
    }

    /**
     * @Get cubicar.trae los datos previamente enviados
     * @Get
     *
     */
    private void cargarFicha() {
        try {
            //Ficha tecnica
            DecimalFormat formato1 = new DecimalFormat("#.00");
            txtInmuebleProyecto.setText(cubicar.getInmuebleSelect());
            txtTCproyecto.setText(cubicar.getNomTipoConstruccionSelect());
            txtCproyecto.setText(cubicar.getNomConstruccionSelect());
            txtAnchoProyecto.setText(cubicar.getAncho() + " mts");
            txtLargoProyecto.setText(cubicar.getLargo() + " mts");
            txtAlturaProyecto.setText(cubicar.getAlto() + " cms");
            txtM3Proyecto.setText(formato1.format(cubicar.getM3()) + " M³");
            txtCantSacosProyecto.setText(Math.round(cubicar.getSacosCemento()) + " sacos de cemento necesitas");
            txtGravaProyecto.setText(String.valueOf(cubicar.getGravillaOcupar()));
            txtArenaProyecto.setText(String.valueOf(cubicar.getArenaOcupar()));
            txtAguaProyecto.setText(String.valueOf(cubicar.getAguaOcupar()));
            txtSacosxM3.setText(cubicar.getDosificacion() + " (Sacos/M³)");
            //Material
            cargarTienda();
            Picasso.get().load(cementoProducto.getImgUrl()).into(imgMaterial);
            txtMarcaMaterial.setText(cementoProducto.getMarca());
            txtDescripcionMaterial.setText(cementoProducto.getDescripcion());

            txtPrecioMaterial.setText("$ " + cementoProducto.getPrecio() + " C/U");
            String total = String.valueOf(getIntent().getSerializableExtra("total"));
            txtTotalMaterial.setText("$ " + total + " por los " + Math.round(cubicar.getSacosCemento()) + " sacos");
            txtDespachoMaterial.setText(cementoProducto.getDespacho());
            txtRetiroMaterial.setText(cementoProducto.getRetiro());

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    private void cargarTienda() {
        Call<Tienda> tiendaCall = RetrofitBuilder.tiendaService.getTiendaSelect(cubicar.getIdTienda());
        tiendaCall.enqueue(new Callback<Tienda>() {
            @Override
            public void onResponse(Call<Tienda> call, Response<Tienda> response) {
                if (response.isSuccessful()) {
                    Tienda tienda = response.body();
                    txtTiendaMaterial.setText(tienda.getNomTienda());
                }
            }

            @Override
            public void onFailure(Call<Tienda> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
            }
        });
    }

    public void validarNombre(View v){
        crearCotizacion();
       /* SharedPreferences prefs = getApplicationContext().getSharedPreferences("identificadorCl", MODE_PRIVATE);
        String userSave = prefs.getString("userID", null);
        int idUs = Integer.parseInt(userSave);
        Call<List<DetalleCotizacion>> detalleCotiCall = RetrofitBuilder.detalleCotizacionService.getMiCotizacion(idUs);
        detalleCotiCall.enqueue(new Callback<List<DetalleCotizacion>>() {
            @Override
            public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                if (response.isSuccessful()) {

                    detalleCotizacionList = response.body();
                    for (int i = 0 ; i < detalleCotizacionList.size(); i++){

                        if(detalleCotizacionList.get(i).getNombreCoti().equals(txtNombreCoti.getText().toString())){
                            Toast.makeText(CrearCotizacion.this,"¡Ya tienes una cotizacion con ese nombre!", Toast.LENGTH_LONG).show();
                        } else if(detalleCotizacionList.isEmpty()){

                        }
                    }
                } else {
                    System.out.println("Contador: "+response.body().size());
                    Toast.makeText(CrearCotizacion.this, "Error en cotizaciones", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {
                   Log.d("Error ",t.getMessage());
            }
        });*/
    }

    public void crearCotizacion() {
        try {
            if(txtNombreCoti.getText().toString().isEmpty()){
                Toast.makeText(CrearCotizacion.this, "No dejes campos vacios", Toast.LENGTH_LONG).show();
            }else {
                material = new Material();


                material.setImagenMaterial(cementoProducto.getImgUrl());
                material.setMarcaMaterial(cementoProducto.getMarca());
                material.setTienda_idTienda(cubicar.getIdTienda());
                material.setRetiro(cementoProducto.getRetiro());
                material.setDespacho(cementoProducto.getDespacho());
                material.setDescripcionMaterial(cementoProducto.getDescripcion());


                cementoPrice = new CementoProducto();
                cementoPrice.setPrecio(cementoProducto.getPrecio());
                String d = cementoPrice.getPrecio();
                addPrice(cementoPrice.getPrecio(), '.', d.length());
                System.out.println("DECIMAL NUMERO:" + cementoPrice.getPrecio());
                material.setPrecio(new BigDecimal(cementoPrice.getPrecio()));


                Call<Material> cementoCall = RetrofitBuilder.cementoService.crearMaterial(material);
                cementoCall.enqueue(new Callback<Material>() {
                    @Override
                    public void onResponse(Call<Material> call, Response<Material> response) {
                        if (response.isSuccessful()) {
                            System.out.println("Cemento creado: ");
                            int idMaterial = response.body().getIdMaterial();
                            crearCubicacion(idMaterial);
                        }
                    }

                    @Override
                    public void onFailure(Call<Material> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private void crearCubicacion(int idCemento) {
        try {
            cubicacion = new Cubicacion();
            cubicacion.setArea(cubicar.getArea());
            cubicacion.setProfundidad(cubicar.getAlto());
            cubicacion.setAncho(cubicar.getAncho());
            cubicacion.setVolumen(0);
            cubicacion.setM3(cubicar.getM3());
            cubicacion.setDosificacion(cubicar.getDosificacion());
            cubicacion.setGrava(cubicar.getGravillaOcupar());
            cubicacion.setArena(cubicar.getArenaOcupar());
            cubicacion.setAgua(cubicar.getAguaOcupar());
            cubicacion.setLargo(cubicar.getLargo());
            int sacosAprox = (int) Math.round(cubicar.getSacosCemento());
            cubicacion.setCantidad(sacosAprox);
            cubicacion.setInmueble_idInmueble(cubicar.getIdInmueble());
            cubicacion.setConstrucciones_idConstrucciones(cubicar.getIdConstrucciones());

            Call<Cubicacion> cubicacionCall = RetrofitBuilder.cubicacionService.crearCubicacion(cubicacion);
            cubicacionCall.enqueue(new Callback<Cubicacion>() {
                @Override
                public void onResponse(Call<Cubicacion> call, Response<Cubicacion> response) {
                    if (response.isSuccessful()) {
                        System.out.println("Cubicacion creada");
                        cubicacion = new Cubicacion();
                        cubicacion = response.body();
                        int idCubicacion = cubicacion.getIdCubica();
                        guardarCotizacion(idCemento, idCubicacion);
                        System.out.println("llamada dentro del metodo de cubic: " + response.body().getIdCubica());


                    } else {
                        System.out.println("Cubicacion no creada");
                    }
                }

                @Override
                public void onFailure(Call<Cubicacion> call, Throwable t) {
                    Log.d("Error: ", t.getMessage());
                }
            });

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void guardarCotizacion(int idMaterial, int idCubicacion) {

        try {
                detalleCotizacion = new DetalleCotizacion();
                detalleCotizacion.setNombreCoti(txtNombreCoti.getText().toString());
                System.out.println(txtNombreCoti.getText().toString());
                totalProyecto = new String();
                totalProyecto = String.valueOf(getIntent().getSerializableExtra("total"));
                addTotal(totalProyecto, '.', totalProyecto.length());
                System.out.println("TOTAL: " + totalProyecto);
                detalleCotizacion.setTotal(new BigDecimal(totalProyecto));
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("identificadorCl", MODE_PRIVATE);
                String userSave = prefs.getString("userID", null);
                detalleCotizacion.setUsers_id(Integer.parseInt(userSave));
                detalleCotizacion.setCubicacion_idCubica(idCubicacion);
                detalleCotizacion.setMaterial_idMaterial(idMaterial);
                System.out.println("ID Cubicacion " + idCubicacion);
                System.out.println("ID de usuario " + userSave);
                System.out.println("ID Cemento " + idMaterial);
                Call<DetalleCotizacion> detalleCotizacionCall = RetrofitBuilder.detalleCotizacionService.crearDetalleCoti(detalleCotizacion);
                detalleCotizacionCall.enqueue(new Callback<DetalleCotizacion>() {
                    @Override
                    public void onResponse(Call<DetalleCotizacion> call, Response<DetalleCotizacion> response) {
                        if (response.isSuccessful()) {
                            System.out.println("Cotizacion creada");
                            dialog = ProgressDialog.show(CrearCotizacion.this, "",
                                    "Espere. Creando su cotización", true);
                            dialog.show();
                            new Hilo1().start();
                        }
                    }

                    @Override
                    public void onFailure(Call<DetalleCotizacion> call, Throwable t) {
                               Log.d("Error",t.getMessage());
                    }
                });

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }


    }
    class Hilo1 extends Thread {
        @Override
        public void run() {
            try {


                Thread.sleep(3000);

                runOnUiThread(() -> {
                    Toast.makeText(CrearCotizacion.this, "¡Cotizacion creada!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                });
                Thread.sleep(1500);
                Intent i = new Intent(CrearCotizacion.this, VistaUsuario.class);
                startActivity(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public String addPrice(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        if (position == 4) {
            sb.insert(1, ch);
        } else if (position == 5) {
            sb.insert(2, ch);
        } else if (position == 6) {
            sb.insert(3, ch);
        }
        cementoPrice.setPrecio(sb.toString());

        return sb.toString();
    }

    public String addTotal(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        if (position == 4) {
            sb.insert(1, ch);
        } else if (position == 5) {
            sb.insert(2, ch);
        } else if (position == 6) {
            sb.insert(3, ch);
        }
        totalProyecto = sb.toString();
        return sb.toString();
    }


    public void cerrar(View v) {
        IsFinish("¿Estás seguro deseas cancelar?");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("¿Volver al principio?");
    }

    public void IsFinish(String msjAlert) {

        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Intent i = new Intent(CrearCotizacion.this, VistaUsuario.class);
                    startActivity(i);
                    // This above line close correctly
                    //finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CrearCotizacion.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

    }
}