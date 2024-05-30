
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CombinedMenu {

    public static void viewCombinedMenu(JFrame frame, JPanel mainMenu, ArrayList<Expense> expenses, ArrayList<Income> incomes) throws InterruptedException {
        JPanel viewCombinedMenu = createViewCombinedMenu();
        frame.setContentPane(viewCombinedMenu);
        frame.revalidate();
        frame.repaint();

        JButton viewExpensesIncomesButton = (JButton) viewCombinedMenu.getComponent(1);
        viewExpensesIncomesButton.addActionListener((ActionEvent e) -> {
            viewExpensesAndIncomesMenu(frame, mainMenu, viewCombinedMenu, expenses, incomes);
        });

        JButton checkBalanceButton = (JButton) viewCombinedMenu.getComponent(2);
        checkBalanceButton.addActionListener((ActionEvent e) -> {
            checkBalance(frame, mainMenu, expenses, incomes);
        });

        JButton backButton = (JButton) viewCombinedMenu.getComponent(3);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createViewCombinedMenu() {
        JPanel viewCombinedMenu = new JPanel(null);
        viewCombinedMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Expenses and Incomes");
        titleLabel.setBounds(190, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        viewCombinedMenu.add(titleLabel);

        JButton viewExpensesIncomesButton = new JButton("View Expenses and Incomes");
        viewExpensesIncomesButton.setBounds(100, 210, 600, 80);
        viewExpensesIncomesButton.setBackground(new Color(220, 220, 220));
        viewExpensesIncomesButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewCombinedMenu.add(viewExpensesIncomesButton);

        JButton checkBalanceButton = new JButton("Check Balance Between Expenses and Income on Specific Date");
        checkBalanceButton.setBounds(100, 330, 600, 80);
        checkBalanceButton.setBackground(new Color(220, 220, 220));
        checkBalanceButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewCombinedMenu.add(checkBalanceButton);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(100, 450, 600, 80);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewCombinedMenu.add(backButton);

        return viewCombinedMenu;
    }

    private static void viewExpensesAndIncomesMenu(JFrame frame, JPanel mainMenu, JPanel viewCombinedMenu, ArrayList<Expense> expenses, ArrayList<Income> incomes) {
        JPanel viewExpensesAndIncomesMenu = createViewExpensesAndIncomesMenu();
        frame.setContentPane(viewExpensesAndIncomesMenu);
        frame.revalidate();
        frame.repaint();

        JButton viewAllExpensesIncomesButton = (JButton) viewExpensesAndIncomesMenu.getComponent(1);
        viewAllExpensesIncomesButton.addActionListener((ActionEvent e) -> {

            JPanel printPanel = new JPanel(new BorderLayout());
            ExpenseDisplayPanel.expenseDisplayPanel(printPanel, expenses);

            JPanel expensesPanel = new JPanel(new BorderLayout());
            ExpenseDisplayPanel.expenseDisplayPanel(expensesPanel, expenses);

            JPanel incomesPanel = new JPanel(new BorderLayout());
            IncomeDisplayPanel.incomeDisplayPanel(incomesPanel, incomes);

            JPanel combinedPanel = new JPanel(new GridLayout(1, 2));
            combinedPanel.add(expensesPanel);
            combinedPanel.add(incomesPanel);

            printPanel.add(combinedPanel, BorderLayout.CENTER);

            JButton backButton = new JButton("Go Back");
            backButton.setBackground(new Color(220, 220, 220));
            backButton.addActionListener((ActionEvent backEvent) -> {
                frame.setContentPane(viewExpensesAndIncomesMenu);
                frame.revalidate();
                frame.repaint();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            printPanel.add(buttonPanel, BorderLayout.SOUTH);

            frame.setContentPane(printPanel);
            frame.revalidate();
            frame.repaint();
        });

        JButton viewExpensesIncomesCriteriaButton = (JButton) viewExpensesAndIncomesMenu.getComponent(2);
        viewExpensesIncomesCriteriaButton.addActionListener((ActionEvent e) -> {
            viewExpensesAndIncomesByDateOrAmount(frame, mainMenu, viewExpensesAndIncomesMenu, expenses, incomes);
        });

        JButton backButton = (JButton) viewExpensesAndIncomesMenu.getComponent(3);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(viewCombinedMenu);
            frame.revalidate();
            frame.repaint();
        });

        JButton backMainButton = (JButton) viewExpensesAndIncomesMenu.getComponent(4);
        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createViewExpensesAndIncomesMenu() {
        JPanel viewExpensesAndIncomesMenu = new JPanel(null);
        viewExpensesAndIncomesMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("View Expenses and Incomes");
        titleLabel.setBounds(160, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        viewExpensesAndIncomesMenu.add(titleLabel);

        JButton viewAllExpensesIncomesButton = new JButton("View All Expenses and Incomes");
        viewAllExpensesIncomesButton.setBounds(100, 210, 600, 80);
        viewAllExpensesIncomesButton.setBackground(new Color(220, 220, 220));
        viewAllExpensesIncomesButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesAndIncomesMenu.add(viewAllExpensesIncomesButton);

        JButton viewExpensesIncomesCriteriaButton = new JButton("View Expenses and Incomes by Date or Amount");
        viewExpensesIncomesCriteriaButton.setBounds(100, 330, 600, 80);
        viewExpensesIncomesCriteriaButton.setBackground(new Color(220, 220, 220));
        viewExpensesIncomesCriteriaButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesAndIncomesMenu.add(viewExpensesIncomesCriteriaButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(100, 450, 290, 80);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesAndIncomesMenu.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(410, 450, 290, 80);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesAndIncomesMenu.add(backMainButton);

        return viewExpensesAndIncomesMenu;
    }

    private static void viewExpensesAndIncomesByDateOrAmount(JFrame frame, JPanel mainMenu, JPanel viewExpensesAndIncomesMenu, ArrayList<Expense> expenses, ArrayList<Income> incomes) {
        JPanel viewExpensesAndIncomesByDateOrAmountMenu = new JPanel(null);
        viewExpensesAndIncomesByDateOrAmountMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Enter the parameters by which you would like to sort your transactions:");
        titleLabel.setBounds(70, 0, 600, 165);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        viewExpensesAndIncomesByDateOrAmountMenu.add(titleLabel);

        // Maximum Amount
        JTextField maxAmountField = new JTextField();
        JLabel maxAmountLabel = new JLabel("Enter maximum amount to view transactions under:");
        maxAmountLabel.setBounds(70, 165, 500, 60);
        maxAmountLabel.setFont(new Font("Arial", Font.BOLD, 13));
        maxAmountField.setBounds(500, 180, 200, 30);
        viewExpensesAndIncomesByDateOrAmountMenu.add(maxAmountLabel);
        viewExpensesAndIncomesByDateOrAmountMenu.add(maxAmountField);

        // Minimum Amount
        JTextField minAmountField = new JTextField();
        JLabel minAmountLabel = new JLabel("Enter minimum amount to view transactions higher than:");
        minAmountLabel.setBounds(70, 245, 400, 60);
        minAmountLabel.setFont(new Font("Arial", Font.BOLD, 13));
        minAmountField.setBounds(500, 260, 200, 30);
        viewExpensesAndIncomesByDateOrAmountMenu.add(minAmountLabel);
        viewExpensesAndIncomesByDateOrAmountMenu.add(minAmountField);

        // Date After
        JTextField dateAfterField = new JTextField();
        JLabel dateAfterLabel = new JLabel("Enter date to view transactions later than (YYYY-MM-DD):");
        dateAfterLabel.setBounds(70, 325, 400, 60);
        dateAfterLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateAfterField.setBounds(500, 340, 200, 30);
        viewExpensesAndIncomesByDateOrAmountMenu.add(dateAfterLabel);
        viewExpensesAndIncomesByDateOrAmountMenu.add(dateAfterField);

        // Date Before
        JTextField dateBeforeField = new JTextField();
        JLabel dateBeforeLabel = new JLabel("Enter date to view transactions before (YYYY-MM-DD):");
        dateBeforeLabel.setBounds(70, 405, 400, 60);
        dateBeforeLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateBeforeField.setBounds(500, 420, 200, 30);
        viewExpensesAndIncomesByDateOrAmountMenu.add(dateBeforeLabel);
        viewExpensesAndIncomesByDateOrAmountMenu.add(dateBeforeField);

        // Filter Button
        JButton filterButton = new JButton("Filter");
        filterButton.setBounds(50, 485, 200, 65);
        filterButton.setBackground(new Color(220, 220, 220));
        filterButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpensesAndIncomesByDateOrAmountMenu.add(filterButton);

        // Back Button
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpensesAndIncomesByDateOrAmountMenu.add(backButton);

        // Back to Main Menu Button
        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpensesAndIncomesByDateOrAmountMenu.add(backMainButton);

        filterButton.addActionListener((ActionEvent e) -> {
            try {
                ArrayList<Income> sortedIncomes = new ArrayList<>(incomes);
                ArrayList<Expense> sortedExpenses = new ArrayList<>(expenses);

                String maxAmountInput = maxAmountField.getText().trim();
                if (!maxAmountInput.isEmpty()) {
                    double maxAmount = Double.parseDouble(maxAmountInput);
                    sortedIncomes = ChooseIncome.sortIncomesUnderAmount(sortedIncomes, maxAmount);
                    sortedExpenses = ChooseExpenses.sortExpensesUnderAmount(sortedExpenses, maxAmount);
                }

                String minAmountInput = minAmountField.getText().trim();
                if (!minAmountInput.isEmpty()) {
                    double minAmount = Double.parseDouble(minAmountInput);
                    sortedIncomes = ChooseIncome.sortIncomesHigherThanAmount(sortedIncomes, minAmount);
                    sortedExpenses = ChooseExpenses.sortExpensesHigherThanAmount(sortedExpenses, minAmount);
                }

                String dateAfter = dateAfterField.getText().trim();
                if (!dateAfter.isEmpty()) {
                    try {
                        LocalDate.parse(dateAfter);
                        sortedIncomes = ChooseIncome.sortIncomesLaterThanDate(sortedIncomes, dateAfter);
                        sortedExpenses = ChooseExpenses.sortExpensesLaterThanDate(sortedExpenses, dateAfter);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String dateBefore = dateBeforeField.getText().trim();
                if (!dateBefore.isEmpty()) {
                    try {
                        LocalDate.parse(dateBefore);
                        sortedIncomes = ChooseIncome.sortIncomesBeforeDate(sortedIncomes, dateBefore);
                        sortedExpenses = ChooseExpenses.sortExpensesBeforeDate(sortedExpenses, dateBefore);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (sortedIncomes.isEmpty() && sortedExpenses.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No transactions found with the given criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JPanel printPanel = new JPanel(new BorderLayout());
                    ExpenseDisplayPanel.expenseDisplayPanel(printPanel, expenses);

                    JPanel expensesPanel = new JPanel(new BorderLayout());
                    ExpenseDisplayPanel.expenseDisplayPanel(expensesPanel, sortedExpenses);

                    JPanel incomesPanel = new JPanel(new BorderLayout());
                    IncomeDisplayPanel.incomeDisplayPanel(incomesPanel, sortedIncomes);

                    JPanel combinedPanel = new JPanel(new GridLayout(1, 2));
                    combinedPanel.add(expensesPanel);
                    combinedPanel.add(incomesPanel);

                    printPanel.add(combinedPanel, BorderLayout.CENTER);

                    JButton backToFilterButton = new JButton("Go Back");
                    backToFilterButton.setBackground(new Color(220, 220, 220));
                    backToFilterButton.addActionListener((ActionEvent backEvent) -> {
                        frame.setContentPane(viewExpensesAndIncomesByDateOrAmountMenu);
                        frame.revalidate();
                        frame.repaint();
                    });

                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(backToFilterButton);
                    printPanel.add(buttonPanel, BorderLayout.SOUTH);

                    frame.setContentPane(printPanel);
                    frame.revalidate();
                    frame.repaint();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(viewExpensesAndIncomesMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(viewExpensesAndIncomesByDateOrAmountMenu);
        frame.revalidate();
        frame.repaint();
    }

    private static void checkBalance(JFrame frame, JPanel mainMenu, ArrayList<Expense> expenses, ArrayList<Income> incomes) {
        JPanel balancePanel = new JPanel(null);
        balancePanel.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Check Balance");
        titleLabel.setBounds(250, 0, 600, 155);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        balancePanel.add(titleLabel);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateLabel.setBounds(100, 155, 400, 60);
        startDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balancePanel.add(startDateLabel);

        JTextField startDateField = new JTextField();
        startDateField.setBounds(400, 170, 200, 30);
        balancePanel.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateLabel.setBounds(100, 265, 400, 60);
        endDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balancePanel.add(endDateLabel);

        JTextField endDateField = new JTextField();
        endDateField.setBounds(400, 280, 200, 30);
        balancePanel.add(endDateField);

        JLabel balanceResultLabel = new JLabel();
        balanceResultLabel.setBounds(100, 375, 600, 60);
        balanceResultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balancePanel.add(balanceResultLabel);

        JButton checkButton = new JButton("Check Balance");
        checkButton.setBounds(50, 485, 200, 65);
        checkButton.setBackground(new Color(220, 220, 220));
        checkButton.setFont(new Font("Arial", Font.BOLD, 15));
        balancePanel.add(checkButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        balancePanel.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        balancePanel.add(backMainButton);

        checkButton.addActionListener((ActionEvent e) -> {
            try {
                String startDateString = startDateField.getText().trim();
                String endDateString = endDateField.getText().trim();

                LocalDate startDate = LocalDate.parse(startDateString);
                LocalDate endDate = LocalDate.parse(endDateString);

                double totalExpenses = 0;
                double totalIncomes = 0;

                for (Expense expense : expenses) {
                    LocalDate expenseDate = LocalDate.parse(expense.getDate());
                    if ((expenseDate.isEqual(startDate) || expenseDate.isAfter(startDate))
                            && (expenseDate.isEqual(endDate) || expenseDate.isBefore(endDate))) {
                        totalExpenses += expense.getAmount();
                    }
                }

                for (Income income : incomes) {
                    LocalDate incomeDate = LocalDate.parse(income.getDate());
                    if ((incomeDate.isEqual(startDate) || incomeDate.isAfter(startDate))
                            && (incomeDate.isEqual(endDate) || incomeDate.isBefore(endDate))) {
                        totalIncomes += income.getAmount();
                    }
                }

                double balance = totalIncomes - totalExpenses;
                String formattedBalance = String.format("%.2f", balance);
                balanceResultLabel.setText("Balance from " + startDateString + " to " + endDateString + ": " + formattedBalance);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(balancePanel);
        frame.revalidate();
        frame.repaint();
    }

}
