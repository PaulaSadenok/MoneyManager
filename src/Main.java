
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Money Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainMenu = createMainMenu();
        frame.add(mainMenu);
        frame.setVisible(true);

        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return e2.getDate().compareTo(e1.getDate());
            }
        });

        ArrayList<Income> incomes = IncomeStorage.loadIncomes();
        Collections.sort(incomes, new Comparator<Income>() {
            @Override
            public int compare(Income i1, Income i2) {
                return i2.getDate().compareTo(i1.getDate());
            }
        });

        JButton expenseButton = (JButton) mainMenu.getComponent(1);
        expenseButton.addActionListener((ActionEvent e) -> {
            ExpensesMenu.viewExpensesMenu(frame, mainMenu, expenses);
        });

        JButton incomeButton = (JButton) mainMenu.getComponent(2);
        incomeButton.addActionListener((ActionEvent e) -> {
            IncomeMenu.viewIncomeMenu(frame, mainMenu, incomes);
        });
        
        JButton bothButton = (JButton) mainMenu.getComponent(3);
        bothButton.addActionListener((ActionEvent e) -> {
            try {
                CombinedMenu.viewCombinedMenu(frame, mainMenu, expenses, incomes);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        JButton exitButton = (JButton) mainMenu.getComponent(4);
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    private static JPanel createMainMenu() {
        JPanel mainMenu = new JPanel(null);
        mainMenu.setBackground(new Color(240, 234, 214));
        
        JLabel titleLabel = new JLabel("Money Manager Menu");
        titleLabel.setBounds(190, 60, 600, 150);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        mainMenu.add(titleLabel);

        JButton expenseButton = new JButton("Expense");
        expenseButton.setBounds(100, 250, 250, 100);
        expenseButton.setBackground(new Color(220, 220, 220));
        expenseButton.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenu.add(expenseButton);

        JButton incomeButton = new JButton("Income");
        incomeButton.setBounds(450, 250, 250, 100);
        incomeButton.setBackground(new Color(220, 220, 220));
        incomeButton.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenu.add(incomeButton);

        JButton bothButton = new JButton("Expenses and Income");
        bothButton.setBounds(100, 400, 250, 100);
        bothButton.setBackground(new Color(220, 220, 220));
        bothButton.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenu.add(bothButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(450, 400, 250, 100);
        exitButton.setBackground(new Color(220, 220, 220));
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenu.add(exitButton);

        return mainMenu;
    }
}
