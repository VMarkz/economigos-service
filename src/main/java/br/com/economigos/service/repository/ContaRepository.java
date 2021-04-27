package br.com.economigos.service.repository;

import br.com.economigos.service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByApelido(String apelido);

    @Query("SELECT c FROM Conta c WHERE usuario_id = :idUsuario")
    List<Conta> findAllByUsuario(@Param("idUsuario") Long idUsuario);
}