package com.microtwitter;

import java.util.Scanner;

public class MicroTwitter {

    public static void main(String[] args) {
        System.out.println("Enter command:");

        Scanner reader = new Scanner(System.in);
        String userInput = reader.next();
        System.out.println("You typed: " + userInput);
    }
}
