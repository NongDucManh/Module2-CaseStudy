package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String fullName;
    private String accountNumber;
    private double accountBalance;
    private String phoneNumber;
    private String address;
    private double money;
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

    public Account(String fullName, String accountNumber, double accountBalance, String phoneNumber, String address, double money) {
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

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
}
