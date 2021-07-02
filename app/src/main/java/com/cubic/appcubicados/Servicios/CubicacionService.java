package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Cubicacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CubicacionService {

    @GET("indexCubic")
    Call<List<Cubicacion>> getCubicaciones();
    @POST("crearCubic")
    Call<Cubicacion> crearCubicacion(@Body Cubicacion cubicacion);

}
