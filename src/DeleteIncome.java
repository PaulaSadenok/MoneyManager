import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class DeleteIncome {

    public static void removeIncomeById(ArrayList<Income> incomes, int id) {
        Income incomeToRemove = null;
        for (Income income : incomes) {
            if (income.getId() == id) {
                incomeToRemove = income;
                break;
            }
        }
        if (incomeToRemove != null) {
            incomes.remove(incomeToRemove);
            System.out.println("Income with ID " + id + " deleted successfully.");
            updateJsonFile(incomes);
        } else {
            System.out.println("Income with ID " + id + " not found.");
        }
    }

    private static void updateJsonFile(ArrayList<Income> incomes) {
        try (FileWriter writer = new FileWriter("./JsonFiles/incomes.json")) {
            Gson gson = new Gson();
            gson.toJson(incomes, writer);
        } catch (IOException e) {
            System.out.println("Error updating JSON file: " + e.getMessage());
        }
    }
}

