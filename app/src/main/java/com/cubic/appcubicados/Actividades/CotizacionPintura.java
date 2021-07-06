package com.cubic.appcubicados.Actividades;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorPintura;
import com.cubic.appcubicados.Clases.CementoProducto;
import com.cubic.appcubicados.Modelos.Tienda;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class CotizacionPintura extends AppCompatActivity {
    private RecyclerView rvPintura;
    private AdaptadorPintura adaptadorPintura;
    private ArrayList<CementoProducto> pinturaProductoList = new ArrayList<>();
    private CementoProducto pinturaProductos;
    private List<Tienda> tiendaList = new ArrayList<>();
    private List<String> nomTiendaList = new ArrayList<>();
    private ProgressBar progressBar;
    private Spinner spTiendaPintura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion_pintura);
        spTiendaPintura = findViewById(R.id.spTiendaPintura);
        spinnerTiendaPintura();
        progressBar = findViewById(R.id.progressBarP);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.rgb(250, 102, 0), PorterDuff.Mode.SRC_IN);
        rvPintura = findViewById(R.id.recyclerPintura);
        spTiendaPintura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                switch (position) {
                    case 0:
                        try {
                            cubicar.setIdTienda(1);
                            System.out.println("Id Pintura"+cubicar.getTipoPintura());
                            sodimacScrapPintura();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionPintura.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case 1:
                        try {
                            cubicar.setIdTienda(2);
                            construmartScrap();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionPintura.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        try {
                            cubicar.setIdTienda(3);
                           // imperialScrap();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionPintura.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void spinnerTiendaPintura() {
        Call<List<Tienda>> tiendaCall = RetrofitBuilder.tiendaService.getTiendas();
        tiendaCall.enqueue(new Callback<List<Tienda>>() {
            @Override
            public void onResponse(Call<List<Tienda>> call, Response<List<Tienda>> response) {
                if (response.isSuccessful()) {
                    tiendaList = response.body();
                    for (int i = 0; i < tiendaList.size(); i++) {
                        nomTiendaList.add(tiendaList.get(i).getNomTienda());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nomTiendaList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spTiendaPintura.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Tienda>> call, Throwable t) {

            }
        });


    }
    private void rvListener() {
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        rvPintura.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvPintura.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvPintura.getChildAdapterPosition(child);
                        Toast.makeText(CotizacionPintura.this, "Item " + position, Toast.LENGTH_SHORT).show();
                        TextView cmto = findViewById(R.id.txtMarcaPintura);

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

    private void sodimacScrapPintura() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "";
                    if(cubicar.getTipoPintura()== 0){
                        url = "https://www.sodimac.cl/sodimac-cl/category/scat359268/Esmaltes-al-agua?currentpage=1&sortBy=derived.price.event.search.7,asc&f.availability.buyatstoreZones=100308&f.product.attribute.Aplicaci%C3%B3n=interior%20y%20exterior";
                    } else if(cubicar.getTipoPintura() == 1){
                        url ="https://www.sodimac.cl/sodimac-cl/category/scat359269?currentpage=1&sortBy=derived.price.event.search.7%2Casc&=&f.availability.buyatstoreZones=100308&f.product.attribute.Aplicaci%C3%B3n=interior%2520y%2520exterior&f.product.attribute.Contenido=1%2520galon(es)";
                    } else if(cubicar.getTipoPintura() == 2){
                        url = "https://www.sodimac.cl/sodimac-cl/category/scat359271?currentpage=1&sortBy=derived.price.event.search.7,asc&f.availability.buyatstoreZones=100308&f.product.attribute.Aplicaci%C3%B3n=interior%20y%20exterior&f.product.attribute.Contenido=1%20galon(es)";

                    } else if(cubicar.getTipoPintura() == 3){
                        url = "https://www.sodimac.cl/sodimac-cl/category/scat359272?currentpage=1&sortBy=derived.price.event.search.7%2Casc&=&f.availability.buyatstoreZones=100308&f.product.attribute.Aplicaci%C3%B3n=interior%2520y%2520exterior&f.product.attribute.Contenido=1%2520galon(es)";
                    }
                    Document doc = Jsoup.connect(url).get();

                    Elements data = doc.select("div.product-container");

                    int size = data.size();
                    Log.d("doc", "doc: " + doc);
                    Log.d("data", "data: " + data);
                    Log.d("size", "" + size);
                    for (int i = 0; i < size; i++) {

                        String imgUrl = data.select("div.product-image")
                                .select("img.ie11-image-contain")
                                .eq(i)
                                .attr("data-src");


                        String marca = data.select("div.jsx-110785930.brand-name")
                                .eq(i)
                                .text();


                        String descripcion = data.select("div.jsx-110785930.title-name")
                                .eq(i)
                                .text();

                        String precio = data.select("div.jsx-175035124")
                                .eq(i)
                                .text();

                        String despacho = data.select("div.dispatch-info")
                                .eq(i)
                                .text();

                        String retiro = data.select("div.withdrawl-info")
                                .eq(i)
                                .text();

                        String idProducto = data.select("div.product-image")
                                .select("img.ie11-image-contain")
                                .eq(i)
                                .attr("id");


                        pinturaProductoList.add(new CementoProducto(url, imgUrl, marca, descripcion, precio, despacho, retiro, idProducto));

                        Log.d("items",
                                "Url Tienda: "+ url
                                        +"img: " + imgUrl
                                        + " . marca: " + marca +
                                        ". descripcion: " + descripcion +
                                        ". precio: " + precio +
                                        ". despacho: " + despacho +
                                        ". retiro: " + retiro +
                                        ". idProducto:" + idProducto);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvPintura.setHasFixedSize(true);
                        rvPintura.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptadorPintura = new AdaptadorPintura(pinturaProductoList, CotizacionPintura.this);
                        rvPintura.setAdapter(adaptadorPintura);
                        adaptadorPintura.notifyDataSetChanged();
                        pinturaProductoList = new ArrayList<>();

                    }
                });
            }
        }).start();
    }

    private void construmartScrap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                     String url = "";
                    if(cubicar.getTipoPintura()== 0){
                        url  = "https://www.construmart.cl/tiendaonline/webapp/pinturas/pintura-decorativas/esmaltes-al-agua/118-780-1132?desde=0&orden=preciosAsc&filtros=&precio=&precio1=";
                    } else if(cubicar.getTipoPintura() == 1){
                        url  = "https://www.construmart.cl/tiendaonline/webapp/pinturas/pintura-decorativas/latex/118-780-1133";
                    } else if(cubicar.getTipoPintura() == 2){
                        url = "https://www.construmart.cl/tiendaonline/webapp/pinturas/pintura-decorativas/esmalte-sintetico/118-780-1131";
                    } else if(cubicar.getTipoPintura() == 3){
                        url  = "https://www.construmart.cl/tiendaonline/webapp/pinturas/pintura-decorativas/oleos/118-780-1134";
                    }
                    Document doc = Jsoup.connect(url).get();

                    Elements data = doc.select("div.container-productos");

                    int size = data.size();
                    Log.d("doc", "doc: " + doc);
                    Log.d("data", "data: " + data);
                    Log.d("size", "" + size);
                    for (int i = 0; i < size; i++) {

                        String imgUrl = data.select("div.imageHover")
                                .select("a")
                                .select("img")
                                .eq(i)
                                .attr("src");

                        String marca = data.select("div.description")
                                .select("h5.marca")
                                .eq(i)
                                .text();

                        String descripcion = data.select("div.description")
                                .select("h2.minificha__nombre")
                                .select("a")
                                .eq(i)
                                .text();

                        String precio = data.select("div.description")
                                .select("div.price")
                                .eq(i)
                                .text();

                        String despacho = data.select("div.clearfix")
                                .select("div.msj-venta")
                                .select("p.despacho--disponible")
                                .eq(i)
                                .text();

                        String retiro = data.select("div.clearfix")
                                .select("div.msj-venta")
                                .select("p.en-tienda--disponible")
                                .eq(i)
                                .text();

                        String idProducto = data.select("div.imageHover")
                                .select("a")
                                .eq(i)
                                .attr("href");


                        String imgFinal = "https://www.construmart.cl" + imgUrl;

                        pinturaProductoList.add(new CementoProducto(url, imgFinal, marca, descripcion, precio, despacho, retiro, idProducto));
                        Log.d("items", "img: " + imgFinal
                                + " . marca: " + marca +
                                ". descripcion: " + descripcion +
                                ". precio: " + precio +
                                ". despacho: " + despacho +
                                ". retiro: " + retiro +
                                ". idProducto: " + idProducto);
                    }
                    //cementoProductoList = new ArrayList<>();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvPintura.setHasFixedSize(true);
                        rvPintura.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptadorPintura = new AdaptadorPintura(pinturaProductoList, CotizacionPintura.this);
                        rvPintura.setAdapter(adaptadorPintura);
                        adaptadorPintura.notifyDataSetChanged();
                        pinturaProductoList = new ArrayList<>();

                    }
                });
            }
        }).start();
    }

}