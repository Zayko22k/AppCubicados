package com.cubic.appcubicados.Actividades;

import android.annotation.SuppressLint;
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
import com.cubic.appcubicados.Modelos.Cubicacion;
import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.Modelos.Material;
import com.cubic.appcubicados.Modelos.Tienda;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class CrearCotizacionPintura extends AppCompatActivity {

    private TextView txtNombreCoti;
    private ImageView imgMaterialMuro;
    private TextView txtInmuebleProyecto;
    private TextView txtTCproyecto;
    private TextView txtCproyecto;
    private TextView txtAnchoProyecto;
    private TextView txtLargoProyecto;
    private TextView txtM2Proyecto;
    private TextView txtLitrosM2;
    private TextView txtTiendaMaterial;
    private TextView txtMarcaMaterial;
    private TextView txtDescripcionMaterial;
    private TextView txtPrecioMaterial;
    private TextView txtDespachoMaterial;
    private TextView txtRetiroMaterial;
    private TextView txtLitrosResultados;
    private Button btnGuardarCoti;
    private Button btnCancelarCoti;
    private DetalleCotizacion detalleCotizacion;
    private Cubicacion cubicacion;
    private Material material;
    private CementoProducto cementoPrice;
    private String totalProyecto;
    private CementoProducto pinturaProducto = new CementoProducto();
    private List<DetalleCotizacion> detalleCotizacionList = new ArrayList<>();
    private ProgressDialog dialog;

    /**
     * @param savedInstanceState
     * @Autor Pablo Rodriguez
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cotizacion_pintura);
        pinturaProducto = (CementoProducto) getIntent().getSerializableExtra("productoSelectMuro");
        //Ficha Tecnica
        txtNombreCoti = findViewById(R.id.txtNombreCotiMuro);
        txtInmuebleProyecto = findViewById(R.id.txtInmProyectoMuro);
        txtTCproyecto = findViewById(R.id.txtTCproyectoMuro);
        txtCproyecto = findViewById(R.id.txtCproyectoMuro);
        txtAnchoProyecto = findViewById(R.id.txtAnchoProyectoMuro);
        txtLargoProyecto = findViewById(R.id.txtLargoProyectoMuro);
        txtM2Proyecto = findViewById(R.id.txtM2ProyectoMuro);
        txtLitrosResultados = findViewById(R.id.txtLitrosResultMuro);
        txtLitrosM2 = findViewById(R.id.txtDosificacionProyectoMuro);
        //Material
        imgMaterialMuro = findViewById(R.id.imgMaterialMuro);
        txtTiendaMaterial = findViewById(R.id.txtTiendaMaterialMuro);
        txtMarcaMaterial = findViewById(R.id.txtMarcaMaterialMuro);
        txtDescripcionMaterial = findViewById(R.id.txtDescripcionMaterialMuro);
        txtPrecioMaterial = findViewById(R.id.txtPrecioMaterialMuro);
        txtDespachoMaterial = findViewById(R.id.txtDespachoMaterialMuro);
        txtRetiroMaterial = findViewById(R.id.txtRetiroMaterialMuro);
        btnGuardarCoti = findViewById(R.id.btnCrearCotiMuro);
        btnCancelarCoti = findViewById(R.id.btnCancelarCotiMuro);
        cargarFichaMuro();
    }

    /**
     * Metodo carga los datos del muro
     */
    @SuppressLint("SetTextI18n")
    private void cargarFichaMuro() {

        //Ficha tecnica
        txtInmuebleProyecto.setText(cubicar.getInmuebleSelect());
        txtTCproyecto.setText(cubicar.getNomTipoConstruccionSelect());
        txtCproyecto.setText(cubicar.getNomConstruccionSelect());
        txtAnchoProyecto.setText(cubicar.getAncho() + " mts");
        System.out.println("Ancho: " + cubicar.getAncho());
        txtLargoProyecto.setText(cubicar.getLargo() + " mts");
        txtM2Proyecto.setText(cubicar.getMuroPorPintar() + " M²");
        int litrosAprox = (int) Math.round(cubicar.getLitrosPintura());
        txtLitrosResultados.setText(litrosAprox + " litros necesitas");
        txtLitrosM2.setText(cubicar.getRendimientoPintura() + " (mts2 / Litros)");
        //Material
        cargarTiendaMuro();
        Picasso.get().load(pinturaProducto.getImgUrl()).into(imgMaterialMuro);
        txtMarcaMaterial.setText(pinturaProducto.getMarca());
        txtDescripcionMaterial.setText(pinturaProducto.getDescripcion());
        txtPrecioMaterial.setText("$ " + pinturaProducto.getPrecio() + " C/U");
        txtDespachoMaterial.setText(pinturaProducto.getDespacho());
        txtRetiroMaterial.setText(pinturaProducto.getRetiro());


    }

    /**
     * Metodo que carga la tienda segun el id asociado
     */
    private void cargarTiendaMuro() {
        Call<Tienda> tiendaCall = RetrofitBuilder.tiendaService.getTiendaSelect(cubicar.getIdTienda());
        tiendaCall.enqueue(new Callback<Tienda>() {
            @Override
            public void onResponse(@NotNull Call<Tienda> call, Response<Tienda> response) {
                if (response.isSuccessful()) {
                    Tienda tienda = response.body();
                    txtTiendaMaterial.setText(tienda.getNomTienda());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Tienda> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
            }
        });
    }

    /**
     * metodo para llamar a un metodo
     * @param v
     */
    public void validarNombreMuro(View v) {
        crearCotizacionMuro();
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

    /**
     * metodo para crear el material en la base de datos
     * con validacion de campos vacios
     */
    public void crearCotizacionMuro() {
        try {
            if (txtNombreCoti.getText().toString().isEmpty()) {
                Toast.makeText(CrearCotizacionPintura.this, "No dejes campos vacios", Toast.LENGTH_LONG).show();
            } else {
                material = new Material();


                material.setImagenMaterial(pinturaProducto.getImgUrl());
                material.setMarcaMaterial(pinturaProducto.getMarca());
                material.setTienda_idTienda(cubicar.getIdTienda());
                material.setRetiro(pinturaProducto.getRetiro());
                material.setDespacho(pinturaProducto.getDespacho());
                material.setDescripcionMaterial(pinturaProducto.getDescripcion());


                cementoPrice = new CementoProducto();
                cementoPrice.setPrecio(pinturaProducto.getPrecio());
                String d = cementoPrice.getPrecio();
                addPrice(cementoPrice.getPrecio(), '.', d.length());
                System.out.println("DECIMAL NUMERO:" + cementoPrice.getPrecio());
                material.setPrecio(new BigDecimal(cementoPrice.getPrecio()));


                Call<Material> cementoCall = RetrofitBuilder.cementoService.crearMaterial(material);
                cementoCall.enqueue(new Callback<Material>() {
                    @Override
                    public void onResponse(@NotNull Call<Material> call, Response<Material> response) {
                        if (response.isSuccessful()) {
                            System.out.println("Pintura creada: ");
                            int idMaterial = response.body().getIdMaterial();
                            crearCubicacionMuro(idMaterial);
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

    /**
     * Metodo que crea la cubicacion con parametro
     * que se encarga de recibir el id del material creado
     * @param idCemento
     */
    private void crearCubicacionMuro(int idCemento) {
        try {
            cubicacion = new Cubicacion();
            cubicacion.setArea(cubicar.getMuroPorPintar());
            cubicacion.setProfundidad(0);
            cubicacion.setAncho(cubicar.getAncho());
            cubicacion.setM3(0);
            cubicacion.setDosificacion(cubicar.getRendimientoPintura());
            cubicacion.setGrava(0);
            cubicacion.setArena(0);
            cubicacion.setAgua(0);
            cubicacion.setLargo(cubicar.getLargo());
            int litrosAprox = (int) Math.round(cubicar.getLitrosPintura());
            cubicacion.setCantidad(litrosAprox);
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
                        guardarCotizacionMuro(idCemento, idCubicacion);
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

    /**
     * Metodo que crea el detalle de la cotizacion recibe dos parametros
     * el idDel material creado y el id de la cubicacion
     * la cual se le pasa al service un objeto que requiere para la creacion
     * de la cotizacion
     * @param idMaterial
     * @param idCubicacion
     */
    public void guardarCotizacionMuro(int idMaterial, int idCubicacion) {

        try {

            detalleCotizacion = new DetalleCotizacion();
            detalleCotizacion.setNombreCoti(txtNombreCoti.getText().toString());
            System.out.println(txtNombreCoti.getText().toString());
            detalleCotizacion.setTotal(new BigDecimal(cementoPrice.getPrecio()));
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("identificadorCl", MODE_PRIVATE);
            String userSave = prefs.getString("userID", null);
            detalleCotizacion.setUsers_id(Integer.parseInt(userSave));
            detalleCotizacion.setCubicacion_idCubica(idCubicacion);
            detalleCotizacion.setMaterial_idMaterial(idMaterial);
            System.out.println("ID Cubicacion " + idCubicacion);
            System.out.println("ID de usuario " + userSave);
            System.out.println("ID Material " + idMaterial);
            Call<DetalleCotizacion> detalleCotizacionCall = RetrofitBuilder.detalleCotizacionService.crearDetalleCoti(detalleCotizacion);
            detalleCotizacionCall.enqueue(new Callback<DetalleCotizacion>() {
                @Override
                public void onResponse(Call<DetalleCotizacion> call, Response<DetalleCotizacion> response) {
                    if (response.isSuccessful()) {
                        System.out.println("Cotizacion creada");
                        dialog = ProgressDialog.show(CrearCotizacionPintura.this, "",
                                "Espere. Creando su cotización", true);
                        dialog.show();
                        new CrearCotizacionPintura.Hilo1().start();
                    }
                }

                @Override
                public void onFailure(Call<DetalleCotizacion> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }


    }

    /**
     * Hilo que tira un toast que indica la creacion de la cotizacion
     */
    class Hilo1 extends Thread {
        @Override
        public void run() {
            try {

                Thread.sleep(3000);
                runOnUiThread(() -> {
                    Toast.makeText(CrearCotizacionPintura.this, "¡Cotizacion creada!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                });
                Thread.sleep(1500);
                Intent i = new Intent(CrearCotizacionPintura.this, VistaUsuario.class);
                startActivity(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * metodo para ajustar el precio
     * la cual se encarga de agregar el punto determinado el largo
     * de la cadena
     * @param str
     * @param ch
     * @param position
     * @return
     */
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

    /**
     * Metodo que llama a un metodo isFinish que se le pasa
     * por parametro una cadena
     * @param v
     */
    public void cerrar(View v) {
        IsFinish("¿Estás seguro deseas cancelar?");
    }

    /**
     * cuando se presiona atrasvuelve al principio
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("¿Volver al principio?");
    }

    public void IsFinish(String msjAlert) {

        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Intent i = new Intent(CrearCotizacionPintura.this, VistaUsuario.class);
                    startActivity(i);
                    // This above line close correctly
                    //finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CrearCotizacionPintura.this);
        builder.setMessage(msjAlert)
                .setPositiveButton("Salir", dialogClickListener)
                .setNegativeButton("Quedarse", dialogClickListener).show();

    }
}