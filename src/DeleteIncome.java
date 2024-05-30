import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.google.gson.Gson;

public class DeleteIncome {

    public static void removeIncomeById(JFrame frame, JPanel mainMenu, JPanel incomeMenu, ArrayList<Income> incomes) {
        JPanel deleteField = new JPanel(null);
        deleteField.setBackground(new Color(240, 234, 214));

        JLabel titleLabel = new JLabel("Delete Income");
        titleLabel.setBounds(250, 0, 600, 155);
        Font font = new Font("Arial", Font.PLAIN, 40);
        titleLabel.setFont(font);
        deleteField.add(titleLabel);

        JTextField idField = new JTextField();
        JLabel idLabel = new JLabel("Enter the ID of the income to delete:");
        idLabel.setBounds(100, 290, 450, 60);
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        idField.setBounds(550, 305, 200, 30);
        deleteField.add(idLabel);
        deleteField.add(idField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(50, 485, 200, 65);
        deleteButton.setBackground(new Color(220, 220, 220));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteField.add(deleteButton);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(300, 485, 200, 65);
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteField.add(backButton);

        JButton backMainButton = new JButton("Back to Main Menu");
        backMainButton.setBounds(550, 485, 200, 65);
        backMainButton.setBackground(new Color(220, 220, 220));
        backMainButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteField.add(backMainButton);

        deleteButton.addActionListener((ActionEvent e) -> {
            String idText = idField.getText();
            int id;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Income incomeToRemove = null;
            for (Income income : incomes) {
                if (income.getId() == id) {
                    incomeToRemove = income;
                    break;
                }
            }
            
            if (incomeToRemove != null) {
                incomes.remove(incomeToRemove);
                updateJsonFile(incomes);
                JOptionPane.showMessageDialog(frame, "Income with ID " + id + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Income with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
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

        frame.setContentPane(deleteField);
        frame.revalidate();
        frame.repaint();
    }

    private static void updateJsonFile(ArrayList<Income> incomes) {
        try (FileWriter writer = new FileWriter("./JsonFiles/incomes.json")) {
            Gson gson = new Gson();
            gson.toJson(incomes, writer);
        } catch (IOException e) {
            System.out.println("Error updating JSON file: " + e.getMessage());
        }
    }
}


