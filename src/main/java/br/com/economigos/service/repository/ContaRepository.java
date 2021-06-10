package br.com.economigos.service.repository;

import br.com.economigos.service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE usuario_id = :idUsuario AND c.apelido = :apelido")
    Optional<Conta> findByApelidoAndUsuario(String apelido, Long idUsuario);

    @Query("SELECT c FROM Conta c WHERE usuario_id = :idUsuario")
    List<Conta> findAllByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT c FROM Conta c WHERE usuario_id = :idUsuario AND id = :idConta")
    Optional<Conta> findContaByUsuario(@Param("idConta") Long idConta,
                                       @Param("idUsuario") Long idUsuario);

}