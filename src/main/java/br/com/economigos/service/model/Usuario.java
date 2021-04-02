package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Entity
public class Usuario implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;
    @OneToMany(mappedBy = "usuario")
    private List<Meta> metas;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
        contas = new ArrayList<>();
        metas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public void update(Observable o, Object arg) {
        String acao = String.valueOf(arg);

        if (o.getClass().equals(Meta.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        } else if (o.getClass().equals(Conta.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        }
    }
}
