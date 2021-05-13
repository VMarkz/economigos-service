package br.com.economigos.service.repository;

import br.com.economigos.service.model.Gasto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

    @Query("SELECT g FROM Gasto g WHERE g.pago = true AND conta_id = ?2 AND g.dataPagamento LIKE ?1%")
    List<Gasto> findByDataPagamentoIsStartingWithByConta(String anoMes, Long idConta);


    @Query("SELECT g FROM Gasto g WHERE conta_id = ?1")
    List<Gasto> findGastoByConta(Long idConta);

    @Query("SELECT g FROM Gasto g WHERE cartao_id = ?1")
    List<Gasto> findGastoByCartao(Long idConta);

    @Query("SELECT SUM(valor) FROM Gasto g WHERE cartao_id = ?1 AND data_pagamento > ?2 AND data_pagamento < ?3")
    Double somaGastosCartao (Long idCartao, String data1, String data2);
}
