package com.passwordvault;


import java.util.Scanner;

public class PasswordVaultApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VaultManager manager = new VaultManager();

        System.out.println("\n*** Welcome to PassVault ***");

        boolean running = true;
        while(running){
            System.out.println("\n1. Add new account details");
            System.out.println("2. View all accounts");
            System.out.println("3. Search account details");
            System.out.println("4. Delete account details");
            System.out.println("5. Exit");

            System.out.println("Choose an option from above: ");
            String choice = sc.nextLine();

            switch(choice){
                case "1":
                    System.out.println("Enter platform name: ");
                    String platform = sc.nextLine();
                    System.out.println("Enter username/email: ");
                    String username = sc.nextLine();
                    System.out.println("Enter password: ");
                    String password = sc.nextLine();

                    PasswordEntry entry = new PasswordEntry(platform,username,password);
                    manager.addEntry(entry);
                    break;

                case "2":
                    manager.viewAllEntries();
                    break;

                case "3":
                    System.out.println("Enter platform name: ");
                    String searchPlatform = sc.nextLine();
                    manager.searchEntry(searchPlatform);
                    break;

                case "4":
                    System.out.println("Enter platform name to delete: ");
                    String deletePlatform = sc.nextLine();
                    manager.deleteEntry(deletePlatform);
                    break;

                case "5":
                    System.out.println("Thank you for using PassVault!");
                    manager.saveToFile();
                    running = false;
                    break;

                default:
                        System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}