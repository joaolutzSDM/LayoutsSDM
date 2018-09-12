package br.edu.ifsp.scl.sdm.layoutssdm;

import java.io.Serializable;

public class Contato implements Serializable {

    private String email;

    private String telefone;

    private int tipoTelefone;


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

    public int getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(int tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

}
