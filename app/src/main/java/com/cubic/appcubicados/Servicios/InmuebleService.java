package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Inmueble;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InmuebleService {
    @GET("indexIn")
    Call<List<Inmueble>> indexInmueble();
}

