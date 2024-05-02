public class Expense {

    private String date;
    private String category;
    private double amount;

    public Expense(String date, String category, double amount) {
        setDate(date);
        setCategory(category);
        setAmount(amount);
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
        }
        else {
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

    @Override
    public String toString() {
        return "Expense(date=" + date + ", category=" + category + ", amount=" + amount + ")";
    }
}
