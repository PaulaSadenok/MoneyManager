import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return e2.getDate().compareTo(e1.getDate());
            }
        });

        ArrayList<Income> incomes = IncomeStorage.loadIncomes();
        Collections.sort(incomes, new Comparator<Income>() {
            @Override
            public int compare(Income i1, Income i2) {
                return i2.getDate().compareTo(i1.getDate());
            }
        });

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Expenses Menu");
            System.out.println("2. Income Menu");
            System.out.println("3. Expenses and Income Menu");
            System.out.println("4. Exit");

            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    validChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    ExpensesMenu.viewExpensesMenu(scanner, expenses);
                    break;
                case 2:
                    IncomeMenu.viewIncomeMenu(scanner, incomes);
                    break;
                case 3:
                    CombinedMenu.viewCombinedMenu(scanner, expenses, incomes);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }
}