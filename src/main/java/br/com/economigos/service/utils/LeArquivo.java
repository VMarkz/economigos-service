package br.com.economigos.service.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LeArquivo {


    public static void leExibeArquivo(String nomeDoArquivo) {
        FileReader arquivo = null;
        Scanner entrada = null;
        boolean crashed = false;

        try {
            arquivo = new FileReader(nomeDoArquivo);
            entrada = new Scanner(arquivo).useDelimiter(";|\\n|\\r\\n");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-15s%-20s%-30s%-50s%5s%10s\n","ID ENTREGA","ESTADO","CIDADE","RUA","NUMERO", "DISTANCIA");
            while (entrada.hasNext()) {
                int idEntrega = entrada.nextInt();
                String estado = entrada.next();
                String cidade = entrada.next();
                String rua = entrada.next();
                int numero = entrada.nextInt();
                Double distancia = entrada.nextDouble();
                System.out.printf
                        ("%015d%-20s%-30s%-50s%05d%05.2f\n",
                                idEntrega,estado,cidade,rua,numero,distancia);
            }
        } catch (NoSuchElementException erro) {
            System.err.println("Arquivo com problemas.");
            crashed = true;
        } catch (IllegalStateException erro) {
            System.err.println("Erro na leitura do arquivo.");
            crashed = true;
        } finally {
            entrada.close();
            try {
                arquivo.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                crashed = true;
            }
            if (crashed)
                System.exit(1);
        }
    }

    public static void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String curso, ra, nomeAluno, nomeDisciplina;
        double media;
        int qtdFalta, contRegistro=0;

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                    int periodoLetivo= Integer.parseInt(registro.substring(6,11));
                    System.out.println("Período letivo: " + periodoLetivo);
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(11,30));
                    System.out.println("Versão do layout: " + registro.substring(30,32));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-5s %-8s %-50s %-40s %5s %6s\n", "CURSO","RA","NOME DO ALUNO","DISCIPLINA",
                                "MÉDIA", "FALTAS");

                    }

                    curso = registro.substring(2,7);
                    ra = registro.substring(7,15);
                    nomeAluno = registro.substring(15,65);
                    nomeDisciplina = registro.substring(65,105);
                    media = Double.parseDouble(registro.substring(105,110).replace(',','.'));
                    qtdFalta = Integer.parseInt(registro.substring(110,113));

                    System.out.printf("%-5s %-8s %-50s %-40s %5.2f %6d\n", curso, ra, nomeAluno,nomeDisciplina,
                            media, qtdFalta);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }
}
