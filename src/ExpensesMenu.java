
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ExpensesMenu {

    public static void viewExpensesMenu(JFrame frame, JPanel mainMenu, ArrayList<Expense> expenses) {
        JPanel expensesMenu = createExpensesMenu();
        frame.setContentPane(expensesMenu);
        frame.revalidate();
        frame.repaint();

        JButton addExpenseButton = (JButton) expensesMenu.getComponent(1);
        addExpenseButton.addActionListener((ActionEvent e) -> {
            AddExpense.addExpense(frame, mainMenu, expensesMenu, expenses);
        });

        JButton viewExpensesButton = (JButton) expensesMenu.getComponent(2);
        viewExpensesButton.addActionListener((ActionEvent e) -> {
            viewExpenses(frame, mainMenu, expensesMenu, expenses);
        });

        JButton deleteExpensesButton = (JButton) expensesMenu.getComponent(3);
        deleteExpensesButton.addActionListener((ActionEvent e) -> {
            DeleteExpense.removeExpenseById(frame, mainMenu, expensesMenu, expenses);
        });

        JButton backButton = (JButton) expensesMenu.getComponent(4);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createExpensesMenu() {
        JPanel expensesMenu = new JPanel(null);
        expensesMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Expenses");
        titleLabel.setBounds(300, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        expensesMenu.add(titleLabel);

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBounds(100, 250, 250, 100);
        addExpenseButton.setBackground(new Color(220, 220, 220));
        addExpenseButton.setFont(new Font("Arial", Font.BOLD, 20));
        expensesMenu.add(addExpenseButton);

        JButton viewExpensesButton = new JButton("View Expenses");
        viewExpensesButton.setBounds(450, 250, 250, 100);
        viewExpensesButton.setBackground(new Color(220, 220, 220));
        viewExpensesButton.setFont(new Font("Arial", Font.BOLD, 20));
        expensesMenu.add(viewExpensesButton);

        JButton deleteExpensesButton = new JButton("Delete Expenses");
        deleteExpensesButton.setBounds(100, 400, 250, 100);
        deleteExpensesButton.setBackground(new Color(220, 220, 220));
        deleteExpensesButton.setFont(new Font("Arial", Font.BOLD, 20));
        expensesMenu.add(deleteExpensesButton);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(450, 400, 250, 100);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        expensesMenu.add(backButton);

        return expensesMenu;
    }

    private static void viewExpenses(JFrame frame, JPanel mainMenu, JPanel expensesMenu, ArrayList<Expense> expenses) {
        JPanel viewExpensesMenu = createViewExpensesMenu();
        frame.setContentPane(viewExpensesMenu);
        frame.revalidate();
        frame.repaint();

        JButton allExpenseButton = (JButton) viewExpensesMenu.getComponent(1);
        allExpenseButton.addActionListener((ActionEvent e) -> {
            if (expenses.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No expenses recorded.", "Expenses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel expensesPanel = new JPanel();
                ExpenseDisplayPanel.expenseDisplayPanel(expensesPanel, expenses);

                JButton backButton = new JButton("Go Back");
                backButton.setBackground(new Color(220, 220, 220));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener((ActionEvent ev) -> {
                    frame.setContentPane(viewExpensesMenu);
                    frame.revalidate();
                    frame.repaint();
                });
                expensesPanel.add(backButton, BorderLayout.SOUTH);

                frame.setContentPane(expensesPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton categoryExpensesButton = (JButton) viewExpensesMenu.getComponent(2);
        categoryExpensesButton.addActionListener((ActionEvent e) -> {
            try {
                viewExpensesByCategoryMenu(frame, mainMenu, viewExpensesMenu, expenses);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        JButton backButton = (JButton) viewExpensesMenu.getComponent(3);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(expensesMenu);
            frame.revalidate();
            frame.repaint();
        });

        JButton backMainButton = (JButton) viewExpensesMenu.getComponent(4);
        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createViewExpensesMenu() {
        JPanel viewExpensesMenu = new JPanel(null);
        viewExpensesMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("View Expenses");
        titleLabel.setBounds(250, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        viewExpensesMenu.add(titleLabel);

        JButton allExpenseButton = new JButton("All Expenses");
        allExpenseButton.setBounds(100, 250, 250, 100);
        allExpenseButton.setBackground(new Color(220, 220, 220));
        allExpenseButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesMenu.add(allExpenseButton);

        JButton categoryExpensesButton = new JButton("Expenses By Category");
        categoryExpensesButton.setBounds(450, 250, 250, 100);
        categoryExpensesButton.setBackground(new Color(220, 220, 220));
        categoryExpensesButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesMenu.add(categoryExpensesButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(100, 400, 250, 100);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesMenu.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(450, 400, 250, 100);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewExpensesMenu.add(backMainButton);

        return viewExpensesMenu;
    }

    private static void viewExpensesByCategoryMenu(JFrame frame, JPanel mainMenu, JPanel viewExpensesMenu, ArrayList<Expense> expenses) throws InterruptedException {

        JPanel viewExpenseByCategoryMenu = new JPanel(null);
        viewExpenseByCategoryMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Enter the parameters by which you would like to sort your expenses:");
        titleLabel.setBounds(70, 0, 600, 155);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        viewExpenseByCategoryMenu.add(titleLabel);

        // Category
        JTextField categoryField = new JTextField();
        JLabel categoryLabel = new JLabel("Enter specific category to view expenses:");
        categoryLabel.setBounds(70, 155, 400, 36);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        categoryField.setBounds(500, 155, 200, 36);
        viewExpenseByCategoryMenu.add(categoryLabel);
        viewExpenseByCategoryMenu.add(categoryField);

        // Maximum Amount
        JTextField maxAmountField = new JTextField();
        JLabel maxAmountLabel = new JLabel("Enter maximum amount to view expenses under:");
        maxAmountLabel.setBounds(70, 221, 500, 36);
        maxAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        maxAmountField.setBounds(500, 221, 200, 36);
        viewExpenseByCategoryMenu.add(maxAmountLabel);
        viewExpenseByCategoryMenu.add(maxAmountField);

        // Minimum Amount
        JTextField minAmountField = new JTextField();
        JLabel minAmountLabel = new JLabel("Enter minimum amount to view expenses higher than:");
        minAmountLabel.setBounds(70, 287, 400, 36);
        minAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        minAmountField.setBounds(500, 287, 200, 36);
        viewExpenseByCategoryMenu.add(minAmountLabel);
        viewExpenseByCategoryMenu.add(minAmountField);

        JTextField dateAfterField = new JTextField();
        JLabel dateAfterLabel = new JLabel("Enter date to view expenses later than (YYYY-MM-DD):");
        dateAfterLabel.setBounds(70, 353, 400, 36);
        dateAfterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateAfterField.setBounds(500, 353, 200, 36);
        viewExpenseByCategoryMenu.add(dateAfterLabel);
        viewExpenseByCategoryMenu.add(dateAfterField);

        JTextField dateBeforeField = new JTextField();
        JLabel dateBeforeLabel = new JLabel("Enter date to view expenses before (YYYY-MM-DD):");
        dateBeforeLabel.setBounds(70, 419, 400, 36);
        dateBeforeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateBeforeField.setBounds(500, 419, 200, 36);
        viewExpenseByCategoryMenu.add(dateBeforeLabel);
        viewExpenseByCategoryMenu.add(dateBeforeField);

        JButton filterButton = new JButton("Filter");
        filterButton.setBounds(50, 485, 200, 65);
        filterButton.setBackground(new Color(220, 220, 220));
        filterButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpenseByCategoryMenu.add(filterButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpenseByCategoryMenu.add(backButton);

        // Back to Main Menu Button
        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewExpenseByCategoryMenu.add(backMainButton);

        filterButton.addActionListener((ActionEvent e) -> {
            try {
                String categoryToView = categoryField.getText().trim();
                ArrayList<Expense> sortedExpenses = new ArrayList<>(expenses);

                if (!categoryToView.isEmpty()) {
                    sortedExpenses = ChooseExpenses.sortExpensesByCategory(sortedExpenses, categoryToView);
                }

                String maxAmountInput = maxAmountField.getText().trim();
                if (!maxAmountInput.isEmpty()) {
                    double maxAmount = Double.parseDouble(maxAmountInput);
                    sortedExpenses = ChooseExpenses.sortExpensesUnderAmount(sortedExpenses, maxAmount);
                }

                String minAmountInput = minAmountField.getText().trim();
                if (!minAmountInput.isEmpty()) {
                    double minAmount = Double.parseDouble(minAmountInput);
                    sortedExpenses = ChooseExpenses.sortExpensesHigherThanAmount(sortedExpenses, minAmount);
                }

                String laterThanDate = dateAfterField.getText().trim();
                if (!laterThanDate.isEmpty()) {
                    try {
                        LocalDate.parse(laterThanDate);
                        sortedExpenses = ChooseExpenses.sortExpensesLaterThanDate(sortedExpenses, laterThanDate);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String beforeDate = dateBeforeField.getText().trim();
                if (!beforeDate.isEmpty()) {
                    try {
                        LocalDate.parse(beforeDate);
                        sortedExpenses = ChooseExpenses.sortExpensesBeforeDate(sortedExpenses, beforeDate);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
                }

                if (sortedExpenses.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No expenses found with the given criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JPanel expensesPanel = new JPanel(new BorderLayout());
                    expensesPanel.setBackground(new Color(240, 234, 214));

                    ExpenseDisplayPanel.expenseDisplayPanel(expensesPanel, sortedExpenses);

                    JButton goBackButton = new JButton("Go Back");
                    goBackButton.setBackground(new Color(220, 220, 220));
                    goBackButton.setFont(new Font("Arial", Font.BOLD, 20));
                    goBackButton.addActionListener((ActionEvent ev) -> {
                        frame.setContentPane(viewExpensesMenu);
                        frame.revalidate();
                        frame.repaint();
                    });

                    expensesPanel.add(goBackButton, BorderLayout.SOUTH);

                    frame.setContentPane(expensesPanel);
                    frame.revalidate();
                    frame.repaint();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(viewExpensesMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(viewExpenseByCategoryMenu);
        frame.revalidate();
        frame.repaint();
    }
}
