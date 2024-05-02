import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.util.ArrayList;
import java.lang.reflect.Type;

public class ExpenseStorage {
    private static final String FILENAME = "./JsonFiles/expenses.json";

    public static void saveExpenses(ArrayList<Expense> expenses) {
        try (Writer writer = new FileWriter(FILENAME)) {
            Gson gson = new Gson();
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try (Reader reader = new FileReader(FILENAME)) {
            Gson gson = new Gson();
            Type expenseListType = new TypeToken<ArrayList<Expense>>(){}.getType();
            expenses = gson.fromJson(reader, expenseListType);
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
