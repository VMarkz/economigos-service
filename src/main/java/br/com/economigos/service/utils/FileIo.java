package br.com.economigos.service.utils;

import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIo {

    static File file;

    public static File getFile() {
        return file;
    }

    public static void createFile() {
        try {
            file = new File("./src/main/resources/output-files/filename.csv");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void separarGastosRendas(ListaObj<Renda> rendas, ListaObj<Gasto> gastos) {

        List<List> rendasConteudo = new ArrayList();
        List<List> gastosConteudo = new ArrayList();

        for (int i = 0; i < rendas.getTamanho(); i++) {
            List<String> tempList = new ArrayList<>();
            tempList.add(rendas.getElemento(i).getValor().toString());
            tempList.add(rendas.getElemento(i).getDescricao().toString());
            rendasConteudo.add(tempList);
        }

        for (int i = 0; i < gastos.getTamanho(); i++) {
            List<String> tempList = new ArrayList<>();
            tempList.add(gastos.getElemento(i).getValor().toString());
            tempList.add(gastos.getElemento(i).getDescricao().toString());
            gastosConteudo.add(tempList);
        }

//        rendas.forEach(renda -> {
//            List<String> tempList = new ArrayList<>();
//            tempList.add(renda.getValor().toString());
//            tempList.add(renda.getDescricao().toString());
//            rendasConteudo.add(tempList);
//        });
//        gastos.forEach(gasto -> {
//            List<String> tempList = new ArrayList<>();
//            tempList.add(gasto.getValor().toString());
//            tempList.add(gasto.getDescricao().toString());
//            gastosConteudo.add(tempList);
//        });

        tratarGastosRendas(rendasConteudo,gastosConteudo);
    }

    public static void tratarGastosRendas(List<List> rendasConteudo, List<List> gastosConteudo){

        String csvCorpo = "";

        for (int i = 0; i < rendasConteudo.size() | i < gastosConteudo.size(); i++) {

            csvCorpo = csvCorpo + rendasConteudo.get(i).toString().replace('[',' ').replace(']',';') + ";";
            csvCorpo = csvCorpo + gastosConteudo.get(i).toString().replace('[',' ').replace(']',';') + "\n";

        }

        writeFile(csvCorpo);
    }


    public static File writeFile(String corpo) {
        try {
            FileWriter myWriter = new FileWriter(file.getPath());
            myWriter.write("Rendas;;;Gastos \n " +
                    "Valor;Descriçao;;Valor;Descrição \n"+
                    corpo);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

}
