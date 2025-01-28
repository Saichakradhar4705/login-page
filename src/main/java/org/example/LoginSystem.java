package org.example;

import java.util.HashMap;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class AuthenticationSystem {
    private HashMap<String, User> users = new HashMap<>();
    private User loggedInUser = null;

    // Register a new user
    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Error: Username already exists.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("Registration successful!");
        }
    }

    // Log in an existing user
    public void login(String username, String password) {
        if (loggedInUser != null) {
            System.out.println("Error: Already logged in as " + loggedInUser.getUsername());
            return;
        }

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Error: Invalid username or password.");
        }
    }

    // Log out the currently logged-in user
    public void logout() {
        if (loggedInUser == null) {
            System.out.println("Error: No user is currently logged in.");
        } else {
            System.out.println("Logout successful! Goodbye, " + loggedInUser.getUsername() + "!");
            loggedInUser = null;
        }
    }
}

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationSystem authSystem = new AuthenticationSystem();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Register
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    authSystem.register(regUsername, regPassword);
                    break;

                case 2: // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    authSystem.login(loginUsername, loginPassword);
                    break;

                case 3: // Logout
                    authSystem.logout();
                    break;

                case 4: // Exit
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
