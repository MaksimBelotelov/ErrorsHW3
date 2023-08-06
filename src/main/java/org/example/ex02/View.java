package org.example.ex02;

import java.io.File;
import java.util.Scanner;

public class View {
    public static void start() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Добро пожаловать в файловый менеджер!");
        showHelp();
    }

    public static void showHelp() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Список доступных команд: ");
        System.out.printf("%-17s%s", "help", "- справка по доступным командам\n");
        System.out.printf("%-17s%s", "open имя-файла", "- просмотреть содержимое файла имя-файла\n");
        System.out.printf("%-17s%s", "ll имя-каталога", "- просмотреть содержимое каталога\n");
        System.out.printf("%-17s%s", "cd имя-каталога", "- перейти в каталог имя-каталога\n");
        System.out.printf("%-17s%s", "copy имя1 имя2", "- копировать файл имя1 в файл имя2\n");
        System.out.printf("%-17s%s", "create имя-файла", "- создать файл имя-файла\n");
        System.out.printf("%-17s%s", "exit", "- выйти из программы\n");
        System.out.println("----------------------------------------------------------------------");
    }

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().strip().toLowerCase();
    }

    public static void showListOfFiles(File[] list) {
        System.out.printf("%-20s%-20s%s\n", "Name", "Size(Bytes)", "Type");
        System.out.println("----------------------------------------------------------------");
        for(File file: list) {
            System.out.printf("%-20s%-20s%-4s",
                    file.getName(),
                    file.isDirectory() ? "-" : file.length(),
                    file.isDirectory()? "Dir" : "File");
            System.out.println();
        }
    }
}

