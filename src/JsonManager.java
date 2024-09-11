import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class JsonManager {
    private final Path path = Path.of("Expenses.json");

    public JsonManager(){
        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    public void jsonUpdate(ArrayList<Expense> expenses){
        try {
            Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING).close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (int i = 0; i < expenses.size(); i++){
            if (i != expenses.size() - 1){
                stringBuilder.append(expenses.get(i).toJson() + ",\n");
            }
            else stringBuilder.append(expenses.get(i).toJson());
        }
        stringBuilder.append("\n]");

        try {
            Files.writeString(path, stringBuilder.toString(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
