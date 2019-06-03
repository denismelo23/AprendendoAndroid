package com.example.trabalhofatec.bean;

import java.io.Serializable;

public class Banho implements Serializable {
    private Integer idban;
    private Integer idfub;
    private Integer idanib;
    private String data;
    private String hora;

    public Integer getIdban() {
        return idban;
    }

    public void setIdban(int idban) {
        this.idban = idban;
    }

    public Integer getIdfub() {
        return idfub;
    }

    public void setIdfub(int idfub) {
        this.idfub = idfub;
    }

    public Integer getIdanib() {
        return idanib;
    }

    public void setIdanib(int idanib) {
        this.idanib = idanib;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString(){
        return "{ Cod. Serv: "+ idban+ " |Funcionário: "+idfub+" |Animal: "+idanib+" |Data: "+data+" |Hora: "+hora+" }";
    }
}
