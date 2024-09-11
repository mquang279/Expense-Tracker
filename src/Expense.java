import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private static int lastId;
    private int id;
    private String description;
    private double amount;
    private LocalDate date;
    private DateFormat dateFormat = DateFormat.getDateInstance();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Expense(String description, double amount) {
        this.id = ++lastId;
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public Expense(int id, String description, double amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public static Expense convertJsonToExpense(String json){
        if (json.charAt(json.length() - 1) == ',') json = json.substring(0, json.length() - 1);
        json = json.substring(1, json.length() - 1);
        String[] properties =  json.split(",");
        int countOfProperties = 0;
        int id = 0;
        String description = "";
        double amount = 0;
        LocalDate date = null;
        for (String property : properties){
            countOfProperties++;
            property = property.split(":")[1];
            property = property.substring(1, property.length() - 1);
            switch (countOfProperties){
                case 1:
                    id = Integer.parseInt(property);
                    lastId = id;
                    break;
                case 2:
                    description = property;
                    break;
                case 3:
                    amount = Double.parseDouble(property);
                    break;
                case 4:
                    date = LocalDate.parse(property);
                    break;
            }
        }
        return new Expense(id, description, amount, date);
    }

    public String toJson(){
        return "{\"id\":\"" + id + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"amount\":\"" + amount + "\"," +
                "\"date\":\"" + date + "\"}";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date.format(formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
