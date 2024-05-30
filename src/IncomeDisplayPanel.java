import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class IncomeDisplayPanel extends JPanel {

    public static void incomeDisplayPanel(JPanel viewIncome, ArrayList<Income> incomes) {
        viewIncome.removeAll(); 
        viewIncome.setLayout(new BorderLayout());
        viewIncome.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Incomes:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        viewIncome.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");

        for (Income income : incomes) {
            tableModel.addRow(new Object[]{income.getId(), income.getDate(), income.getAmount()});
        }

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        viewIncome.add(scrollPane, BorderLayout.CENTER);
        
        viewIncome.revalidate(); 
        viewIncome.repaint();
    }
}
