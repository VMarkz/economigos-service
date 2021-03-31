package br.com.economigos.service.repository;

import br.com.economigos.service.model.Contabil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContabilRepository extends JpaRepository<Contabil, Long> {
}
