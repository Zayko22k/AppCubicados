package com.cubic.appcubicados.Clases;

public class InmuebleClass {
    private int idInmueble;
    private String nomInmueble;

    public InmuebleClass() {
    }

    public InmuebleClass(int idInmueble, String nomInmueble) {
        this.idInmueble = idInmueble;
        this.nomInmueble = nomInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getNomInmueble() {
        return nomInmueble;
    }

    public void setNomInmueble(String nomInmueble) {
        this.nomInmueble = nomInmueble;
    }
}
