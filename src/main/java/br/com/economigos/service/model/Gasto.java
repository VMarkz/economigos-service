package br.com.economigos.service.model;

import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.utils.converters.Data;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Gasto extends Contabil {

    private Boolean pago;
    @ManyToOne
    private Cartao cartao;
    private Integer parcelas;

    public Gasto(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, Boolean pago, String dataPagamento) {
        super(conta, categoria, valor, descricao, fixo, dataPagamento, "Gasto");
        this.pago = pago;
    }

    public Gasto(Cartao cartao,  Categoria categoria,Double valor, String descricao, Boolean fixo, Boolean pago, String dataPagamento) {
        super(categoria, valor, descricao, fixo, dataPagamento, "Gasto");
        this.pago = pago;
        this.parcelas = 0;
        this.cartao = cartao;
    }

    public Gasto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public static Double getUltimosMeses (String anoMes, GastoRepository gastoRepository, Long idConta){
        Double soma = 0.0;

        List<Gasto> gastosMes01 = gastoRepository.findByDataPagamentoIsStartingWithByConta(anoMes, idConta);
        for (Gasto gasto : gastosMes01) {
            soma += gasto.getValor(); }
        return soma;
    }
//    public Double getValorParcela(Gasto gasto){
//        return gasto.valor/ gasto.parcelas;
//    }
//    public void dividirParcela(Gasto gasto, GastoRepository gastoRepository){
//        Double valorDaParcela = getValorParcela(gasto);
//            for (int i = 0; i < parcelas; i++){
//                LocalDateTime dataConvertida = Data.converterDateTime(gasto.getDataPagamento());
//                String novaData = dataConvertida.plusMonths(i).toString();
//                gastoRepository.save(new Gasto(gasto.getCartao(),gasto.getCategoria(),valorDaParcela,
//                        i+1, gasto.getDescricao(), gasto.getFixo(),
//                        gasto.getPago(), novaData));
//                ;
//            }
//
//    }
}
