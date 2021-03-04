package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.model.Contato;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContatoForm implements CommonForm{

    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String mensagem;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public Contato converter() {
        return new Contato(this.email,this.mensagem);
    }
}
