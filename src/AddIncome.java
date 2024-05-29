import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddIncome {

    public static void addIncome(Scanner scanner, ArrayList<Income> incomes) {
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

        incomes.add(new Income(date, amount));
        IncomeStorage.saveIncomes(incomes);
        System.out.println("Income added successfully.");
    }
}
