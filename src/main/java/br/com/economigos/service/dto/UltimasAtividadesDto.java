package br.com.economigos.service.dto;

import java.util.List;

public class UltimasAtividadesDto {

    private String fonte;
    private Long id;
    private List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos;

    public UltimasAtividadesDto(String fonte, Long id, List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos) {
        this.fonte = fonte;
        this.id = id;
        this.contabilUltimasAtividadesDtos = contabilUltimasAtividadesDtos;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ContabilUltimasAtividadesDto> getContabilUltimasAtividadesDtos() {
        return contabilUltimasAtividadesDtos;
    }

    public void setContabilUltimasAtividadesDtos(List<ContabilUltimasAtividadesDto> contabilUltimasAtividadesDtos) {
        this.contabilUltimasAtividadesDtos = contabilUltimasAtividadesDtos;
    }
}
