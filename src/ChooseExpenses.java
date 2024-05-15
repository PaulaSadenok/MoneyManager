import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ChooseExpenses {

    public static ArrayList<Expense> sortExpensesByCategory(ArrayList<Expense> expenses, String category) {
        ArrayList<Expense> sortedExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                sortedExpenses.add(expense);
            }
        }
        return sortedExpenses;
    }

    public static ArrayList<Expense> sortExpensesUnderAmount(ArrayList<Expense> expenses, double maxAmount) {
        ArrayList<Expense> sortedExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getAmount() < maxAmount) {
                sortedExpenses.add(expense);
            }
        }
        return sortedExpenses;
    }

    public static ArrayList<Expense> sortExpensesHigherThanAmount(ArrayList<Expense> expenses, double minAmount) {
        ArrayList<Expense> sortedExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getAmount() > minAmount) {
                sortedExpenses.add(expense);
            }
        }
        return sortedExpenses;
    }

    public static ArrayList<Expense> sortExpensesLaterThanDate(ArrayList<Expense> expenses, String dateString) {
        ArrayList<Expense> sortedExpenses = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Expense expense : expenses) {
                if (LocalDate.parse(expense.getDate()).isAfter(date)) {
                    sortedExpenses.add(expense);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return sortedExpenses;
        }
        return sortedExpenses;
    }

    public static ArrayList<Expense> sortExpensesBeforeDate(ArrayList<Expense> expenses, String dateString) {
        ArrayList<Expense> sortedExpenses = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Expense expense : expenses) {
                if (LocalDate.parse(expense.getDate()).isBefore(date)) {
                    sortedExpenses.add(expense);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return sortedExpenses;
        }
        return sortedExpenses;
    }

    public static void printExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}