public class ExpenseApp {
    public static void main(String[] args) {
        String command = args[0];
        ExpenseManager expenseManager = new ExpenseManager();
        JsonManager jsonManager = new JsonManager();

        expenseManager.updateExpenseList();
        switch (command){
            case "add":
                if (args.length < 5){
                    System.out.println("Usage: java ExpenseApp add --description <description> --amount <amount>");
                }
                else expenseManager.add(args[2], Double.parseDouble(args[4]));
                break;
            case "list":
                expenseManager.list();
                break;
            case "summary":
                if (args.length == 1){
                    expenseManager.summary();
                }
                else expenseManager.summaryMonth(Integer.parseInt(args[2]));
                break;
            case "delete":
                expenseManager.delete(Integer.parseInt(args[2]));
                break;
            default:
                System.out.println("ExpenseApp <command> [argurment]");
        }
        jsonManager.jsonUpdate(expenseManager.getExpenses());
    }
}
