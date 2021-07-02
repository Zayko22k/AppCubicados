package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Arriendo implements Serializable {

    @SerializedName("idArriendo")
    private int idArriendo;
    @SerializedName("created_at")
    private Timestamp created_at;
    @SerializedName("updated_at")
    private  Timestamp updated_at;
    @SerializedName("vencido")
    private short vencido;
    @SerializedName("activo")
    private short activo;
    @SerializedName("servicio_idServicio")
    private int servicio_idServicio;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("precio")
    private BigDecimal precio;
    @SerializedName("tipopago_idTipoPago")
    private int tipopago_idTipoPago;
    @SerializedName("users_id")
    private int users_id;

    public int getIdArriendo() {
        return idArriendo;
    }

    public void setIdArriendo(int idArriendo) {
        this.idArriendo = idArriendo;
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

    public short getVencido() {
        return vencido;
    }

    public void setVencido(short vencido) {
        this.vencido = vencido;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public int getServicio_idServicio() {
        return servicio_idServicio;
    }

    public void setServicio_idServicio(int servicio_idServicio) {
        this.servicio_idServicio = servicio_idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getTipopago_idTipoPago() {
        return tipopago_idTipoPago;
    }

    public void setTipopago_idTipoPago(int tipopago_idTipoPago) {
        this.tipopago_idTipoPago = tipopago_idTipoPago;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}
