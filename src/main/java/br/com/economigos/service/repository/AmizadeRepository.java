package br.com.economigos.service.repository;

import br.com.economigos.service.model.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {
    @Query("SELECT a FROM Amizade a WHERE a.idUsuario = :idUsuario")
    List<Amizade> findAllByUsuario(@Param("idUsuario") Long idUsuario);

    List<Amizade> deleteAmizadeByIdUsuarioAndIdAmigo(Long idUsuario, Long idAmigo);
}
