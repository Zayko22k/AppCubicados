package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Region;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegionService {

    @GET("indexRe")
    Call<List<Region>> getRegion();
}
