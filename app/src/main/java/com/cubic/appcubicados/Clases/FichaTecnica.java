package com.cubic.appcubicados.Clases;

public class FichaTecnica {
    private String marca;
    private String material;
    private String garantia;
    private String contenido;
    private String rendimiento;
    private String tipo;
    private String presentacion;
    private String resistencia;
    private String secadoFinal;
    private String uso;
    private String color;

    public FichaTecnica() {
    }

    public FichaTecnica(String marca, String material, String garantia, String contenido, String rendimiento, String tipo, String presentacion, String resistencia, String secadoFinal) {
        this.marca = marca;
        this.material = material;
        this.garantia = garantia;
        this.contenido = contenido;
        this.rendimiento = rendimiento;
        this.tipo = tipo;
        this.presentacion = presentacion;
        this.resistencia = resistencia;
        this.secadoFinal = secadoFinal;
    }

    public FichaTecnica(String marca, String material, String contenido, String uso, String color) {
        this.marca = marca;
        this.material = material;
        this.contenido = contenido;
        this.uso = uso;
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getResistencia() {
        return resistencia;
    }

    public void setResistencia(String resistencia) {
        this.resistencia = resistencia;
    }

    public String getSecadoFinal() {
        return secadoFinal;
    }

    public void setSecadoFinal(String secadoFinal) {
        this.secadoFinal = secadoFinal;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
