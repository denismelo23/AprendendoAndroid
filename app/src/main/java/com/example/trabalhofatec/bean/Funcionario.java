package com.example.trabalhofatec.bean;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private Integer idfu;
    private String nome;
    private String email;
    private String telefone;

    public Integer getIdfu() {
        return idfu;
    }

    public void setIdfu(int idfu) {
        this.idfu = idfu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "Cod: "+idfu+ " - " + nome;
    }
}
