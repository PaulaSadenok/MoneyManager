
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddIncome {

    public static void addIncome(JFrame frame, JPanel mainMenu, JPanel incomeMenu, ArrayList<Income> incomes) {
        JPanel addIncomeMenu = new JPanel(null);
        addIncomeMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Add Income");
        titleLabel.setBounds(250, 0, 600, 155);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        addIncomeMenu.add(titleLabel);

        JTextField dateField = new JTextField();
        JLabel dateLabel = new JLabel("Enter date (YYYY-MM-DD):");
        dateLabel.setBounds(100, 155, 300, 60);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateField.setBounds(500, 170, 200, 30);
        addIncomeMenu.add(dateLabel);
        addIncomeMenu.add(dateField);

        JTextField amountField = new JTextField();
        JLabel amountLabel = new JLabel("Enter amount:");
        amountLabel.setBounds(100, 320, 250, 60);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        amountField.setBounds(500, 335, 200, 30);
        addIncomeMenu.add(amountLabel);
        addIncomeMenu.add(amountField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(50, 485, 200, 65);
        addButton.setBackground(new Color(220, 220, 220));
        addButton.setFont(new Font("Arial", Font.BOLD, 15));
        addIncomeMenu.add(addButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        addIncomeMenu.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        addIncomeMenu.add(backMainButton);

        addButton.addActionListener((ActionEvent e) -> {
            String date = dateField.getText();
            String amountText = amountField.getText();
            boolean valid = true;

            try {
                LocalDate.parse(date);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
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
                int nextId = calculateNextId(incomes);
                incomes.add(new Income(nextId, date, amount));
                IncomeStorage.saveIncomes(incomes);
                IncomeStorage.saveIncomes(incomes);
                JOptionPane.showMessageDialog(frame, "Income added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                frame.setContentPane(mainMenu);
                frame.revalidate();
                frame.repaint();
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(incomeMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(addIncomeMenu);
        frame.revalidate();
        frame.repaint();
    }
    
    private static int calculateNextId(ArrayList<Income> incomes) {
        int highestId = 0;
        for (Income income : incomes) {
            if (income.getId() > highestId) {
                highestId = income.getId();
            }
        }
        return highestId + 1;
    }
}
