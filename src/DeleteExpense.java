import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class DeleteExpense {

    public static void removeExpenseById(ArrayList<Expense> expenses, int id) {
        Expense expenseToRemove = null;
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expenseToRemove = expense;
                break;
            }
        }
        if (expenseToRemove != null) {
            expenses.remove(expenseToRemove);
            System.out.println("Expense with ID " + id + " deleted successfully.");
            updateJsonFile(expenses);
        } else {
            System.out.println("Expense with ID " + id + " not found.");
        }
    }

    private static void updateJsonFile(ArrayList<Expense> expenses) {
        try (FileWriter writer = new FileWriter("./JsonFiles/expenses.json")) {
            Gson gson = new Gson();
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error updating JSON file: " + e.getMessage());
        }
    }
}
