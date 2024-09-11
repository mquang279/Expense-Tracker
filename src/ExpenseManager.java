import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ExpenseManager {
    private ArrayList<Expense> expenses;

    public ExpenseManager(){
        expenses = new ArrayList<>();
    }

    public void updateExpenseList(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("expenses.json"));
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        String line = null;
        while (true){
            try{
                if ((line = reader.readLine()) == null) break;
            } catch (IOException ioe){
                throw new RuntimeException(ioe);
            }
            if (line.length() > 1){
                expenses.add(Expense.convertJsonToExpense(line));
            }
        }
    }

    public void add(String description, double amount){
        Expense expense = new Expense(description, amount);
        expenses.add(expense);
        System.out.println("Expense added successfully (ID: " + expense.getId() + ")");
    }

    public void list() {
        System.out.printf(" %-2s  %-12s  %-21s %-23s %n", "ID", "Date", "Description", "Amount");
        for (Expense expense : expenses){
            System.out.printf(" %-2s  %-12s  %-21s %-23s %n", Integer.toString(expense.getId()), expense.getDate(), expense.getDescription(), Double.toString(expense.getAmount()));
        }
    }

    public void summary(){
        double totalAmount = 0;
        for (Expense expense : expenses){
            totalAmount += expense.getAmount();
        }
        System.out.println("Total expenses: $" + totalAmount);
    }

    public void delete(int id){
        Expense expense = findExpense(id).orElseThrow(() -> new IllegalArgumentException("Expense with ID: " + id + " not found."));
        expenses.remove(expense);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public Optional<Expense> findExpense(int id){
        return expenses.stream().filter((task) -> task.getId() == id).findFirst();
    }
}
