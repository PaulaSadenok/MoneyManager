
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddExpense {

    public static void addExpense(JFrame frame, JPanel mainMenu, JPanel expensesMenu, ArrayList<Expense> expenses) {
        JPanel addExpenseMenu = new JPanel(null);
        addExpenseMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Add Expense");
        titleLabel.setBounds(300, 0, 600, 155);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        addExpenseMenu.add(titleLabel);

        JTextField dateField = new JTextField();
        JLabel dateLabel = new JLabel("Enter date (YYYY-MM-DD):");
        dateLabel.setBounds(100, 155, 300, 60);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateField.setBounds(500, 170, 200, 30);
        addExpenseMenu.add(dateLabel);
        addExpenseMenu.add(dateField);

        JTextField categoryField = new JTextField();
        JLabel categoryLabel = new JLabel("Enter category:");
        categoryLabel.setBounds(100, 265, 250, 60);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryField.setBounds(500, 280, 200, 30);
        addExpenseMenu.add(categoryLabel);
        addExpenseMenu.add(categoryField);

        JTextField amountField = new JTextField();
        JLabel amountLabel = new JLabel("Enter amount:");
        amountLabel.setBounds(100, 375, 250, 60);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        amountField.setBounds(500, 390, 200, 30);
        addExpenseMenu.add(amountLabel);
        addExpenseMenu.add(amountField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(50, 485, 200, 65);
        addButton.setBackground(new Color(220, 220, 220));
        addButton.setFont(new Font("Arial", Font.BOLD, 15));
        addExpenseMenu.add(addButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        addExpenseMenu.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        addExpenseMenu.add(backMainButton);

        addButton.addActionListener((ActionEvent e) -> {
            String date = dateField.getText();
            String category = categoryField.getText();
            String amountText = amountField.getText();
            boolean valid = true;

            try {
                LocalDate.parse(date);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }

            if (category.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Category cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }

            double amount = 0;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Amount must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }

            if (valid) {
                int nextId = calculateNextId(expenses);
                expenses.add(new Expense(nextId, date, category, amount));
                ExpenseStorage.saveExpenses(expenses);
                JOptionPane.showMessageDialog(frame, "Expense added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Collections.sort(expenses, new Comparator<Expense>() {
                    @Override
                    public int compare(Expense e1, Expense e2) {
                        return e2.getDate().compareTo(e1.getDate());
                    }
                });
                frame.setContentPane(mainMenu);
                frame.revalidate();
                frame.repaint();
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(expensesMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(addExpenseMenu);
        frame.revalidate();
        frame.repaint();
    }
    
    private static int calculateNextId(ArrayList<Expense> expenses) {
        int highestId = 0;
        for (Expense expense : expenses) {
            if (expense.getId() > highestId) {
                highestId = expense.getId();
            }
        }
        return highestId + 1;
    }
}
