package br.com.economigos.service.repository;

import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RendaRepository extends JpaRepository<Renda, Long> {

    @Query("SELECT r FROM Renda r WHERE r.recebido = true AND conta_id = ?2 AND r.dataPagamento LIKE ?1%")
    List<Renda> findByDataPagamentoIsStartingWith(String anoMes, Long idConta);

}
