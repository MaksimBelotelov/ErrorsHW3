package org.example.ex02;

import java.io.*;
import java.nio.file.Files;

public class Model {
    private final String root;
    private String current;

    public Model(String current) {
        this.root = current;
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }

    public File[] getListOfFiles() {
        File currentFolder = new File(current);
        return currentFolder.listFiles();
    }

    public void fileCopy(String src, String dst) throws IOException, RuntimeException {
        File source = new File(src);
        File destination = new File(dst);
        if (!source.exists()) throw new RuntimeException("Не найден исходный файл для копирования.");
        Files.copy(source.toPath(), destination.toPath());
        System.out.println("Файл " + src + " скопирован в файл " + dst);
    }

    public void createNewFile(String name) throws RuntimeException, IOException {
        File myNewFile = new File(name);
        if (myNewFile.createNewFile())
            System.out.println("Создан новый файл: " + name);
        else
            throw new RuntimeException("Файл с таким именем уже существует");
    }

    public void openFile(String s) throws FileNotFoundException, IOException {
        File file = new File(s);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }

    public void cd(String s) throws FileNotFoundException {
        if (s.equals("root") || s.equals("/"))
            this.current = this.root;
        else if (s.equals("..")) {
            if (!this.current.equals("root"))
                this.current = this.current.substring(0, this.current.lastIndexOf("/"));
        } else {
            File dir = null;
            String path = null;
            if (s.indexOf("root") == 0) {
                path = s;
            } else {
                path = this.current + "/" + s;
            }
            dir = new File(path);
            if (dir.exists())
                this.current = path;
            else
                throw new FileNotFoundException("Такой директории нет");
        }
    }
}
