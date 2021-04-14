package br.com.economigos.service.utils;

import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Contabil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.FormatterClosedException;
import java.util.List;

public class GravaArquivo {

    public static void gravaLista(ListaObj<Contabil> lista, String nomeDoArquivo, Boolean csvFile) {
        FileWriter arquivo = null;
        String registro = "";
        String nomeDoArquivoDefault = "extrato";
        boolean crashed = false;

        if (nomeDoArquivo == null) {
            nomeDoArquivo = nomeDoArquivoDefault;
        }
        if (csvFile) {
            nomeDoArquivo += ".csv";

            try {
                for (int i = 0; i < lista.getTamanho(); i++) {
                    Contabil contabil = lista.getElemento(i);
                    registro = String.format
                            ("%4d;%-1s;%9.2f;%-50s;%-10s;%-25s;%-15s%n",
                                    contabil.getId(),
                                    contabil.getTipo(),
                                    contabil.getValor(),
                                    contabil.getDescricao(),
                                    contabil.getDataPagamento(),
                                    contabil.getCategoria(),
                                    contabil.getConta()
                            );
                    gravaRegistro(nomeDoArquivo, registro);
                }
            } catch (FormatterClosedException erro) {
                System.err.println("Erro ao gravar no arquivo");
                crashed = true;
            } finally {
                try {
                    arquivo.close();
                } catch (IOException erro) {
                    System.err.println("Erro ao fechar arquivo.");
                    crashed = true;
                }
                if (crashed)
                    System.exit(1);
            }

        } else {
            nomeDoArquivo += ".txt";
            gravarTxt(nomeDoArquivo, lista);
        }

    }

    public static void gravaRegistro(String nomeArq, String registro) {

        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            if(nomeArq.endsWith(".csv")){
                saida.append(registro);
                saida.close();
            }else{
                saida.append(registro + "\n");
                saida.close();
            }

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }


    public static void gravarTxt(String nomeArq, ListaObj<Contabil> entregas) {

        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00ENTREGAS00189236";
        header += formatter.format(dataDeHoje);
        header += "01";
        gravaRegistro(nomeArq, header);

        for (int i = 0; i < entregas.getTamanho(); i++) {
            Contabil contabil = entregas.getElemento(i);

            corpo += "02";
            corpo += String.format("%04d", contabil.getId());
            corpo += String.format("%-1s", contabil.getTipo());
            corpo += String.format("%9.2f", contabil.getValor());
            corpo += String.format("%-50s", contabil.getDescricao());
            corpo += String.format("%-10s", contabil.getDataPagamento());
            corpo += String.format("%-25s", contabil.getCategoria());
            corpo += String.format("%-15s", contabil.getConta());

            contRegDados++;
            gravaRegistro(nomeArq, corpo);
        }

        trailer += "01";
        trailer += String.format("%05d", contRegDados);
        gravaRegistro(nomeArq, trailer);
    }

}