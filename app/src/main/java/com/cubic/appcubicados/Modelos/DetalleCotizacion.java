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
    @SerializedName("cantidad")
    private int cantidad;
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
    @SerializedName("material_idMaterial")
    private  int material_idMaterial;
    @SerializedName("imagenMaterial")
    private String imagenMaterial;
    @SerializedName("descripcionMaterial")
    private String descripcionMaterial;
    @SerializedName("marcaMaterial")
    private String marcaMaterial;
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getM3() {
        return m3;
    }

    public void setM3(double m3) {
        this.m3 = m3;
    }

    public int getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(int dosificacion) {
        this.dosificacion = dosificacion;
    }

    public double getGrava() {
        return grava;
    }

    public void setGrava(double grava) {
        this.grava = grava;
    }

    public double getArena() {
        return arena;
    }

    public void setArena(double arena) {
        this.arena = arena;
    }

    public double getAgua() {
        return agua;
    }

    public void setAgua(double agua) {
        this.agua = agua;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getInmueble_idInmueble() {
        return Inmueble_idInmueble;
    }

    public void setInmueble_idInmueble(int inmueble_idInmueble) {
        Inmueble_idInmueble = inmueble_idInmueble;
    }

    public String getNomInmueble() {
        return nomInmueble;
    }

    public void setNomInmueble(String nomInmueble) {
        this.nomInmueble = nomInmueble;
    }

    public int getConstrucciones_idConstrucciones() {
        return Construcciones_idConstrucciones;
    }

    public void setConstrucciones_idConstrucciones(int construcciones_idConstrucciones) {
        Construcciones_idConstrucciones = construcciones_idConstrucciones;
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

    public String getNomTipoCons() {
        return nomTipoCons;
    }

    public void setNomTipoCons(String nomTipoCons) {
        this.nomTipoCons = nomTipoCons;
    }

    public int getMaterial_idMaterial() {
        return material_idMaterial;
    }

    public void setMaterial_idMaterial(int material_idMaterial) {
        this.material_idMaterial = material_idMaterial;
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

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
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
}
