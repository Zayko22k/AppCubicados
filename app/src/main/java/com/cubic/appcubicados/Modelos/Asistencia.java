package com.cubic.appcubicados.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class Asistencia implements Serializable {

    @SerializedName("idAsistencia")
    private int idAsistencia;
    @SerializedName("consulta")
    private String consulta;
    @SerializedName("email")
    private String email;
    @SerializedName("created_at")
    private Timestamp created_At;
    @SerializedName("updated_at")
    private  Timestamp updated_at;
    @SerializedName("users_id")
    private BigInteger users_id;
    @SerializedName("Region_idRegion")
    private int Region_idRegion;

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
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

    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public BigInteger getUsers_id() {
        return users_id;
    }

    public void setUsers_id(BigInteger users_id) {
        this.users_id = users_id;
    }

    public int getRegion_idRegion() {
        return Region_idRegion;
    }

    public void setRegion_idRegion(int region_idRegion) {
        Region_idRegion = region_idRegion;
    }
}
