import java.util.ArrayList;

public class Income {

    private int id;
    private String date;
    private double amount;
    private static ArrayList<Income> incomes = new ArrayList<>(); 

    public Income(int id, String date, double amount) {
        setId(id);
        setDate(date);
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

    public static ArrayList<Income> getIncomes() {
        return incomes;
    }

    public static void setIncomes(ArrayList<Income> incomes) {
        Income.incomes = incomes;
    }

    public static void addIncome(Income income) {
        incomes.add(income);
    }

    public static void removeIncome(Income income) {
        incomes.remove(income);
    }

    public static void clearIncomes() {
        incomes.clear();
    }

    @Override
    public String toString() {
        return "Income{" + "id=" + id + ", date='" + date + '\'' + ", amount=" + amount + '}';
    }
}
