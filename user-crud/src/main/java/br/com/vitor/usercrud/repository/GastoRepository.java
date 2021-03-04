package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
}
