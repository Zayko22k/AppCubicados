package com.cubic.appcubicados.Servicios;

import com.cubic.appcubicados.Modelos.TipoConstruccion;
import com.cubic.appcubicados.Modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioService {
    @GET("indexUS")
    Call<List<Usuario>> indexUsuario();
}
