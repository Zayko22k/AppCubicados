package com.cubic.appcubicados.Retrofit;

import com.cubic.appcubicados.Servicios.TipoConstruccionService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTipoCons {
        private static Retrofit retrofit = null;
        public static final String url_BASE ="http://192.168.1.12/Project_Cubic/public/api/";

        public static TipoConstruccionService getApiService() {
            if(retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit.create(TipoConstruccionService.class);
        }
}