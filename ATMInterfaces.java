import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMInterfaces {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Double> balances = new HashMap<>();
    private static Map<String, StringBuilder> transactionHistory = new HashMap<>();

    public static void main(String[] args) {
        // initialize the users id and pins
        users.put("User1", "4012");
        users.put("User2", "2011");
        // initialize users initial balances
        balances.put("User1", 5000.0);
        balances.put("User2", 4000.0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcom to the ATM");
        System.out.println("Entet your user id");

        String userId = scanner.nextLine();

        // checks users existed or not
        if (!users.containsKey(userId)) {
            System.out.println("Invalid user id.");
            return;
        }
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        // Verify PIN
        if (!users.get(userId).equals(pin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }
        System.out.println("Successful login | \n");

        // ATM functionalities
        while (true) {
            System.out.println("ATM Menu");
            System.out.println("1. View Balance");
            System.out.println("2. Withdrew");
            System.out.println("3. Deposite");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quite");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewBalance(userId);
                    break;
                case 2:

                    withdraw(userId);
                    break;
                case 3:
                    deposit(userId);
                    break;
                case 4:
                    transfer(userId);
                    break;
                case 5:
                    checkTransactionHistory(userId);
                    break;
                case 6:
                    System.out.println("Thank you for using ATM");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");

            }
            System.out.println();
        }


    }

    private static void viewBalance(String userId) {
        double balance = balances.get(userId);
        System.out.println("Your account balance is: $ " + balance);
    }

    private static void withdraw(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        double balance = balances.get(userId);
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            balances.put(userId, balance);
            updateTransactionHistory(userId, "withdrawal -$" + amount);
            System.out.println("Withdrawal successful, Current balance: $" + balance);
        }
    }

    private static void deposit(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount to deposit: $ ");
        double amount = scanner.nextDouble();
        double balance = balances.get(userId);
        balance += amount;
        balances.put(userId, balance); // Corrected line
        updateTransactionHistory(userId, "Deposit: +" + amount);
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    private static void transfer(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's User ID: ");
        String recipientId = scanner.nextLine();

        if (!users.containsKey(recipientId)) {
            System.out.println("Invalid recipient User ID.");
            return;
        }

        double senderBalance = balances.get(userId);
        double recipientBalance = balances.get(recipientId);

        System.out.print("Enter the amount to transfer: $");
        double amount = scanner.nextDouble();

        if (amount > senderBalance) {
            System.out.println("Insufficient balance for the transfer.");
        } else {
            senderBalance -= amount;
            recipientBalance += amount;
            balances.put(userId, senderBalance);
            balances.put(recipientId, recipientBalance);

            updateTransactionHistory(userId, "Transfer to " + recipientId + ": -$" + amount);
            updateTransactionHistory(recipientId, "Transfer from " + userId + ": +$" + amount);

            System.out.println("Transfer successful. Your current balance: $" + senderBalance);
        }
    }

    private static void checkTransactionHistory(String userId) {
        StringBuilder history = transactionHistory.getOrDefault(userId, new StringBuilder("No transactions yet."));
        System.out.println("Transaction History:");
        System.out.println(history);
    }


    private static void updateTransactionHistory(String userId , String transaction) {
        StringBuilder history = transactionHistory.getOrDefault(userId, new StringBuilder());
        history.append(transaction).append("\n");
        transactionHistory.put(userId, history);
    }

}


