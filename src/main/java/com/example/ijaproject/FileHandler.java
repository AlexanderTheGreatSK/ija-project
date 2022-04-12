package com.example.ijaproject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private File file;

    public FileHandler(String file) {
        this.file = new File(file);
    }

    public FileHandler() {}

    public UMLProject read() throws Exception {
        Scanner scanner = new Scanner(this.file);
        String data = "";

        while (scanner.hasNextLine()) {

            data = data.concat(scanner.nextLine());
        }

        System.out.println(data);
        return new UMLProject("bla");
    }

    private void readable() throws IOException {
        if(this.file.canRead() == false) {
            throw new IOException();
        }
    }


}
