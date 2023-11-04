import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, Account> accounts;
    private Account currentAccount;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the ATM!");
        boolean running = true;

        while (running) {
            System.out.print("Enter your account number (or 'exit' to quit): ");
            String accountNumber = scanner.nextLine();

            if (accountNumber.equalsIgnoreCase("exit")) {
                running = false;
                continue;
            }

            if (accounts.containsKey(accountNumber)) {
                currentAccount = accounts.get(accountNumber);
                login();
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (currentAccount.authenticate(pin)) {
            System.out.println("Login successful.");
            showMenu();
        } else {
            System.out.println("Incorrect PIN. Please try again.");
        }
    }

    private void showMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("Select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    currentAccount.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    currentAccount.withdraw(amount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: $");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    currentAccount.deposit(amount);
                    break;
                case 4:
                    loggedIn = false;
                    currentAccount = null;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addAccount(String accountNumber, String pin, double balance) {
        accounts.put(accountNumber, new Account(accountNumber, pin, balance));
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        // Add sample accounts (replace with a database or file-based storage for a real system).
        atm.addAccount("123456", "1234", 1000.0);
        atm.addAccount("987654", "5678", 2500.0);

        atm.run();
    }
}

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(String enteredPin) {
        return enteredPin.equals(pin);
    }

    public void checkBalance() {
        System.out.println("Your balance is: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}