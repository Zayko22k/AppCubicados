package com.cubic.appcubicados.Retrofit;

import com.cubic.appcubicados.Servicios.InmuebleService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInmueble {
    private static Retrofit retrofit = null;
    public static final String url_BASE ="http://localhost/Project_Cubic/public/api/";

    public static InmuebleService getApiService() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(InmuebleService.class);
    }
}