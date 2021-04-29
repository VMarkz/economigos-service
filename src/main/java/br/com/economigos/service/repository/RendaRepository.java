package br.com.economigos.service.repository;

import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendaRepository extends JpaRepository<Renda, Long> {

    List<Gasto> findByDataPagamentoIsStartingWith(String anoMes);

}
