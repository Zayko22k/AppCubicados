package com.cubic.appcubicados.Servicios;


import com.cubic.appcubicados.Modelos.RespuestAsistencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RespuestAsistenciaService {

   @GET("ver/{users_id}")
    Call<List<RespuestAsistencia>> getRespuesta(@Path("users_id") int users_id);
   @POST("vistoRA/{idRespuestAsistencia}")
    Call<RespuestAsistencia> vistoNoti(@Path("idRespuestAsistencia") int idRespuestAsistencia);

}
