package br.com.economigos.service.form;

import br.com.economigos.service.model.Contato;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContatoForm {

    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
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

    public Contato converter() {
        return new Contato(this.email, this.mensagem);
    }
}
