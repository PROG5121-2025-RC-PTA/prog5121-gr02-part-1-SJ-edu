/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.userinput;

/**
 *
 * @author onxum
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInput {
    private String username;
    private String password;
    private String phoneNumber;

    // Constructor to initialize user details
    public UserInput(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Check if the username contains an underscore and is no longer than 5 characters
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validate username
    public static boolean isValidUsername(String username) {
        return checkUserName(username);
    }

    // Check password complexity
    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[@#$%^&+=!].*");
    }

    // Validate password
    public static boolean isValidPassword(String password) {
        return checkPasswordComplexity(password);
    }

    // Check South African phone number format and length
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        String phonePattern = "^\\+27\\d{9}$"; // Format for South African phone numbers with international dialing code
        return Pattern.matches(phonePattern, phoneNumber);
    }

    // Validate South African phone number
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return checkCellPhoneNumber(phoneNumber);
    }

    // Register user with validation and return registration message
    public String registerUser() {
        if (!isValidUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!isValidPassword(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a speacial character.";
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }
        return "User has been registered successfully.";
    }

    // Login user and return status
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Return login status message
    public String returnLoginStatus() {
        return "Login successful! Welcome, " + username + ".";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Registration Process
        System.out.println("=== Registration ===");

        // Enter and validate username
        System.out.print("Enter a username (must contain an underscore and be no more than 5 characters long): ");
        String username = scanner.nextLine();
        
        // Enter and validate password
        System.out.print("Enter a password (at least 8 characters, must contain 1 uppercase letter, 1 number, and 1 special character): ");
        String password = scanner.nextLine();
        
        // Enter and validate phone number
        System.out.print("Enter your South African cell phone number (with international code, e.g., +27812345678): ");
        String phoneNumber = scanner.nextLine();

        // Create the user account
        UserInput user = new UserInput(username, password, phoneNumber);
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);
        
        if (!registrationMessage.equals("User has been registered successfully.")) {
            scanner.close();
            return; // End program if registration fails
        }

        // Login Process
        System.out.println("\n=== Login ===");
        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        if (user.loginUser(loginUsername, loginPassword)) {
            System.out.println(user.returnLoginStatus());
        } else {
            System.out.println("Login failed! Incorrect username or password.");
        }

        scanner.close();
    }
}