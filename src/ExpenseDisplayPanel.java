import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ExpenseDisplayPanel extends JPanel {

    public static void expenseDisplayPanel(JPanel viewExpense, ArrayList<Expense> expenses) {
        viewExpense.setLayout(new BorderLayout());
        viewExpense.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Expenses:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        viewExpense.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Category");
        tableModel.addColumn("Amount");

        for (Expense expense : expenses) {
            tableModel.addRow(new Object[]{expense.getId(), expense.getDate(), expense.getCategory(), expense.getAmount()});
        }

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        viewExpense.add(scrollPane, BorderLayout.CENTER);
    }
}
