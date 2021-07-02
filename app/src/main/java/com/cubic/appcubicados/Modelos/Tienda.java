package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tienda implements Serializable {

    @SerializedName("idTienda")
    private int idTienda;
    @SerializedName("nomTienda")
    private String nomTienda;


    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }
}
