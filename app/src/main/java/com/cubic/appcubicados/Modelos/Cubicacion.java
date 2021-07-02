package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cubicacion implements Serializable {

    @SerializedName("idCubica")
    private int idCubica;
    @SerializedName("area")
    private double area;
    @SerializedName("profundidad")
    private double profundidad;
    @SerializedName("ancho")
    private  double ancho;
    @SerializedName("volumen")
    private double volumen;
    @SerializedName("m3")
    private double m3;
    @SerializedName("dosificacion")
    private  int dosificacion;
    @SerializedName("grava")
    private double grava;
    @SerializedName("arena")
    private double arena;
    @SerializedName("agua")
    private  double agua;
    @SerializedName("largo")
    private double largo;
    @SerializedName("cantidadSacos")
    private  int cantidadSacos;
    @SerializedName("Inmueble_idInmueble")
    private int Inmueble_idInmueble;
    @SerializedName("Construcciones_idConstrucciones")
    private int Construcciones_idConstrucciones;

    public int getIdCubica() {
        return idCubica;
    }

    public void setIdCubica(int idCubica) {
        this.idCubica = idCubica;
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

    public int getCantidadSacos() {
        return cantidadSacos;
    }

    public void setCantidadSacos(int cantidadSacos) {
        this.cantidadSacos = cantidadSacos;
    }

    public int getInmueble_idInmueble() {
        return Inmueble_idInmueble;
    }

    public void setInmueble_idInmueble(int inmueble_idInmueble) {
        Inmueble_idInmueble = inmueble_idInmueble;
    }

    public int getConstrucciones_idConstrucciones() {
        return Construcciones_idConstrucciones;
    }

    public void setConstrucciones_idConstrucciones(int construcciones_idConstrucciones) {
        Construcciones_idConstrucciones = construcciones_idConstrucciones;
    }
}
