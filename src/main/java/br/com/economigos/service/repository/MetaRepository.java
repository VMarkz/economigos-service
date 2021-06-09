package br.com.economigos.service.repository;

import br.com.economigos.service.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    @Query("SELECT c FROM Meta c WHERE usuario_id = :idUsuario")
    List<Meta> findAllByUsuario(@Param("idUsuario") Long idUsuario);
}
