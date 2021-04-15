package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class ContaForm implements CommonForm{

    @NotNull @NotEmpty
    private String banco;
    @NotNull @NotEmpty
    private String numeroConta;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private Long idUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta converter(UsuarioRepository usuarioRepository) {
            Usuario usuario = usuarioRepository.getOne(this.idUsuario);
            return (new Conta(usuario, this.banco, this.numeroConta, this.descricao, this.nome));
    }

    public Conta atualizar(Long id, ContaRepository contaRepository){
        Conta conta = contaRepository.getOne(id);

        conta.setNome(this.nome);
        conta.setBanco(this.banco);
        conta.setDescricao(this.descricao);
        conta.setNumeroConta(this.numeroConta);

        return conta;
    }

}
