package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}