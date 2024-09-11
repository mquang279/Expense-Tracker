# Expense Tracker
This is a command-line interface (CLI) tool for managing your personal expenses. It allows you to add, delete, list, summarize expenses and store expenses into JSON file. 

# Installation
1.Clone the repository
```
% git clone https://github.com/mquang279/Expense-Tracker.git
% cd Expense-Tracker\src\
```
2.Compile and Run through Terminal
```
% javac ExpenseApp.java
% java ExpenseApp [command] <argument>
```

# How to use
```
// Adding an expense
% java ExpenseApp add --description <description> --amount <amount>

// List all expenses
% java ExpenseApp list

// Delete an expense
$java ExpenseApp delete --id <expenseID>

// Summary all expenses
% java ExpenseApp summary

// Summary for a specific month
% java ExpenseApp summary --month <month(in number)>
