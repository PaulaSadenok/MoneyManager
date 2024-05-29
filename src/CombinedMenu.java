import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CombinedMenu {

    public static void viewCombinedMenu(Scanner scanner, ArrayList<Expense> expenses, ArrayList<Income> incomes) throws InterruptedException {
        while (true) {
            System.out.println("\nCombined Expenses and Income Menu:");
            System.out.println("1. View Expenses and Incomes");
            System.out.println("2. Check Balance Between Expenses and Income on Specific Date");
            System.out.println("3. Back to Main Menu");

            int choice = getChoice(scanner, 1, 3);

            switch (choice) {
                case 1:
                    viewExpensesAndIncomesMenu(scanner, expenses, incomes);
                    break;
                case 2:
                    checkBalance(scanner, expenses, incomes);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void viewExpensesAndIncomesMenu(Scanner scanner, ArrayList<Expense> expenses, ArrayList<Income> incomes) throws InterruptedException {
        System.out.println("\nView Expenses and Incomes:");
        System.out.println("1. View All Expenses and Incomes");
        System.out.println("2. View Expenses and Incomes by Date or Amount");

        int viewChoice = getChoice(scanner, 1, 2);

        switch (viewChoice) {
            case 1:
                System.out.println("\nAll Expenses:");
                ChooseExpenses.printExpenses(expenses);
                System.out.println("\nAll Incomes:");
                ChooseIncome.printIncomes(incomes);
                break;
            case 2:
                viewExpensesAndIncomesByDateOrAmount(scanner, expenses, incomes);
                break;
        }
        Thread.sleep(2000);
    }

    private static void viewExpensesAndIncomesByDateOrAmount(Scanner scanner, ArrayList<Expense> expenses, ArrayList<Income> incomes) throws InterruptedException {
        scanner.nextLine();
        ArrayList<Expense> sortedExpenses = new ArrayList<>(expenses);
        ArrayList<Income> sortedIncomes = new ArrayList<>(incomes);

        try {

            System.out.print("\nEnter the parameters by which you would like to sort your transactions:\n");

            System.out.print("Enter maximum amount to view transactions under (press Enter to skip): ");
            String maxAmountInput = scanner.nextLine().trim();
            if (!maxAmountInput.isEmpty()) {
                double maxAmount = Double.parseDouble(maxAmountInput);
                sortedExpenses = ChooseExpenses.sortExpensesUnderAmount(sortedExpenses, maxAmount);
                sortedIncomes = ChooseIncome.sortIncomesUnderAmount(sortedIncomes, maxAmount);
            }

            System.out.print("Enter minimum amount to view transactions higher than (press Enter to skip): ");
            String minAmountInput = scanner.nextLine().trim();
            if (!minAmountInput.isEmpty()) {
                double minAmount = Double.parseDouble(minAmountInput);
                sortedExpenses = ChooseExpenses.sortExpensesHigherThanAmount(sortedExpenses, minAmount);
                sortedIncomes = ChooseIncome.sortIncomesHigherThanAmount(sortedIncomes, minAmount);
            }

            System.out.print("Enter date to view transactions later than (YYYY-MM-DD) (press Enter to skip): ");
            String laterThanDate = scanner.nextLine().trim();
            if (!laterThanDate.isEmpty()) {
                sortedExpenses = ChooseExpenses.sortExpensesLaterThanDate(sortedExpenses, laterThanDate);
                sortedIncomes = ChooseIncome.sortIncomesLaterThanDate(sortedIncomes, laterThanDate);
            }

            System.out.print("Enter date to view transactions before (YYYY-MM-DD) (press Enter to skip): ");
            String beforeDate = scanner.nextLine().trim();
            if (!beforeDate.isEmpty()) {
                sortedExpenses = ChooseExpenses.sortExpensesBeforeDate(sortedExpenses, beforeDate);
                sortedIncomes = ChooseIncome.sortIncomesBeforeDate(sortedIncomes, beforeDate);
            }

            System.out.print("\nSorted Expenses:\n");
            ChooseExpenses.printExpenses(sortedExpenses);

            System.out.print("\nSorted Incomes:\n");
            ChooseIncome.printIncomes(sortedIncomes);

        }  catch (NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid data.");
        }

        Thread.sleep(2000);
    }

    private static void checkBalance(Scanner scanner, ArrayList<Expense> expenses, ArrayList<Income> incomes) throws InterruptedException {
        scanner.nextLine();
        System.out.print("Enter the start date to check balance (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine().trim();
        System.out.print("Enter the end date to check balance (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine().trim();

        try {
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            double totalExpenses = 0;
            double totalIncomes = 0;

            for (Expense expense : expenses) {
                LocalDate expenseDate = LocalDate.parse(expense.getDate());
                if ((expenseDate.isEqual(startDate) || expenseDate.isAfter(startDate)) &&
                        (expenseDate.isEqual(endDate) || expenseDate.isBefore(endDate))) {
                    totalExpenses += expense.getAmount();
                }
            }

            for (Income income : incomes) {
                LocalDate incomeDate = LocalDate.parse(income.getDate());
                if ((incomeDate.isEqual(startDate) || incomeDate.isAfter(startDate)) &&
                        (incomeDate.isEqual(endDate) || incomeDate.isBefore(endDate))) {
                    totalIncomes += income.getAmount();
                }
            }

            double balance = totalIncomes - totalExpenses;
            System.out.println("Balance from " + startDateString + " to " + endDateString + ": " + balance);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
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
