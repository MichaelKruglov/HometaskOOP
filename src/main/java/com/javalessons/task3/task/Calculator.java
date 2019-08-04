package com.javalessons.task3.task;

public class Calculator {
    private static final int MONTHS = 12;
    private int loanAmount;
    private int years;
    private float interestRatePerYear;
    private float extraPayment;
    private float interestAmount = 0;

    public Calculator(int loanAmount, int years, float interestRatePerYear) {
        this(loanAmount, years, interestRatePerYear, 0);
    }

    public Calculator(int loanAmount, int years, float interestRatePerYear, float extraPayment) {
        this.loanAmount = loanAmount;
        this.years = years;
        this.interestRatePerYear = interestRatePerYear;
        this.extraPayment = extraPayment;
    }

    public void calculateAndPrint() {
        int periodCount = 0;
        float monthStartBalance = loanAmount;
        float months = years * MONTHS;
        float monthlyInterestRate = interestRatePerYear / MONTHS;
        float payment = calcPayment(loanAmount, monthlyInterestRate, months);
        float monthEndBalance = monthStartBalance;
        System.out.println("MONTH    STARING_BALANCE,  PAYMENT,  INTEREST,  PRINCIPAL,  ENDING_BALANCE,  TOTAL_INTEREST");
        while (periodCount < months) {
            periodCount++;
            if (monthEndBalance >= payment) {
                float monthlyInterest = calcMonthInterest(monthStartBalance, monthlyInterestRate);
                float monthlyPrincipal = calcMonthPrincipal(payment, monthlyInterest);
                monthEndBalance = calcMonthEndbalance(monthStartBalance, monthlyPrincipal);
                interestAmount += monthlyInterest;
                System.out.printf("%d, %15.2f, %12.2f, %8.2f, %8.2f, %15.2f, %15.2f%n", periodCount, monthStartBalance,
                        payment + extraPayment, monthlyInterest, monthlyPrincipal, monthEndBalance, interestAmount);
                monthStartBalance = monthEndBalance;
                if (monthlyPrincipal + extraPayment > monthEndBalance) {
                    extraPayment = 0;
                }
            } else {
                System.out.printf("%d, %15.2f, %12.2f, %8.2f, %8.2f, %15.2f, %15.2f%n", periodCount, monthStartBalance,
                        monthEndBalance, 0.0, monthEndBalance, 0.0, interestAmount);
                break;
            }

        }

    }

    public float calcPayment(float loanAmount, float monthlyInterestRate, float months) {
        return (loanAmount * monthlyInterestRate) / (float) (1 - Math.pow((1 + monthlyInterestRate), -1 * months));
    }

    private float calcMonthInterest(float monthStartBalance, float monthlyInterestRate) {
        return monthStartBalance * monthlyInterestRate;
    }

    private float calcMonthPrincipal(float payment, float monthInterest) {
        return payment + this.extraPayment - monthInterest;
    }

    private float calcMonthEndbalance(float monthStartBalance, float monthPrincipal) {
        return monthStartBalance - monthPrincipal;
    }


    //    System.out.printf("%d, %15.2f, %12.2f, %8.2f, %8.2f, %15.2f, %15.2f%n", periodCount, monthStartBalance,
    //payment, monthlyInterest, monthlyPrincipal, monthEndBalance, interestAmount);
}
