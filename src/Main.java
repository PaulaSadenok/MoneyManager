import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses Of Specified Category");
            System.out.println("4. View Expenses UNDER Amount");
            System.out.println("5. View Expenses HIGHER Than Amount");
            System.out.println("6. View Expenses LATER Than Date");
            System.out.println("7. View Expenses BEFORE Date");
            System.out.println("8. Exit");

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
                    Thread.sleep(2000);
                    break;
                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        System.out.println("Expenses:");
                        for (Expense e : expenses) {
                            System.out.println(e);
                        }
                        Thread.sleep(2000);
                    }
                    break;
                case 3:
                    System.out.print("Enter category to view expenses: ");
                    String categoryToView = scanner.next();
                    ChooseExpenses.printExpensesByCategory(expenses, categoryToView);
                    Thread.sleep(2000);
                    break;
                case 4:
                    System.out.print("Enter maximum amount to view expenses under: ");
                    double maxAmount = scanner.nextDouble();
                    ChooseExpenses.printExpensesUnderAmount(expenses, maxAmount);
                    Thread.sleep(2000);
                    break;
                case 5:
                    System.out.print("Enter minimum amount to view expenses higher than: ");
                    double minAmount = scanner.nextDouble();
                    ChooseExpenses.printExpensesHigherThanAmount(expenses, minAmount);
                    Thread.sleep(2000);
                    break;
                case 6:
                    System.out.print("Enter date to view expenses later than (YYYY-MM-DD): ");
                    String laterThanDate = scanner.next();
                    ChooseExpenses.printExpensesLaterThanDate(expenses, laterThanDate);
                    Thread.sleep(2000);
                    break;
                case 7:
                    System.out.print("Enter date to view expenses before (YYYY-MM-DD): ");
                    String beforeDate = scanner.next();
                    ChooseExpenses.printExpensesBeforeDate(expenses, beforeDate);
                    Thread.sleep(2000);
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }
}
