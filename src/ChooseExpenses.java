import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ChooseExpenses{

    public static void printExpensesByCategory(ArrayList<Expense> expenses, String category) {
        System.out.println("Expenses for Category: " + category);
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for category: " + category);
        }
    }

    public static void printExpensesUnderAmount(ArrayList<Expense> expenses, double maxAmount) {
        System.out.println("Expenses under " + maxAmount + ":");
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getAmount() < maxAmount) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found under " + maxAmount);
        }
    }

    public static void printExpensesHigherThanAmount(ArrayList<Expense> expenses, double minAmount) {
        System.out.println("Expenses higher than " + minAmount + ":");
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getAmount() > minAmount) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found higher than " + minAmount);
        }
    }

    public static void printExpensesLaterThanDate(ArrayList<Expense> expenses, String dateString) {
        System.out.println("Expenses later than " + dateString + ":");
        boolean found = false;
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Expense expense : expenses) {
                if (LocalDate.parse(expense.getDate()).isAfter(date)) {
                    System.out.println(expense);
                    found = true;
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }
        if (!found) {
            System.out.println("No expenses found later than " + dateString);
        }
    }

    public static void printExpensesBeforeDate(ArrayList<Expense> expenses, String dateString) {
        System.out.println("Expenses before " + dateString + ":");
        boolean found = false;
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Expense expense : expenses) {
                if (LocalDate.parse(expense.getDate()).isBefore(date)) {
                    System.out.println(expense);
                    found = true;
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }
        if (!found) {
            System.out.println("No expenses found before " + dateString);
        }
    }
}
