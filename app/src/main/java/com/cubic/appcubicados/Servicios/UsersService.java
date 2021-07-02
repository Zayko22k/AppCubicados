package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface UsersService {
    @POST("login")
    Call<Users> loginResponse(@Body Users users);
    @POST("user")
    Call<Users> getSecret(@Header("Authorization") String authToken);
}
