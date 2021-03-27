package br.com.vitor.usercrud.utils;

import java.util.ArrayList;
import java.util.List;

public class DocumentoLayout {

    private List<List> tipos;
    private List<List> rendas;
    private List<List> gastos;

    public DocumentoLayout() {
        this.tipos = new ArrayList<>();
        this.rendas= new ArrayList<>();
        this.gastos= new ArrayList<>();
    }

    public List<List> getTipos() {
        return tipos;
    }

    public void setTipos(List<List> tipos) {
        this.tipos = tipos;
    }

    public List<List> getRendas() {
        return rendas;
    }

    public void setRendas(List<List> rendas) {
        this.rendas = rendas;
    }

    public List<List> getGastos() {
        return gastos;
    }

    public void setGastos(List<List> gastos) {
        this.gastos = gastos;
    }

    public List<List> adcionaRendasConteudo(List<String> rendasConteudo){
        rendas.add(rendasConteudo);
        return tipos;
    }

    public List<List> adcionaGastosConteudo(List<String> gastosConteudo){
        gastos.add(gastosConteudo);
        return tipos;
    }

    public List<List> adcionaTipos(List<List> rendas, List<List> gastos){
        tipos.add(rendas);
        tipos.add(gastos);
        return tipos;
    }

}
