package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ContaForm implements CommonForm{

    @NotNull
    private String banco;
    @NotNull
    private String numeroConta;
    @NotNull
    private String descricao;
    @NotNull
    private String apelido;
    @NotNull
    private String usuario;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Conta converter(UsuarioRepository usuarioRepository) {
        List<Usuario> usuario = usuarioRepository.findByEmail(this.usuario);
        return (new Conta(usuario.get(0), this.banco, this.numeroConta, this.descricao, this.apelido));
    }

    public Conta atualizar(Long id, ContaRepository contaRepository){
        Conta conta = contaRepository.getOne(id);

        conta.setApelido(this.apelido);
        conta.setBanco(this.banco);
        conta.setDescricao(this.descricao);
        conta.setNumeroConta(this.numeroConta);

        return conta;
    }

}
