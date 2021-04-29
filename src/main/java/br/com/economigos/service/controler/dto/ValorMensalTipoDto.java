package br.com.economigos.service.controler.dto;

import java.util.List;

public class ValorMensalTipoDto {

    private String tipoContabil;
    private List<ValorMensalDto> valorMensalDtos;

    public ValorMensalTipoDto(String tipoContabil, List<ValorMensalDto> valorMensalDtos) {
        this.tipoContabil = tipoContabil;
        this.valorMensalDtos = valorMensalDtos;
    }

    public String getTipoContabil() {
        return tipoContabil;
    }

    public void setTipoContabil(String tipoContabil) {
        this.tipoContabil = tipoContabil;
    }

    public List<ValorMensalDto> getValorMensalDtos() {
        return valorMensalDtos;
    }

    public void setValorMensalDtos(List<ValorMensalDto> valorMensalDtos) {
        this.valorMensalDtos = valorMensalDtos;
    }
}
