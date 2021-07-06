package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Material;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MaterialService {

    @POST("crearMa")
    Call<Material> crearMaterial(@Body Material material);
}
