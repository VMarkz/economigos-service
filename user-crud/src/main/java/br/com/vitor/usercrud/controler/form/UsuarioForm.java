package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.controler.dto.UsuarioDto;
import br.com.vitor.usercrud.model.Usuario;
import br.com.vitor.usercrud.repository.UsuarioRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty @Size(min = 5)
    private String nome;
    @NotNull @Size(min = 8)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(this.email, this.nome, this.senha);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getOne(id);

        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);

        return usuario;
    }

}
