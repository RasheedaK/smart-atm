package org.assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATMClient atmClient = new ATMClient(new ATM(), new Bank());

        start(atmClient);
    }

    public static void start(ATMClient atmClient) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Smart ATM started. Type 'exit' to quit.");

        while (true) {
            System.out.print("Enter command: ");
            input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting program.");
                break;  // Exit the loop and end the program
            }

            System.out.println(atmClient.process(input));
        }

        scanner.close();
    }
}