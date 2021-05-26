package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inmueble implements Serializable {
    @SerializedName("idInmueble")
    private int idInmueble;
    @SerializedName("nomInmueble")
    private String nomInmueble;

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getNomInmueble() {
        return nomInmueble;
    }

    public void setNomInmueble(String nomInmueble) {
        this.nomInmueble = nomInmueble;
    }
}
