package swingy.dot.com.controller;

import swingy.dot.com.model.color.Color;

public class SwingyRunner {

    public static void main(String[] args) {

        if ((args.length == 1) && (args[0].equalsIgnoreCase("console") || args[0].equalsIgnoreCase("gui"))) {
            SwingyAppController appController = new SwingyAppController();
            if (args[0].equals("gui")) {
                appController.startGui();
            } else if (args[0].equals("console")) {
                appController.startConsole();
            }
        } else {
            System.out.println(Color.RED + "Error:: The parameters must be 'console' or 'gui'" + Color.RESET);
        }
    }
}
