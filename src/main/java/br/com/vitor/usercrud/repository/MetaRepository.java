package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository extends JpaRepository<Meta, Long> {
}
