package br.com.economigos.service.dto;

import java.util.List;

public class qroFeriasDto {
    private List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos;

    public qroFeriasDto(List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos) {
        this.contabilUltimasAtividadesDtos = contabilUltimasAtividadesDtos;
    }

    public List<ContabilUltimasAtividadesDto> getContabilUltimasAtividadesDtos() {
        return contabilUltimasAtividadesDtos;
    }

    public void setContabilUltimasAtividadesDtos(List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos) {
        this.contabilUltimasAtividadesDtos = contabilUltimasAtividadesDtos;
    }
}
