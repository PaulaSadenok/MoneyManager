import java.util.ArrayList;

public final class Expense {

    private int id;
    private String date;
    private String category;
    private double amount;

    public Expense(int id, String date, String category, double amount) {
        setId(id);
        setDate(date);
        setCategory(category);
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative.");
        }
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Expense(id=" + id + ", date=" + date + ", category=" + category + ", amount=" + amount + ")";
    }
}
