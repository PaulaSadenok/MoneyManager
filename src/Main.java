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

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
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
                    AddExpense.addExpense(scanner, expenses);
                    Collections.sort(expenses, new Comparator<Expense>() {
                        @Override
                        public int compare(Expense e1, Expense e2) {
                            return e2.getDate().compareTo(e1.getDate());
                        }
                    });
                    Thread.sleep(2000);
                    break;
                case 2:
                    ViewExpenses.viewExpensesMenu(scanner, expenses);
                    break;
                case 3:
                    System.out.print("Enter the ID of the expense to delete: ");
                    int idToDelete = scanner.nextInt();
                    DeleteExpense.removeExpenseById(expenses, idToDelete);
                    Thread.sleep(2000);
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