package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.model.Usuario;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioLoginForm {

    @NotNull
    @NotEmpty
    private String email;
    @NotNull @Size(min = 8)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
