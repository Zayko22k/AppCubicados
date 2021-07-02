package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Arriendo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArriendoService {

    @GET("indexAr")
    Call<List<Arriendo>> getArriendoList();
    @GET("buscarxUser/{users_id}")
    Call<List<Arriendo>> getArriendo(@Path("users_id") int users_id);

}
