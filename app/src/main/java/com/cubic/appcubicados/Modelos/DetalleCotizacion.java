package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class DetalleCotizacion implements Serializable {

    @SerializedName("idDetalleCoti")
    private int idDetalleCoti;
    @SerializedName("nombreCoti")
    private String nombreCoti;
    @SerializedName("total")
    private BigDecimal total;
    //Cubicacion
    @SerializedName("cubicacion_idCubica")
    private  int cubicacion_idCubica;
    @SerializedName("area")
    private double area;
    @SerializedName("profundidad")
    private double profundidad;
    @SerializedName("ancho")
    private double ancho;
    @SerializedName("volumen")
    private double volumen;
    @SerializedName("m3")
    private double m3;
    @SerializedName("dosificacion")
    private int dosificacion;
    @SerializedName("grava")
    private double grava;
    @SerializedName("arena")
    private double arena;
    @SerializedName("agua")
    private double agua;
    @SerializedName("largo")
    private double largo;
    @SerializedName("cantidadSacos")
    private int cantidadSacos;
    //Inmueble
    @SerializedName("Inmueble_idInmueble")
    private int Inmueble_idInmueble;
    @SerializedName("nomInmueble")
    private String nomInmueble;
    //Construcciones
    @SerializedName("Construcciones_idConstrucciones")
    private int Construcciones_idConstrucciones;
    @SerializedName("nomConstr")
    private String nomConstr;
    //Tipo construccion
    @SerializedName("TipoConstruccion_idTipoConstruccion")
    private int TipoConstruccion_idTipoConstruccion;
    @SerializedName("nomTipoCons")
    private String nomTipoCons;
    //Cemento
    @SerializedName("cemento_idCemento")
    private  int cemento_idCemento;
    @SerializedName("imagenCemento")
    private String imagenCemento;
    @SerializedName("descripcionCemento")
    private String descripcioncemento;
    @SerializedName("marcaCemento")
    private String marcaCemento;
    @SerializedName("precio")
    private  BigDecimal precio;
    @SerializedName("despacho")
    private String despacho;
    @SerializedName("retiro")
    private String retiro;
    @SerializedName("tienda_idTienda")
    private int tienda_idTienda;
    @SerializedName("nomTienda")
    private String nomTienda;
    @SerializedName("users_id")
    private int users_id;
    @SerializedName("created_at")
    private Timestamp created_at;
    @SerializedName("updated_at")
    private Timestamp updated_at;

    public int getIdDetalleCoti() {
        return idDetalleCoti;
    }

    public void setIdDetalleCoti(int idDetalleCoti) {
        this.idDetalleCoti = idDetalleCoti;
    }

    public String getNombreCoti() {
        return nombreCoti;
    }

    public void setNombreCoti(String nombreCoti) {
        this.nombreCoti = nombreCoti;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getCubicacion_idCubica() {
        return cubicacion_idCubica;
    }

    public void setCubicacion_idCubica(int cubicacion_idCubica) {
        this.cubicacion_idCubica = cubicacion_idCubica;
    }

    public int getCemento_idCemento() {
        return cemento_idCemento;
    }

    public void setCemento_idCemento(int cemento_idCemento) {
        this.cemento_idCemento = cemento_idCemento;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    //Get de los join

    public double getArea() {
        return area;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public double getAncho() {
        return ancho;
    }

    public double getVolumen() {
        return volumen;
    }

    public double getM3() {
        return m3;
    }

    public int getDosificacion() {
        return dosificacion;
    }

    public double getGrava() {
        return grava;
    }

    public double getArena() {
        return arena;
    }

    public double getAgua() {
        return agua;
    }

    public double getLargo() {
        return largo;
    }

    public int getCantidadSacos() {
        return cantidadSacos;
    }

    public int getInmueble_idInmueble() {
        return Inmueble_idInmueble;
    }

    public String getNomInmueble() {
        return nomInmueble;
    }

    public int getConstrucciones_idConstrucciones() {
        return Construcciones_idConstrucciones;
    }

    public String getNomConstr() {
        return nomConstr;
    }

    public int getTipoConstruccion_idTipoConstruccion() {
        return TipoConstruccion_idTipoConstruccion;
    }

    public String getNomTipoCons() {
        return nomTipoCons;
    }

    public String getImagenCemento() {
        return imagenCemento;
    }

    public String getDescripcioncemento() {
        return descripcioncemento;
    }

    public String getMarcaCemento() {
        return marcaCemento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getDespacho() {
        return despacho;
    }

    public String getRetiro() {
        return retiro;
    }

    public int getTienda_idTienda() {
        return tienda_idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }
}
