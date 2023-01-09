package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Map<String, Account> listAccounts = new HashMap<>();

    public static boolean isAdmin = false;
    public static void main(String[] args) throws IOException {
        login();
        loadFileCsv();
        System.out.println("The next step:");
        int choose;
        do {
            showMenu();
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    Account account = new Account();
                    account.input();
                    listAccounts.put(account.getAccountNumber(), account);
                    break;
                case 2:
                    if(!isAdmin) {
                        System.err.println("You do not have access to this feature!!!");
                        break;
                    }
                    if (listAccounts.size() == 0) {
                        System.err.println("You do not have an account in the system!!!");
                        break;
                    }

                    System.out.println("Account number to recharge: ");
                    String accountNumber = scanner.nextLine();
                    Account account1 = listAccounts.get(accountNumber);
                    if (account1 != null) {
                        account1.addRecharge();
                        account1.display();
                    } else {
                        System.err.println("Account doesn't exist!!!");
                    }
                    break;
                case 3:

                    System.out.println("Account number to transfer: ");
                    Account currentAccountNumberToTransfer = listAccounts.get(scanner.nextLine());
                    
                    if (currentAccountNumberToTransfer != null) {
                        currentAccountNumberToTransfer.transfer();
                    } else {
                        System.err.println("Account doesn't exist!!!");
                    }
                    break;
                case 4:
                    System.out.println("Account number to show history top up: ");
                    Account currentAccountNumberToShowHistoryTopUp = listAccounts.get(scanner.nextLine());
                    if (currentAccountNumberToShowHistoryTopUp != null) {
                        currentAccountNumberToShowHistoryTopUp.displayMoneyTopUpHistory();
                    } else {
                        System.err.println("Account doesn't exist!!!");
                    }
                    break;
                case 5:
                   System.out.println("Account number to show history transfer: ");
                    Account currentAccountNumberToMoneyTransferHistory = listAccounts.get(scanner.nextLine());
                    if (currentAccountNumberToMoneyTransferHistory != null) {
                        currentAccountNumberToMoneyTransferHistory.displayTransferMoneyHistory();
                    } else {
                        System.err.println("Account doesn't exist!!!");
                    }
                    break;
                case 6:
                    System.out.println("List accounts: ");
                    int number = 1;
                    System.out.printf("----------------------------------------------------------------------------------------------%n");
                    System.out.printf("                                            LIST ACCOUNTS                                     %n");
                    System.out.printf("----------------------------------------------------------------------------------------------%n");

                    System.out.printf(" | %-15s | %-20s | %-10s | %-30s |%n", "ORDINAL NUMBERS","ACCOUNT NUMBER", "NAME", "ACCOUNT BALANCE");
                    for (Map.Entry<String, Account> entry : listAccounts.entrySet()) {
                        System.out.printf(" | %-15s | %-20s | %-10s |$ %-30s|%n", number++,
                                entry.getValue().getAccountNumber(), entry.getValue().getFullName(), entry.getValue().getAccountBalance());
                    }
                    System.out.printf("----------------------------------------------------------------------------------------------%n");
                    break;
                case 7:
                    System.out.println("Account number to remove: ");
                    Account currentAccountNumberToRemove = listAccounts.get(scanner.nextLine());
                    if (currentAccountNumberToRemove != null) {
                        listAccounts.remove(currentAccountNumberToRemove.getAccountNumber());
                    } else {
                        System.err.println("Account doesn't exist!!!");
                    }
                    break;
                case 8:
                    System.out.println("Exit!");
                    break;
                case 9:
                    saveCsv();
                    break;
                case 10:
                    System.out.println("Exit system");
                    System.exit(0);
                default:
                    System.out.println("You entered the wrong!Enter choice: 0~10");
                    break;
            }
        } while (true);
    }
    private static final String FILE_HEADER = "Account Number,Name,Account Balance";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static void saveCsv() {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("src/main/java/org/example/Data/MyFile.csv");

            // Write the CSV file header
            fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (Map.Entry<String,Account> accountEntry : listAccounts.entrySet()) {
                fileWriter.append(accountEntry.getValue().getAccountNumber());
                fileWriter.append(",");
                fileWriter.append(accountEntry.getValue().getFullName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(accountEntry.getValue().getAccountBalance()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    private static void loadFileCsv() throws IOException {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src/main/java/org/example/Data/MyFile.csv"));

            // How to read file in java line by line?
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(FILE_HEADER)) {
                    continue;
                }
                parseCsvLine(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }

    }

    public static void parseCsvLine(String csvLine) {
        if (csvLine != null) {
            String[] splitData = csvLine.split(",");
            Account account = new Account(splitData[1],splitData[0], Double.parseDouble(splitData[2]));
            listAccounts.put(account.getAccountNumber(), account);
        }
    }


    static void showMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Recharge Money Into Account");
        System.out.println("3. Money Transmission Services");
        System.out.println("4. Money Top Up History");
        System.out.println("5. Transfer Money History");
        System.out.println("6. Show List Accounts");
        System.out.println("7. Remove Account");
        System.out.println("8. Exit!");
        System.out.println("9. Save CSV File");
        System.out.println("10. Exit system!");
    }
    static void login() {
        System.out.println("----------WELCOME TO BANK----------");
        System.out.println("UserName: ");
        String userName = scanner.nextLine();
        System.out.println("PassWord: ");
        String password = scanner.nextLine();
        if (userName.equalsIgnoreCase("admin") && password.equals("123456")) {
            System.out.println("Congratulations! Successfully logged!");
            Main.isAdmin = true;
        } else if (userName.equalsIgnoreCase("user") && password.equals("123456")) {
            Main.isAdmin = false;
        } else {
            System.err.println("Failed login!");
            login();
        }
    }

}