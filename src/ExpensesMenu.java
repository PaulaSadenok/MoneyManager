import java.util.*;
import  java.lang.InterruptedException;

public class ExpensesMenu {

    public static void viewExpensesMenu(Scanner scanner, ArrayList<Expense> expenses) throws InterruptedException {
        while (true) {
            System.out.println("\nExpenses Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Back to Main Menu");

            int expenseChoice = getChoice(scanner, 1, 4);

            switch (expenseChoice) {
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
                    viewExpenses(scanner, expenses);
                    break;
                case 3:
                    System.out.print("Enter the ID of the expense to delete: ");
                    int idToDelete = scanner.nextInt();
                    DeleteExpense.removeExpenseById(expenses, idToDelete);
                    Thread.sleep(2000);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void viewExpenses(Scanner scanner, ArrayList<Expense> expenses) throws InterruptedException {
        System.out.println("\nView Expenses Menu:");
        System.out.println("1. View All Expenses");
        System.out.println("2. View expenses by category");

        int viewChoice = getChoice(scanner, 1, 2);

        switch (viewChoice) {
            case 1:
                if (expenses.isEmpty()) {
                    System.out.println("No expenses recorded.");
                } else {
                    System.out.println("Expenses:");
                    for (Expense e : expenses) {
                        System.out.println(e);
                    }
                }
                break;
            case 2:
                viewExpensesByCategoryMenu(scanner, expenses);
                break;
            default:
                System.out.println("Invalid choice. Please select from the menu.");
        }
        Thread.sleep(2000);
    }

    private static void viewExpensesByCategoryMenu(Scanner scanner, ArrayList<Expense> expenses) throws InterruptedException {

        ArrayList<Expense> sortedExpenses = new ArrayList<>(expenses);

        try {

            System.out.print("\nEnter the parameters by which you would like to sort your expenses:\n");

            System.out.print("Enter specific category to view expenses (press Enter to skip): ");
            scanner.nextLine().trim();
            String categoryToView = scanner.nextLine().trim();
            if (!categoryToView.isEmpty()) {
                sortedExpenses = ChooseExpenses.sortExpensesByCategory(expenses, categoryToView);
            }

            System.out.print("Enter maximum amount to view expenses under (press Enter to skip): ");
            String maxAmountInput = scanner.nextLine().trim();
            if (!maxAmountInput.isEmpty()) {
                double maxAmount = Double.parseDouble(maxAmountInput);
                sortedExpenses = ChooseExpenses.sortExpensesUnderAmount(sortedExpenses, maxAmount);
            }

            System.out.print("Enter minimum amount to view expenses higher than (press Enter to skip): ");
            String minAmountInput = scanner.nextLine().trim();
            if (!minAmountInput.isEmpty()) {
                double minAmount = Double.parseDouble(minAmountInput);
                sortedExpenses = ChooseExpenses.sortExpensesHigherThanAmount(sortedExpenses, minAmount);
            }

            System.out.print("Enter date to view expenses later than (YYYY-MM-DD) (press Enter to skip): ");
            String laterThanDate = scanner.nextLine().trim();
            if (!laterThanDate.isEmpty()) {
                sortedExpenses = ChooseExpenses.sortExpensesLaterThanDate(sortedExpenses, laterThanDate);
            }

            System.out.print("Enter date to view expenses before (YYYY-MM-DD) (press Enter to skip): ");
            String beforeDate = scanner.nextLine().trim();
            if (!beforeDate.isEmpty()) {
                sortedExpenses = ChooseExpenses.sortExpensesBeforeDate(sortedExpenses, beforeDate);
            }

            System.out.print("Sorted Expenses:\n");
            ChooseExpenses.printExpenses(sortedExpenses);
        }  catch (NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid data.");
        }
        Thread.sleep(2000);
    }

    private static int getChoice(Scanner scanner, int min, int max) {
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }
}