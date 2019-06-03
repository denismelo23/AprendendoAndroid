package com.example.trabalhofatec.bean;

import java.io.Serializable;

public class Animal implements Serializable {
    private Integer idani;
    private String nome;
    private String tipo;
    private String dono;

    public Integer getIdani() {
        return idani;
    }

    public void setIdani(int idani) {
        this.idani = idani;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    @Override
    public String toString(){ return "{Cod: "+idani+"   |Nome: "+ nome + "   |Tipo: "+tipo+" }";}
}
