package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

public class RespuestAsistencia implements Serializable {


    @SerializedName("idRespuestAsistencia")
    private int idRespuestAsistencia;
    @SerializedName("respuesta")
    private String respuesta;
    @SerializedName("created_at")
    private Timestamp created_at;
    @SerializedName("updated_at")
    private Timestamp updated_at;
    @SerializedName("Asistencia_idAsistencia")
    private int Asistencia_idAsistencia;
    @SerializedName("consulta")
    private String consulta;
    @SerializedName("email")
    private String email;
    @SerializedName("users_id")
    private int users_id;
    @SerializedName("visto")
    private short visto;
    @SerializedName("name")
    private String name;
    @SerializedName("Region_idRegion")
    private int Region_idRegion;
    @SerializedName("nomRegion")
    private String nomRegion;


    public short getVisto() {
        return visto;
    }

    public void setVisto(short visto) {
        this.visto = visto;
    }

    public int getIdRespuestAsistencia() {
        return idRespuestAsistencia;
    }

    public void setIdRespuestAsistencia(int idRespuestAsistencia) {
        this.idRespuestAsistencia = idRespuestAsistencia;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    public int getAsistencia_idAsistencia() {
        return Asistencia_idAsistencia;
    }

    public void setAsistencia_idAsistencia(int asistencia_idAsistencia) {
        Asistencia_idAsistencia = asistencia_idAsistencia;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion_idRegion() {
        return Region_idRegion;
    }

    public void setRegion_idRegion(int region_idRegion) {
        Region_idRegion = region_idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }
}
