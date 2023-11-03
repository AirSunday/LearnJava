package org.example;

import org.example.Service.CommandService;

public class Main {
    public static void main(String[] args) {
        CommandService commandService = new CommandService();   //Сервис используется для обработки ввода команд
        commandService.start();
    }
}