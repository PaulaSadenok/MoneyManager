import java.util.ArrayList;

public class Expense {

    private int id;
    private String date;
    private String category;
    private double amount;
    private static ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();

    public Expense(String date, String category, double amount) {
        this.id = calculateNextId();
        setDate(date);
        setCategory(category);
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Category cannot be null or empty.");
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
        for (Expense expense : expenses) {
            if (expense.getId() > highestId) {
                highestId = expense.getId();
            }
        }
        return highestId + 1;
    }

    @Override
    public String toString() {
        return "Expense(id=" + id + ", date=" + date + ", category=" + category + ", amount=" + amount + ")";
    }
}
