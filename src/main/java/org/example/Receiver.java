package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Receiver {
    private int money;
    private String note;
    private Date creatAt;

    public Receiver() {
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "money=" + money +
                ", note='" + note + '\'' +
                ", creatAt=" + getCreatAtString() +
                '}';
    }
//Nạp tiền vào tk
    public void recharge() {
        System.out.println("Recharge Information ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount: ");
        money = Integer.parseInt(scanner.nextLine());
        System.out.println("Money transfer content: ");
        note = scanner.nextLine();
        creatAt = new Date();
    }
//    Hiển thị thời gian
    public String getCreatAtString() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String str = format.format(creatAt);
        return str;
    }
    public void display() {
        System.out.println(toString());
    }
}
