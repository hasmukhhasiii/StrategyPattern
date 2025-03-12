package org.example.StrategyPattern;

import java.util.Scanner;

public class StudentData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/marklist";
        String user = "root";
        String password = "123456";

        Scanner scanner = new Scanner(System.in);
        StudentSearchContext searchContext = new StudentSearchContext();

        while (true) {
            System.out.println("\nStudent Search Menu");
            System.out.println("1. Search by Admission Number");
            System.out.println("2. Search by Name");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid entry. Please enter a valid option (1, 2, or 3).");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchContext.setSearchStrategy(new SearchByAdmissionNumber());
                    System.out.print("Enter Admission Number: ");
                    break;
                case 2:
                    searchContext.setSearchStrategy(new SearchByName());
                    System.out.print("Enter Student Name: ");
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            searchContext.executeSearch(scanner.nextLine(), url, user, password);
        }
    }
}