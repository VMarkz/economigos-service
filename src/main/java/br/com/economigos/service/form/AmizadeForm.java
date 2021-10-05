package br.com.economigos.service.form;

import br.com.economigos.service.model.Amizade;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class AmizadeForm {

    @NotNull
    @NotEmpty
    private Long idUsuario;
    @NotNull
    @NotEmpty
    private Long idAmigo;

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

    public Amizade converter() {
        return new Amizade(idUsuario, idAmigo);
    }
}
