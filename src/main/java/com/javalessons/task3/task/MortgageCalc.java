package com.javalessons.task3.task;


import java.util.Scanner;

public class MortgageCalc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of family members");
        int members = scanner.nextInt();
        System.out.println("Enter your family income");
        int income = scanner.nextInt();
        System.out.println("Enter the Amount");
        int loanAmount = scanner.nextInt();
        System.out.println("Enter number of years");
        int years = scanner.nextInt();
        System.out.println("Enter interest rate");
        float interestRate = scanner.nextFloat();
        System.out.println("Enter extra");
        float extraPayment = scanner.nextFloat();
        Calculator calculator;
        if (extraPayment == 0) {
            calculator = new Calculator(loanAmount, years, interestRate);
        } else {
            calculator = new Calculator(loanAmount, years, interestRate, extraPayment);
        }

        double monthlyPayment = calculator.calcPayment(loanAmount, interestRate / 12, years * 12);
        System.out.println("Your monthly payment:  " + monthlyPayment);
        double incomeRate;
        switch (members) {
            case 1:
                incomeRate = 0.5;
                break;
            case 2:
                incomeRate = 0.45;
                break;
            case 3:
                incomeRate = 0.35;
                break;
            case 4:
                incomeRate = 0.3;
                break;
            case 5:
                incomeRate = 0.25;
                break;
                default: incomeRate = 0.0;
        }


        if (monthlyPayment / income < incomeRate) {
            System.out.println("Approve");
            calculator.calculateAndPrint();
        } else {
            System.out.println("Decline");

        }

    }
}
