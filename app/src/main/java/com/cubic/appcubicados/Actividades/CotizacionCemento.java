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

import com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorCemento;
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

public class CotizacionCemento extends AppCompatActivity {
    private RecyclerView rvCemento;
    private AdaptadorCemento adaptadorCemento;
    private ArrayList<CementoProducto> cementoProductoList = new ArrayList<>();
    private CementoProducto cementoProductos;
    private List<Tienda> tiendaList = new ArrayList<>();
    private List<String> nomTiendaList = new ArrayList<>();
    private ProgressBar progressBar;
    private Spinner spTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion_cemento);
        spTienda = findViewById(R.id.spTienda);
        spinnerTienda();
        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.rgb(250, 102, 0), PorterDuff.Mode.SRC_IN);
        rvCemento = findViewById(R.id.recyclerCemento);
        spTienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                switch (position) {
                    case 0:
                        try {
                            cubicar.setIdTienda(1);
                           sodimacScrap();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionCemento.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case 1:
                        try {
                            cubicar.setIdTienda(2);
                            construmartScrap();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionCemento.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        try {
                            cubicar.setIdTienda(3);
                            imperialScrap();
                            rvListener();
                        } catch (Exception e) {
                            Toast.makeText(CotizacionCemento.this, "Error en el id de la tienda", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void construmartScrap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://www.construmart.cl/tiendaonline/webapp/materiales-obra-gruesa/cementos-y-aridos/cementos/117-770-1114?desde=0&orden=preciosAsc&filtros=&precio=&precio1=3000-10000";

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


                        String imgFinal = "https://www.construmart.cl/" + imgUrl;

                        cementoProductoList.add(new CementoProducto(url, imgFinal, marca, descripcion, precio, despacho, retiro, idProducto));
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
                        rvCemento.setHasFixedSize(true);
                        rvCemento.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptadorCemento = new AdaptadorCemento(cementoProductoList, CotizacionCemento.this);
                        rvCemento.setAdapter(adaptadorCemento);
                        adaptadorCemento.notifyDataSetChanged();
                        cementoProductoList = new ArrayList<>();

                    }
                });
            }
        }).start();
    }

    private void sodimacScrap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://www.sodimac.cl/sodimac-cl/category/scat102653/Cemento-y-Morteros?currentpage=1&sortBy=derived.price.event.search.7%2Casc&=&f.availability.buyatstoreZones=100308&f.product.L2_category_paths=scat913790%257C%257CObra%2520gruesa%252Fscat934880%257C%257CCemento%2520y%2520Complementos%252Fscat102653%257C%257CCemento%2520y%2520Morteros&f.product.attribute.Material=cemento%252C%2520aridos%2520y%2520aditivos%3A%3Ahormigon&f.product.brandName=Topex";

                    Document doc = Jsoup.connect(url).get();

                    Elements data = doc.select("div.ie11-product-container");

                    int size = data.size();
                    Log.d("doc", "doc: " + doc);
                    Log.d("data", "data: " + data);
                    Log.d("size", "" + size);
                    for (int i = 0; i < size; i++) {

                        String imgUrl = data.select("div.product-image")
                                .select("img.ie11-image-contain")
                                .eq(i)
                                .attr("data-src");

                        String marca = data.select("div.jsx-886840993")
                                .select("a")
                                .select("div.product-brand")
                                .eq(i)
                                .text();

                        String descripcion = data.select("div.jsx-411745769")
                                .select("a")
                                .select("h2.product-title")
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


                        cementoProductoList.add(new CementoProducto(url, imgUrl, marca, descripcion, precio, despacho, retiro, idProducto));

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
                   // cementoProductoList = new ArrayList<>();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvCemento.setHasFixedSize(true);
                        rvCemento.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptadorCemento = new AdaptadorCemento(cementoProductoList, CotizacionCemento.this);
                        rvCemento.setAdapter(adaptadorCemento);
                        adaptadorCemento.notifyDataSetChanged();
                        cementoProductoList = new ArrayList<>();

                    }
                });
            }
        }).start();
    }

    private void imperialScrap(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://www.imperial.cl/searchresults?&&&N=471717289+3069711006+3949476578&Nrpp=15&suppressResults=false&searchType=guided&type=search";

                    Document doc = Jsoup.connect(url).get();

                    Elements data = doc.select("div.content__grid content__grid--products");

                    int size = data.size();
                    Log.d("doc", "doc: " + doc);
                    Log.d("data", "data: " + data);
                    Log.d("size", "" + size);
                    for (int i = 0; i < size; i++) {

                        String imgUrl = data.select("div.content__product")
                                .select("a")
                                .select("img.content__product-img")
                                .eq(i)
                                .attr("src");

                        String marca = data.select("div.content__product-title")
                                .select("small")
                                .eq(i)
                                .text();

                        String descripcion = data.select("div.content__product-title")
                                .select("a")
                                .eq(i)
                                .text();

                        String precio = data.select("div.content__product-title")
                                .select("p.content__product-offer")
                                .select("span")
                                .eq(i)
                                .text();

                        String despacho = data.select("div.overlay-modal__show")
                                .select("h3.u-text_center")
                                .select("span")
                                .eq(i)
                                .text();

                        String idProducto = data.select("div.content__product")
                                .select("a.CC-product-grid-detail-132647-00")
                                .eq(i)
                                .attr("id");


                        cementoProductoList.add(new CementoProducto(imgUrl, marca, descripcion, precio, despacho, idProducto));

                        Log.d("items", "img: " + imgUrl
                                + " . marca: " + marca +
                                ". descripcion: " + descripcion +
                                ". precio: " + precio +
                                ". despacho: " + despacho +

                                ". idProducto:" + idProducto);
                    }
                   // cementoProductoList = new ArrayList<>();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvCemento.setHasFixedSize(true);
                        rvCemento.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptadorCemento = new AdaptadorCemento(cementoProductoList, CotizacionCemento.this);
                        rvCemento.setAdapter(adaptadorCemento);
                        adaptadorCemento.notifyDataSetChanged();
                        cementoProductoList = new ArrayList<>();

                    }
                });
            }
        }).start();
    }
    private void easy(){

    }

    private void spinnerTienda() {
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
                        spTienda.setAdapter(adapter);
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

        rvCemento.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View child = rvCemento.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rvCemento.getChildAdapterPosition(child);
                        Toast.makeText(CotizacionCemento.this, "Item " + position, Toast.LENGTH_SHORT).show();
                        TextView cmto = findViewById(R.id.txtMarcaCemento);

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