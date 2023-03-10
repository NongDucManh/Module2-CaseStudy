package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Transfer {
    String transferAccount;
    String note;
    double money;
    Date creatAt;

    public Transfer() {
    }

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "Receiving account number='" + transferAccount + '\'' +
                ", Message = '" + note + '\'' +
                ", Money = " + "$" + money +
                ", Transaction Date = " + getCreatAtString() +
                '}';
    }

    public void display() {
        System.out.println(toString());
    }

    public void input(double maxMoney) {
        System.out.println("Enter deposit information: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to receive: ");
        transferAccount = scanner.nextLine();
        boolean flag = true;
        for (; ; ) {
            do {
                try {
                    System.out.println("Enter amount ($): ");
                    money = Double.parseDouble(scanner.nextLine());
                    flag = false;
                } catch (NumberFormatException e) {
                    System.err.println("Wrong format, re-enter!!!");
                }
            }
            while (flag);
            if (money <= maxMoney) {
                break;
            }
            System.err.println("You cannot transfer amount anymore than is in your account!!!");
        }
        System.out.println("Money transfer content: ");
        note = scanner.nextLine();
        creatAt = new Date();
        Account receiverAccountBalance = Main.listAccounts.get(transferAccount);
        if (receiverAccountBalance == null) {
            System.err.println("Receiver account not exist!!!");
            return;
        }
        double currentReceiverAccountBalance = receiverAccountBalance.getAccountBalance();
        receiverAccountBalance.setAccountBalance(currentReceiverAccountBalance + money);
    }

    public String getCreatAtString() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String string1 = format.format(creatAt);
        return string1;
    }
}
