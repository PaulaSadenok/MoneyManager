import java.util.ArrayList;

public class Income {

    private int id;
    private String date;
    private double amount;
    private static ArrayList<Income> incomes = IncomeStorage.loadIncomes();

    public Income(String date, double amount) {
        this.id = calculateNextId();
        setDate(date);
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(int id) {
        if (id < 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id cannot be negative.");
        }
    }

    public void setDate(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    private int calculateNextId() {
        int highestId = 0;
        for (Income income : incomes) {
            if (income.getId() > highestId) {
                highestId = income.getId();
            }
        }
        return highestId + 1;
    }

    public String toString() {
        return "Income{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}