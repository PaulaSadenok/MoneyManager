import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ChooseIncome {

    public static ArrayList<Income> sortIncomesUnderAmount(ArrayList<Income> incomes, double maxAmount) {
        ArrayList<Income> sortedIncomes = new ArrayList<>();
        for (Income income : incomes) {
            if (income.getAmount() < maxAmount) {
                sortedIncomes.add(income);
            }
        }
        return sortedIncomes;
    }

    public static ArrayList<Income> sortIncomesHigherThanAmount(ArrayList<Income> incomes, double minAmount) {
        ArrayList<Income> sortedIncomes = new ArrayList<>();
        for (Income income : incomes) {
            if (income.getAmount() > minAmount) {
                sortedIncomes.add(income);
            }
        }
        return sortedIncomes;
    }

    public static ArrayList<Income> sortIncomesLaterThanDate(ArrayList<Income> incomes, String dateString) {
        ArrayList<Income> sortedIncomes = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Income income : incomes) {
                if (LocalDate.parse(income.getDate()).isAfter(date)) {
                    sortedIncomes.add(income);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return sortedIncomes;
        }
        return sortedIncomes;
    }

    public static ArrayList<Income> sortIncomesBeforeDate(ArrayList<Income> incomes, String dateString) {
        ArrayList<Income> sortedIncomes = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(dateString);
            for (Income income : incomes) {
                if (LocalDate.parse(income.getDate()).isBefore(date)) {
                    sortedIncomes.add(income);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return sortedIncomes;
        }
        return sortedIncomes;
    }

    public static void printIncomes(ArrayList<Income> incomes) {
        if (incomes.isEmpty()) {
            System.out.println("No incomes found.");
            return;
        }
        for (Income income : incomes) {
            System.out.println(income);
        }
    }
}

