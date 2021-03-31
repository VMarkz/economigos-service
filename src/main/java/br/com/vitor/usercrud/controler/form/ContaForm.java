package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Usuario;
import br.com.vitor.usercrud.repository.ContaRepository;
import br.com.vitor.usercrud.repository.UsuarioRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class ContaForm implements CommonForm{

    @NotNull @NotEmpty
    private String banco;
    @NotNull @NotEmpty
    private String numeroConta;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String apelido;

    @Override
    public Conta converter() {
        return (new Conta(this.banco, this.numeroConta, this.descricao, this.apelido));
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
