package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.DetalleCotizacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DetalleCotizacionService {

    @GET("indexDT")
    Call<List<DetalleCotizacion>> getDetalleCotis();
    @POST("crearDT")
    Call<DetalleCotizacion> crearDetalleCoti(@Body DetalleCotizacion detalleCotizacion);
    @GET("buscarMicoti/{users_id}")
    Call<List<DetalleCotizacion>> getMiCotizacion(@Path("users_id") int users_id);
    @DELETE("borrarDT/{idDetalleCoti}")
    Call<List<DetalleCotizacion>> deleteCotizacion(@Path("idDetalleCoti") int idDetalleCoti);
    @GET("buscarCoti/{keywords}")
    Call<List<DetalleCotizacion>> buscarCotizacion(@Path("keywords") String keywords);

}
