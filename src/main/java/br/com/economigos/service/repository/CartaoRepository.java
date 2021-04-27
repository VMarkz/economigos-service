package br.com.economigos.service.repository;

import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    @Query("SELECT c FROM Cartao c WHERE usuario_id = :idUsuario")
    List<Cartao> findAllByUsuario(@Param("idUsuario") Long idUsuario);
}
