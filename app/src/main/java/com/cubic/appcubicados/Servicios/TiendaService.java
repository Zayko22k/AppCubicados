package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Tienda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TiendaService {

    @GET("indexTienda")
    Call<List<Tienda>> getTiendas();
    @GET("buscarTienda/{idTienda}")
    Call<Tienda> getTiendaSelect(@Path("idTienda") int idTienda);
}
