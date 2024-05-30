
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class IncomeMenu {

    public static void viewIncomeMenu(JFrame frame, JPanel mainMenu, ArrayList<Income> incomes) {
        JPanel incomeMenu = createIncomeMenu();
        frame.setContentPane(incomeMenu);
        frame.revalidate();
        frame.repaint();

        JButton addIncomeButton = (JButton) incomeMenu.getComponent(1);
        addIncomeButton.addActionListener((ActionEvent e) -> {
            AddIncome.addIncome(frame, mainMenu, incomeMenu, incomes);
            incomes.sort((i1, i2) -> i2.getDate().compareTo(i1.getDate()));
        });

        JButton viewIncomesButton = (JButton) incomeMenu.getComponent(2);
        viewIncomesButton.addActionListener((ActionEvent e) -> {
            viewIncomes(frame, mainMenu, incomeMenu, incomes);
        });

        JButton deleteIncomeButton = (JButton) incomeMenu.getComponent(3);
        deleteIncomeButton.addActionListener((ActionEvent e) -> {
            DeleteIncome.removeIncomeById(frame, mainMenu, incomeMenu, incomes);
        });

        JButton backButton = (JButton) incomeMenu.getComponent(4);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createIncomeMenu() {
        JPanel incomeMenu = new JPanel(null);
        incomeMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Income");
        titleLabel.setBounds(350, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        incomeMenu.add(titleLabel);

        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.setBounds(100, 250, 250, 100);
        addIncomeButton.setBackground(new Color(220, 220, 220));
        addIncomeButton.setFont(new Font("Arial", Font.BOLD, 20));
        incomeMenu.add(addIncomeButton);

        JButton viewIncomesButton = new JButton("View Incomes");
        viewIncomesButton.setBounds(450, 250, 250, 100);
        viewIncomesButton.setBackground(new Color(220, 220, 220));
        viewIncomesButton.setFont(new Font("Arial", Font.BOLD, 20));
        incomeMenu.add(viewIncomesButton);

        JButton deleteIncomeButton = new JButton("Delete Income");
        deleteIncomeButton.setBounds(100, 400, 250, 100);
        deleteIncomeButton.setBackground(new Color(220, 220, 220));
        deleteIncomeButton.setFont(new Font("Arial", Font.BOLD, 20));
        incomeMenu.add(deleteIncomeButton);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(450, 400, 250, 100);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        incomeMenu.add(backButton);

        return incomeMenu;
    }

    private static void viewIncomes(JFrame frame, JPanel mainMenu, JPanel incomeMenu, ArrayList<Income> incomes) {
        JPanel viewIncomesMenu = createViewIncomesMenu();
        frame.setContentPane(viewIncomesMenu);
        frame.revalidate();
        frame.repaint();

        JButton allIncomeButton = (JButton) viewIncomesMenu.getComponent(1);
        allIncomeButton.addActionListener((ActionEvent e) -> {
            if (incomes.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No incomes recorded.", "Incomes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel incomesPanel = new JPanel();
                IncomeDisplayPanel.incomeDisplayPanel(incomesPanel, incomes);

                JButton backButton = new JButton("Go Back");
                backButton.setBackground(new Color(220, 220, 220));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.addActionListener((ActionEvent ev) -> {
                    frame.setContentPane(viewIncomesMenu);
                    frame.revalidate();
                    frame.repaint();
                });
                incomesPanel.add(backButton, BorderLayout.SOUTH);

                frame.setContentPane(incomesPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton dateAmountIncomeButton = (JButton) viewIncomesMenu.getComponent(2);
        dateAmountIncomeButton.addActionListener((ActionEvent e) -> {
            try {
                viewIncomesByDateOrAmountMenu(frame, mainMenu, viewIncomesMenu, incomes);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        JButton backButton = (JButton) viewIncomesMenu.getComponent(3);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(incomeMenu);
            frame.revalidate();
            frame.repaint();
        });

        JButton backMainButton = (JButton) viewIncomesMenu.getComponent(4);
        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }

    private static JPanel createViewIncomesMenu() {
        JPanel viewIncomesMenu = new JPanel(null);
        viewIncomesMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("View Incomes");
        titleLabel.setBounds(300, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        viewIncomesMenu.add(titleLabel);

        JButton allIncomeButton = new JButton("All Incomes");
        allIncomeButton.setBounds(100, 250, 250, 100);
        allIncomeButton.setBackground(new Color(220, 220, 220));
        allIncomeButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomesMenu.add(allIncomeButton);

        JButton dateAmountIncomeButton = new JButton("Incomes By Date or Amount");
        dateAmountIncomeButton.setBounds(450, 250, 250, 100);
        dateAmountIncomeButton.setBackground(new Color(220, 220, 220));
        dateAmountIncomeButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomesMenu.add(dateAmountIncomeButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(100, 400, 250, 100);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomesMenu.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(450, 400, 250, 100);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomesMenu.add(backMainButton);

        return viewIncomesMenu;
    }

    private static void viewIncomesByDateOrAmountMenu(JFrame frame, JPanel mainMenu, JPanel viewIncomesMenu, ArrayList<Income> incomes) throws InterruptedException {
        JPanel viewIncomeByDateOrAmountMenu = new JPanel(null);
        viewIncomeByDateOrAmountMenu.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Enter the parameters by which you would like to sort your incomes:");
        titleLabel.setBounds(70, 0, 600, 165);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        viewIncomeByDateOrAmountMenu.add(titleLabel);

        // Maximum Amount
        JTextField maxAmountField = new JTextField();
        JLabel maxAmountLabel = new JLabel("Enter maximum amount to view incomes under:");
        maxAmountLabel.setBounds(70, 165, 500, 60);
        maxAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        maxAmountField.setBounds(500, 180, 200, 30);
        viewIncomeByDateOrAmountMenu.add(maxAmountLabel);
        viewIncomeByDateOrAmountMenu.add(maxAmountField);

        // Minimum Amount
        JTextField minAmountField = new JTextField();
        JLabel minAmountLabel = new JLabel("Enter minimum amount to view incomes higher than:");
        minAmountLabel.setBounds(70, 245, 400, 60);
        minAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        minAmountField.setBounds(500, 260, 200, 30);
        viewIncomeByDateOrAmountMenu.add(minAmountLabel);
        viewIncomeByDateOrAmountMenu.add(minAmountField);

        // Date After
        JTextField dateAfterField = new JTextField();
        JLabel dateAfterLabel = new JLabel("Enter date to view incomes later than (YYYY-MM-DD):");
        dateAfterLabel.setBounds(70, 325, 400, 60);
        dateAfterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateAfterField.setBounds(500, 340, 200, 30);
        viewIncomeByDateOrAmountMenu.add(dateAfterLabel);
        viewIncomeByDateOrAmountMenu.add(dateAfterField);

        // Date Before
        JTextField dateBeforeField = new JTextField();
        JLabel dateBeforeLabel = new JLabel("Enter date to view incomes before (YYYY-MM-DD):");
        dateBeforeLabel.setBounds(70, 405, 400, 60);
        dateBeforeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateBeforeField.setBounds(500, 420, 200, 30);
        viewIncomeByDateOrAmountMenu.add(dateBeforeLabel);
        viewIncomeByDateOrAmountMenu.add(dateBeforeField);

        // Filter Button
        JButton filterButton = new JButton("Filter");
        filterButton.setBounds(50, 485, 200, 65);
        filterButton.setBackground(new Color(220, 220, 220));
        filterButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomeByDateOrAmountMenu.add(filterButton);

        // Back Button
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomeByDateOrAmountMenu.add(backButton);

        // Back to Main Menu Button
        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewIncomeByDateOrAmountMenu.add(backMainButton);

        filterButton.addActionListener((ActionEvent e) -> {
            try {
                ArrayList<Income> sortedIncomes = new ArrayList<>(incomes);
                String maxAmountInput = maxAmountField.getText().trim();
                if (!maxAmountInput.isEmpty()) {
                    double maxAmount = Double.parseDouble(maxAmountInput);
                    sortedIncomes = ChooseIncome.sortIncomesUnderAmount(sortedIncomes, maxAmount);
                }

                String minAmountInput = minAmountField.getText().trim();
                if (!minAmountInput.isEmpty()) {
                    double minAmount = Double.parseDouble(minAmountInput);
                    sortedIncomes = ChooseIncome.sortIncomesHigherThanAmount(sortedIncomes, minAmount);
                }

                String laterThanDateIncome = dateAfterField.getText().trim();
                if (!laterThanDateIncome.isEmpty()) {
                    try {
                        LocalDate.parse(laterThanDateIncome);
                        sortedIncomes = ChooseIncome.sortIncomesLaterThanDate(sortedIncomes, laterThanDateIncome);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String beforeDateIncome = dateBeforeField.getText().trim();
                if (!beforeDateIncome.isEmpty()) {
                    try {
                        LocalDate.parse(beforeDateIncome);
                        sortedIncomes = ChooseIncome.sortIncomesBeforeDate(sortedIncomes, beforeDateIncome);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (sortedIncomes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No incomes found with the given criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JPanel incomesPanel = new JPanel(new BorderLayout());
                    incomesPanel.setBackground(new Color(240, 234, 214));

                    IncomeDisplayPanel.incomeDisplayPanel(incomesPanel, sortedIncomes);

                    JButton goBackButton = new JButton("Go Back");
                    goBackButton.setBackground(new Color(220, 220, 220));
                    goBackButton.setFont(new Font("Arial", Font.BOLD, 20));
                    goBackButton.addActionListener((ActionEvent ev) -> {
                        frame.setContentPane(viewIncomeByDateOrAmountMenu);
                        frame.revalidate();
                        frame.repaint();
                    });

                    incomesPanel.add(goBackButton, BorderLayout.SOUTH);

                    frame.setContentPane(incomesPanel);
                    frame.revalidate();
                    frame.repaint();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(viewIncomesMenu);
            frame.revalidate();
            frame.repaint();
        });

        backMainButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });

        frame.setContentPane(viewIncomeByDateOrAmountMenu);
        frame.revalidate();
        frame.repaint();
    }

}
