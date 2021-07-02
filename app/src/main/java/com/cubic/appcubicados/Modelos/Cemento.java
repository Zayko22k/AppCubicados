package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cemento implements Serializable {

    @SerializedName("idCemento")
    private int idCemento;
    @SerializedName("imagenCemento")
    private String imagenCemento;
    @SerializedName("descripcionCemento")
    private String descripcionCemento;
    @SerializedName("marcaCemento")
    private  String marcaCemento;
    @SerializedName("precio")
    private BigDecimal precio;
    @SerializedName("despacho")
    private  String despacho;
    @SerializedName("retiro")
    private String retiro;
    @SerializedName("tienda_idTienda")
    private int tienda_idTienda;

    public int getIdCemento() {
        return idCemento;
    }

    public void setIdCemento(int idCemento) {
        this.idCemento = idCemento;
    }

    public String getImagenCemento() {
        return imagenCemento;
    }

    public void setImagenCemento(String imagenCemento) {
        this.imagenCemento = imagenCemento;
    }

    public String getDescripcionCemento() {
        return descripcionCemento;
    }

    public void setDescripcionCemento(String descripcionCemento) {
        this.descripcionCemento = descripcionCemento;
    }

    public String getMarcaCemento() {
        return marcaCemento;
    }

    public void setMarcaCemento(String marcaCemento) {
        this.marcaCemento = marcaCemento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public int getTienda_idTienda() {
        return tienda_idTienda;
    }

    public void setTienda_idTienda(int tienda_idTienda) {
        this.tienda_idTienda = tienda_idTienda;
    }
}
