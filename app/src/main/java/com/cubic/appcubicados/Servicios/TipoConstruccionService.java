package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.TipoConstruccion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TipoConstruccionService {
    @GET("indexTC")
    Call<List<TipoConstruccion>> indexTipoCons();
}