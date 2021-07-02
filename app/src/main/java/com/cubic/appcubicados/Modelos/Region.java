package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Region implements Serializable {
    @SerializedName("idRegion")
     private int idRegion;
    @SerializedName("nomRegion")
    private String nomRegion;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }
}
