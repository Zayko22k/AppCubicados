package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Cemento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CementoService {

    @POST("crearMa")
    Call<Cemento> crearCemento(@Body Cemento cemento);
}
