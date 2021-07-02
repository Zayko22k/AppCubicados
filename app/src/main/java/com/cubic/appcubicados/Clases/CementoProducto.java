package com.cubic.appcubicados.Clases;

import java.io.Serializable;

public class CementoProducto implements Serializable {

    private String urlTienda;
    private String imgUrl;
    private String marca;
    private String descripcion;
    private String titulo;
    private String precio;
    private String despacho;
    private String retiro;
    private String idProducto;
    private String stock;

    public CementoProducto() {
    }
    public CementoProducto(String imgUrl, String marca, String descripcion, String precio, String idProducto, String stock) {
        this.imgUrl = imgUrl;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idProducto = idProducto;
        this.stock = stock;
    }


    public CementoProducto(String urlTienda ,String imgUrl, String marca, String descripcion, String precio, String despacho, String retiro, String idProducto) {
        this.urlTienda = urlTienda;
        this.imgUrl = imgUrl;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.despacho = despacho;
        this.retiro = retiro;
        this.idProducto = idProducto;
    }

    public String getUrlTienda() {
        return urlTienda;
    }

    public void setUrlTienda(String urlTienda) {
        this.urlTienda = urlTienda;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
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
}
