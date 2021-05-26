package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Construcciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConstruccionesService {
    @GET("indexC")
    Call<List<Construcciones>> indexConstrucciones();
}
