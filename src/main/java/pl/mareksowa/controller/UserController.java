package pl.mareksowa.controller;

import java.util.Scanner;

public class UserController {

    private Scanner scanner;

    public UserController() {
        scanner = new Scanner(System.in);
    }

    public String getUserInputString(){
        return scanner.nextLine();
    }
}

