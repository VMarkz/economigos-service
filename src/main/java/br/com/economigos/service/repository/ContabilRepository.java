package br.com.economigos.service.repository;

import br.com.economigos.service.controler.dto.ContabilUltimasAtividadesDto;
import br.com.economigos.service.model.Contabil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContabilRepository extends JpaRepository<Contabil, Long> {
    @Query(value = "SELECT id, data_pagamento, descricao, valor, tipo FROM GASTO  where conta_id = 1 UNION SELECT " +
            "id, data_pagamento, descricao, valor, tipo FROM RENDA where conta_id = 1 order by data_pagamento desc;",nativeQuery = true)
    List<?> findUltimasAtividades();
}
