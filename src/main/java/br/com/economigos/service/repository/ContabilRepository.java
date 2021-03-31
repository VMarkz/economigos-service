package br.com.vitor.usercrud.repository;

import br.com.vitor.usercrud.model.Contabil;
import br.com.vitor.usercrud.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContabilRepository extends JpaRepository<Contabil, Long> {
}
