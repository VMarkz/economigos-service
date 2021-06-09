package br.com.economigos.service.repository;

import br.com.economigos.service.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    @Query("SELECT c FROM Cartao c WHERE usuario_id = :idUsuario")
    List<Cartao> findAllByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT c FROM Cartao c WHERE usuario_id = :idUsuario AND id = :idCartao")
    Optional<Cartao> findCartaoByUsuario(@Param("idCartao") Long idCartao,
                                         @Param("idUsuario") Long idUsuario);
}
