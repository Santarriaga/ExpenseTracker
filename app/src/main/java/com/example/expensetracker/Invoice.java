package com.example.expensetracker;

public class Invoice {
    private int id;
    private String iconName;
    private String title;
    private String date;
    private String value;
    private boolean isExpense;


    public Invoice(int id, String iconName, String title, String date, String value, boolean isExpense) {
        this.id = id;
        this.iconName = iconName;
        this.title = title;
        this.date = date;
        this.value = value;
        this.isExpense = isExpense;
    }

    public Invoice(String iconName, String title, String date, String value, boolean isExpense) {
        this.iconName = iconName;
        this.title = title;
        this.date = date;
        this.value = value;
        this.isExpense = isExpense;
    }

    public Invoice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "iconUrl='" + iconName + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", value=" + value +
                ", isExpense=" + isExpense +
                '}';
    }
}
