package org.example;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Account {
    private String fullName;
    private String accountNumber;
    private double accountBalance;
    private String phoneNumber;
    private String address;
    private int money;
    private List<Receiver> receiverList = new ArrayList<>();
    private List<Transfer> transferList = new ArrayList<>();



    public Account() {
        this.accountBalance = 50;
    }

    public Account(String fullName, String accountNumber, double accountBalance) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account(String fullName, String accountNumber, double accountBalance, String phoneNumber, String address, int money) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.money = money;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return  accountNumber + "-" + fullName + "-" + fmt.format(accountBalance);
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        fullName = scanner.nextLine();
        System.out.println("Enter your account number: ");
        accountNumber = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.println("Enter your address: ");
        address = scanner.nextLine();
        money = 0;
    }

    public void addRecharge() {
        Receiver receiver = new Receiver();
        receiver.recharge();
        receiverList.add(receiver);
        accountBalance += receiver.getMoney();
    }

    public void transfer() {
        Transfer transfer = new Transfer();
        transfer.input(this.accountBalance);
        transferList.add(transfer);
        accountBalance -= transfer.getMoney();

    }

    public void display() {
        System.out.println(toString());
        displayMoneyTopUpHistory();
        displayTransferMoneyHistory();
    }

    public void displayMoneyTopUpHistory() {
        System.out.println("Money Top Up History");
        for (Receiver receiver : receiverList) {
            receiver.display();
        }
    }

    public void displayTransferMoneyHistory() {
        System.out.println("Transfer Money History");
        for (Transfer transfer : transferList) {
            transfer.display();
        }
    }
    public static String getHeader() {
        return "Infor:";
    }
//    @Override
//    public String getFileInfor() {
//    return fullName + "," + accountNumber + "," + phoneNumber + "," + address + "," + money;
//    }
//    @Override
//    public void putFileInfor(String str) {
//        String[] param = str.split(",");
//        fullName = param[0];
//        accountNumber = param[1];
//        phoneNumber = param[2];
//        address = param[3];
//        accountBalance = Integer.parseInt(param[4]);
//    }
}
