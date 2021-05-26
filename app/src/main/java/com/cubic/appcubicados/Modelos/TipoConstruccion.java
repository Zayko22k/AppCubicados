package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TipoConstruccion implements Serializable {

    @SerializedName("idTipoConstruccion")
    private int idTipoConstruccion;
    @SerializedName("nomTipoCons")
    private String nomTipoCons;


    public int getIdTipoConstruccion() {
        return idTipoConstruccion;
    }

    public void setIdTipoConstruccion(int idTipoConstruccion) {
        this.idTipoConstruccion = idTipoConstruccion;
    }

    public String getNomTipoCons() {
        return nomTipoCons;
    }

    public void setNomTipoCons(String nomTipoCons) {
        this.nomTipoCons = nomTipoCons;
    }
}
