package br.com.economigos.service.repository;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByCategoria(String categoria);
}