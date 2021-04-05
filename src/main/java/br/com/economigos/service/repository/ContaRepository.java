package br.com.economigos.service.repository;

import br.com.economigos.service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}