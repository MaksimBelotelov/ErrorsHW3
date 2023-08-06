package org.example.ex02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class Controller {
    private Model fileManager;

    public Controller() {
        this.fileManager = new Model("root");
    }

    public Model getFileManager() {
        return fileManager;
    }

    public static String[] parseCommand(String command) {
        String[] args = command.split("\\s+");
        if(args.length > 1) {
            if (args[1].charAt(args[1].length() - 1) == '/' &&
                    !args[1].equals("/"))
                args[1] = args[1].substring(0, args[1].lastIndexOf('/'));
        }
        return args;
    }

    public void processCommand(String[] parsedCommand) {
        if(parsedCommand != null && !parsedCommand[0].equals("")) {
            switch (parsedCommand[0]) {
                case "exit":
                    System.exit(0);
                case "cd":
                    try {
                        fileManager.cd(parsedCommand[1]);
                    } catch(FileNotFoundException ex) {
                        System.out.println("Ошибка! Такой директории нет.");
                    }
                    break;
                case "ll":
                    try {
                        View.showListOfFiles(fileManager.getListOfFiles());
                    } catch(NullPointerException ex) {
                        System.out.println("Ошибка! Не могу найти папку для отображения.");
                    }
                    break;
                case "help":
                    View.showHelp();
                    break;
                case "copy":
                    if(parsedCommand.length < 3) System.out.println("Неверное количество аргументов у команды\n" +
                            "Правильное использование: copy имя-исходного-файла имя-файла-назначения\n");
                    else {
                        try {
                            fileManager.fileCopy(fileManager.getCurrent() + "/" + parsedCommand[1],
                                    fileManager.getCurrent() + "/" + parsedCommand[2]);
                        } catch (FileAlreadyExistsException ex) {
                            System.out.println("Файл назначения с таким именем уже существует. Сначала удалите файл.");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                case "create":
                    if(parsedCommand.length < 2) System.out.println("Неверное количество аргументов у команды\n" +
                            "Правильное использование: create имя_файла\n");
                    else {
                        try {
                            fileManager.createNewFile(fileManager.getCurrent() + "/" + parsedCommand[1]);
                        } catch (RuntimeException ex) {
                            System.out.println(ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                case "open":
                    if(parsedCommand.length < 2) System.out.println("Неверное количество аргументов у команды\n" +
                            "Правильное использование: open имя_файла\n");
                    else {
                        try {
                            fileManager.openFile(fileManager.getCurrent() + "/" + parsedCommand[1]);
                        }catch (FileNotFoundException ex){
                            System.out.println("Файл с именем " + parsedCommand[1] + " не найден.");
                        }catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("Команда не найдена.\nДля просмотра доступных команд наберите help");
            }
        }
    }
}
