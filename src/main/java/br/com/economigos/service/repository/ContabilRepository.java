package br.com.economigos.service.repository;

import br.com.economigos.service.model.Contabil;
import br.com.economigos.service.utils.ListaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContabilRepository extends JpaRepository<Contabil, Long> {
//    @Query(value = "select * from contabil where data_pagamento BETWEEN CURRENT_DATE() :parametro AND CURRENT_DATE()", nativeQuery = true)
//    List<Contabil> findByCurrent(@Param("parametro") int parametro);.

//    @Query(value = "select * from contabil where data_pagamento >= CURRENT_DATE() (?) and (pago = true or recebido = true)", nativeQuery = true)
//    List<Contabil> findByDataPagamento(Integer parametro);

    @Query(value = "select * from contabil where data_pagamento >= CURRENT_DATE() -90 and (pago = true or recebido = true)", nativeQuery = true)
    List<Contabil> findByDataPagamento90();

    @Query(value = "select * from contabil where data_pagamento >= CURRENT_DATE() -60 and (pago = true or recebido = true)", nativeQuery = true)
    List<Contabil> findByDataPagamento60();

    @Query(value = "select * from contabil where data_pagamento >= CURRENT_DATE() -30 and (pago = true or recebido = true)", nativeQuery = true)
    List<Contabil> findByDataPagamento30();

}
