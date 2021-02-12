package com.example.expensetracker;

public class Utils {

    private static double foodTotal;
    private static double incomeTotal;
    private static double travelTotal;
    private static double petTotal;
    private static double utilityTotal;
    private static double entertainmentTotal;
    private static double gymTotal;
    private static double rent;


    private static double totalBalance;
    private static double totalIncome;
    private static double totalExpense;
    private static double total;


    public static void initTotals() {
        foodTotal = 0;
        incomeTotal = 0;
        travelTotal = 0;
        petTotal = 0;
        utilityTotal = 0;
        entertainmentTotal = 0;
        gymTotal = 0;
        rent = 0;

        totalBalance = 0;
        totalExpense = 0;
        totalIncome = 0;
        total = 0;
    }

    public static double getTotal() {
        return total;
    }

    public static void addTotal(double total) {
        Utils.total += total;
    }

    public static double getFoodTotal() {
        return foodTotal;
    }

    public static void addFoodTotal(double foodTotal) {
        Utils.foodTotal += foodTotal;
    }

    public static double getIncomeTotal() {
        return incomeTotal;
    }

    public static void addIncomeTotal(double incomeTotal) {
        Utils.incomeTotal += incomeTotal;
    }

    public static double getTravelTotal() {
        return travelTotal;
    }

    public static void addTravelTotal(double travelTotal) {
        Utils.travelTotal += travelTotal;
    }

    public static double getPetTotal() {
        return petTotal;
    }

    public static void addPetTotal(double petTotal) {
        Utils.petTotal += petTotal;
    }

    public static double getUtilityTotal() {
        return utilityTotal;
    }

    public static void addUtilityTotal(double utilityTotal) {
        Utils.utilityTotal += utilityTotal;
    }

    public static double getEntertainmentTotal() {
        return entertainmentTotal;
    }

    public static void addEntertainmentTotal(double entertainmentTotal) {
        Utils.entertainmentTotal += entertainmentTotal;
    }

    public static double getGymTotal() {
        return gymTotal;
    }

    public static void addGymTotal(double gymTotal) {
        Utils.gymTotal += gymTotal;
    }

    public static double getRent() {
        return rent;
    }

    public static void addRent(double rent) {
        Utils.rent += rent;
    }

    public static double getTotalBalance() {
        return totalBalance;
    }

    public static void addTotalBalance(double totalBalance) {
        Utils.totalBalance += totalBalance;
    }

    public static double getTotalIncome() {
        return totalIncome;
    }

    public static void addTotalIncome(double totalIncome) {
        Utils.totalIncome += totalIncome;
    }

    public static double getTotalExpense() {
        return totalExpense;
    }

    public static void addTotalExpense(double totalExpense) {
        Utils.totalExpense += totalExpense;
    }
}
