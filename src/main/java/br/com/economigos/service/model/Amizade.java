package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Amizade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private Long idAmigo;
    private LocalDateTime dataAmizade;

    public Amizade() {
    }

    public Amizade(Long idUsuario, Long idAmigo) {
        this.idUsuario = idUsuario;
        this.idAmigo = idAmigo;
        this.dataAmizade = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(Long idAmigo) {
        this.idAmigo = idAmigo;
    }

    public LocalDateTime getDataAmizade() {
        return dataAmizade;
    }

    public void setDataAmizade(LocalDateTime dataAmizade) {
        this.dataAmizade = dataAmizade;
    }
}
