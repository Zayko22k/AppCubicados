package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class Material implements Serializable {

    @SerializedName("idMaterial")
    private int idMaterial;
    @SerializedName("imagenMaterial")
    private String imagenMaterial;
    @SerializedName("descripcionMaterial")
    private String descripcionMaterial;
    @SerializedName("marcaMaterial")
    private  String marcaMaterial;
    @SerializedName("precio")
    private BigDecimal precio;
    @SerializedName("despacho")
    private  String despacho;
    @SerializedName("retiro")
    private String retiro;
    @SerializedName("tienda_idTienda")
    private int tienda_idTienda;

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getImagenMaterial() {
        return imagenMaterial;
    }

    public void setImagenMaterial(String imagenMaterial) {
        this.imagenMaterial = imagenMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public String getMarcaMaterial() {
        return marcaMaterial;
    }

    public void setMarcaMaterial(String marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
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
