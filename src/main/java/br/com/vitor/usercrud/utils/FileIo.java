package br.com.vitor.usercrud.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIo {

    static File file;

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

    public static File writeFile() {
        try {
            FileWriter myWriter = new FileWriter(file.getPath());
            myWriter.write("Gasto,Descrição \n" +
                    "R$ 10.00, SEILA");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

}
