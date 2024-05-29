import java.util.*;
import  java.lang.InterruptedException;

public class IncomeMenu {
    public static void viewIncomeMenu(Scanner scanner, ArrayList<Income> incomes) throws InterruptedException {
        while (true) {
            System.out.println("\nIncome Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. View Incomes");
            System.out.println("3. Delete Income");
            System.out.println("4. Back to Main Menu");

            int incomeChoice = getChoice(scanner, 1, 4);

            switch (incomeChoice) {
                case 1:
                    AddIncome.addIncome(scanner, incomes);
                    Collections.sort(incomes, new Comparator<Income>() {
                        @Override
                        public int compare(Income i1, Income i2) {
                            return i2.getDate().compareTo(i1.getDate());
                        }
                    });
                    Thread.sleep(2000);
                    break;
                case 2:
                    viewIncomes(scanner, incomes);
                    break;
                case 3:
                    System.out.print("Enter the ID of the income to delete: ");
                    int idToDelete = scanner.nextInt();
                    DeleteIncome.removeIncomeById(incomes, idToDelete);
                    Thread.sleep(2000);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void viewIncomes(Scanner scanner, ArrayList<Income> incomes) throws InterruptedException {
        System.out.println("\nView Incomes:");
        System.out.println("1. View All Incomes");
        System.out.println("2. View Incomes by Date or Amount");

        int viewChoice = getChoice(scanner, 1, 2);

        switch (viewChoice) {
            case 1:
                ChooseIncome.printIncomes(incomes);
                break;
            case 2:
                viewIncomesByDateOrAmount(scanner, incomes);
                break;
        }
        Thread.sleep(2000);
    }

    private static void viewIncomesByDateOrAmount(Scanner scanner, ArrayList<Income> incomes) throws InterruptedException {
        scanner.nextLine();
        ArrayList<Income> sortedIncomes = new ArrayList<>(incomes);

        try {

            System.out.print("\nEnter the parameters by which you would like to sort your incomes:\n");

            System.out.print("Enter maximum amount to view incomes under (press Enter to skip): ");
            String maxAmountInput = scanner.nextLine().trim();
            if (!maxAmountInput.isEmpty()) {
                double maxAmount = Double.parseDouble(maxAmountInput);
                sortedIncomes = ChooseIncome.sortIncomesUnderAmount(sortedIncomes, maxAmount);
            }

            System.out.print("Enter minimum amount to view incomes higher than (press Enter to skip): ");
            String minAmountInput = scanner.nextLine().trim();
            if (!minAmountInput.isEmpty()) {
                double minAmount = Double.parseDouble(minAmountInput);
                sortedIncomes = ChooseIncome.sortIncomesHigherThanAmount(sortedIncomes, minAmount);
            }

            System.out.print("Enter date to view incomes later than (YYYY-MM-DD) (press Enter to skip): ");
            String laterThanDate = scanner.nextLine().trim();
            if (!laterThanDate.isEmpty()) {
                sortedIncomes = ChooseIncome.sortIncomesLaterThanDate(sortedIncomes, laterThanDate);
            }

            System.out.print("Enter date to view incomes before (YYYY-MM-DD) (press Enter to skip): ");
            String beforeDate = scanner.nextLine().trim();
            if (!beforeDate.isEmpty()) {
                sortedIncomes = ChooseIncome.sortIncomesBeforeDate(sortedIncomes, beforeDate);
            }

            System.out.print("Sorted Incomes:\n");
            ChooseIncome.printIncomes(sortedIncomes);
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
