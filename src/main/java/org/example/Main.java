package org.example;

import org.example.ex02.Controller;
import org.example.ex02.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        View.start();
        String[] parsedCommand = null;
        do {
            System.out.print(controller.getFileManager().getCurrent() + ":");
            String currentCommand = View.readCommand();
            parsedCommand = Controller.parseCommand(currentCommand);
            controller.processCommand(parsedCommand);
        } while (!parsedCommand[0].equals("exit"));
    }
}