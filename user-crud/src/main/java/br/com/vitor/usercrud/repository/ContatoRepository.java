package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
