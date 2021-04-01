package br.com.economigos.service.repository;

import br.com.economigos.service.model.Renda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendaRepository extends JpaRepository<Renda, Long> {
}
