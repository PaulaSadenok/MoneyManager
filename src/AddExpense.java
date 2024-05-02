import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddExpense {
    public static void addExpense(Scanner scanner, ArrayList<Expense> expenses) {
        String date = "";
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = scanner.next();
            try {
                LocalDate.parse(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        String category = "";
        while (true) {
            System.out.print("Enter category: ");
            category = scanner.next();
            if (!category.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Category cannot be empty.");
            }
        }

        double amount = 0;
        while (true) {
            try {
                System.out.print("Enter amount: ");
                amount = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Amount must be a number.");
                scanner.next();
            }
        }

        expenses.add(new Expense(date, category, amount));
        ExpenseStorage.saveExpenses(expenses);
        System.out.println("Expense added successfully.");
    }
}
