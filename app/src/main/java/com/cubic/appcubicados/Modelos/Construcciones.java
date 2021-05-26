package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

public class Construcciones {

    @SerializedName("idConstrucciones")
    private int idConstrucciones;
    @SerializedName("nomConstr")
    private String nomConstr;
    @SerializedName("TipoConstruccion_idTipoConstruccion")
    private int TipoConstruccion_idTipoConstruccion;

    public int getIdConstrucciones() {
        return idConstrucciones;
    }

    public void setIdConstrucciones(int idConstrucciones) {
        this.idConstrucciones = idConstrucciones;
    }

    public String getNomConstr() {
        return nomConstr;
    }

    public void setNomConstr(String nomConstr) {
        this.nomConstr = nomConstr;
    }

    public int getTipoConstruccion_idTipoConstruccion() {
        return TipoConstruccion_idTipoConstruccion;
    }

    public void setTipoConstruccion_idTipoConstruccion(int tipoConstruccion_idTipoConstruccion) {
        TipoConstruccion_idTipoConstruccion = tipoConstruccion_idTipoConstruccion;
    }
}
