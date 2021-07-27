package com.cubic.appcubicados.Retrofit;

import com.cubic.appcubicados.Servicios.ArriendoService;
import com.cubic.appcubicados.Servicios.AsistenciaService;
import com.cubic.appcubicados.Servicios.ConstruccionesService;
import com.cubic.appcubicados.Servicios.CubicacionService;
import com.cubic.appcubicados.Servicios.DetalleCotizacionService;
import com.cubic.appcubicados.Servicios.InmuebleService;
import com.cubic.appcubicados.Servicios.MaterialService;
import com.cubic.appcubicados.Servicios.RegionService;
import com.cubic.appcubicados.Servicios.RespuestAsistenciaService;
import com.cubic.appcubicados.Servicios.TiendaService;
import com.cubic.appcubicados.Servicios.TipoConstruccionService;
import com.cubic.appcubicados.Servicios.UsersService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addNetworkInterceptor(
                    chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Connection", "close")
                                .build();
                        return chain.proceed(request);
                    }
            )
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor).build();
    public static final String url_BASE = "http://192.168.1.10/Project_Cubic/public/api/";
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url_BASE)

            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ArriendoService arriendoService = retrofit.create(ArriendoService.class);
    public static AsistenciaService asistenciaService = retrofit.create(AsistenciaService.class);
    public static ConstruccionesService construccionesService = retrofit.create(ConstruccionesService.class);
    public static CubicacionService cubicacionService = retrofit.create(CubicacionService.class);
    public static DetalleCotizacionService detalleCotizacionService = retrofit.create(DetalleCotizacionService.class);
    public static InmuebleService inmuebleService = retrofit.create(InmuebleService.class);
    public static MaterialService cementoService = retrofit.create(MaterialService.class);
    public static RegionService regionService = retrofit.create(RegionService.class);
    public static RespuestAsistenciaService respuestAsistenciaService = retrofit.create(RespuestAsistenciaService.class);
    public static TiendaService tiendaService = retrofit.create(TiendaService.class);
    public static TipoConstruccionService tipoConstruccionService = retrofit.create(TipoConstruccionService.class);
    public static UsersService usersService = retrofit.create(UsersService.class);
}
