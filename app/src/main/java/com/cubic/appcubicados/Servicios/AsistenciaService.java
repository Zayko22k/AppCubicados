package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Asistencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AsistenciaService {

    @GET("indexA")
    Call<List<Asistencia>> getAsistencia();

    @POST("crearA")
    Call<Asistencia> insertAsistencia(@Body Asistencia asistencia);

}
