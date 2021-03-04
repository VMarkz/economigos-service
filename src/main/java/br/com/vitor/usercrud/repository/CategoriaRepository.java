package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
