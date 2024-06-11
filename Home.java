import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<Integer> failedAttempts = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the New User Authentication System"); // Updated message
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        
        usernames.add(username);
        passwords.add(password);
        failedAttempts.add(0);
        
        System.out.println("Registration successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        int userIndex = usernames.indexOf(username);
        if (userIndex != -1 && failedAttempts.get(userIndex) >= 3) {
            System.out.println("Account locked due to too many failed login attempts.");
            return;
        }

        boolean loggedIn = false;
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username) && passwords.get(i).equals(password)) {
                loggedIn = true;
                failedAttempts.set(i, 0);
                break;
            }
        }
        
        if (loggedIn) {
            System.out.println("Login successful!");
        } else {
            if (userIndex != -1) {
                failedAttempts.set(userIndex, failedAttempts.get(userIndex) + 1);
            }
            System.out.println("Invalid username or password. Please try again.");
        }
    }

}
