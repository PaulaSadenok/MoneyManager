import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IncomeStorage {
    private static final String FILENAME = "./JsonFiles/incomes.json";

    public static void saveIncomes(ArrayList<Income> incomes) {
        try (Writer writer = new FileWriter(FILENAME)) {
            Gson gson = new Gson();
            gson.toJson(incomes, writer);
        } catch (IOException e) {
            System.out.println("Error saving incomes: " + e.getMessage());
        }
    }

    public static ArrayList<Income> loadIncomes() {
        ArrayList<Income> incomes = new ArrayList<>();
        try (Reader reader = new FileReader(FILENAME)) {
            Gson gson = new Gson();
            Type incomeListType = new TypeToken<ArrayList<Income>>(){}.getType();
            incomes = gson.fromJson(reader, incomeListType);
        } catch (IOException e) {
            System.out.println("Error loading incomes: " + e.getMessage());
        }
        return incomes;
    }
}
