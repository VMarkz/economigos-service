package br.com.economigos.service.repository;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByCategoria(String categoria);

    List<Categoria> findAllByTipo(String tipo);
}