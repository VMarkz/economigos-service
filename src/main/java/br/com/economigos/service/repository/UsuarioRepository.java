package br.com.economigos.service.repository;

import br.com.economigos.service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Query padronizada pelo spring.data
//    List<Usuario> findByUsuario(String usuario);
    // Exemplo de query personalizada
    @Query("SELECT u FROM Usuario u WHERE u.email = :usuarioEmail AND u.senha = :usuarioSenha")
    List<Usuario> findByLogin(@Param("usuarioEmail") String usuarioEmail,
                              @Param("usuarioSenha") String usuarioSenha);

    @Query("SELECT u FROM Usuario u WHERE u.email = :usuarioEmail")
    List<Usuario> findByEmail(@Param("usuarioEmail") String usuarioEmail);


}
