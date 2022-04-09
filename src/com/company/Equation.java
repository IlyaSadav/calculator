package com.company;

import java.util.Scanner;

public class Equation {
    String input;
    Operand x;
    Operand y;
    String operator;
    String solution;

    public Equation(){
        this.input = "";
        this.x = new Operand();
        this.y = new Operand();
        this.operator = "";
        this.solution = "";
    }

    void inputEquation() {
        Scanner in = new Scanner(System.in);
        this.input = in.nextLine();
    }

    void getExpression() throws InputException {
        String[] operations  = new String[] {"+", "-", "*", "/"};
        int merger = -1;
        int i = 0;
        while (i < 4 && merger == -1)
        {
            merger = this.input.indexOf(operations[i]);
            i++;
        }
        if (merger == -1)
        {
            throw new InputException("Exception: No operator");
        }
        else
        {
            try {
                this.x.initialize(this.input.substring(0, merger));
                this.y.initialize(this.input.substring(merger + 1));
                this.operator = operations[i - 1];
            } catch (InputException ie)
            {
                System.out.println(ie.getMessage());
                System.exit(0);
            }
        }
    }

    String convertToRoman(int x) {
        String rez = "";
        String[] romanNumbers = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romanRoundNumbers = new String[] {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        if (x != 100) {
            int b = x / 10;
            int a = x % 10;
            if (b > 0)
                rez += romanRoundNumbers[b - 1];
            if (a > 0)
                rez += romanNumbers[a - 1];
            return rez;
        }
        else
            return "C";
    }

    void calculate() throws InputException {
        if (this.x.isRoman != this.y.isRoman) {
            throw new InputException("Exception: different numeral systems used");
        } else {
            int solution = this.x.value;
            switch (this.operator) {
                case "+" -> solution += this.y.value;
                case "-" -> solution -= this.y.value;
                case "*" -> solution *= this.y.value;
                case "/" -> solution /= this.y.value;
            }
            if (!this.x.isRoman) {
                this.solution = Integer.toString(solution);
            }
            else {
                if (solution < 1) {
                    throw new InputException("Exception: Roman numerals can only be positive");
                } else {
                    this.solution = convertToRoman(solution);
                }
            }
        }

    }

    void solve(){
        this.input = this.input.replace(" ", "");
        try {
            this.getExpression();
            this.calculate();
            System.out.println(this.solution);
        }
        catch(InputException ie) {
            System.out.println(ie.getMessage());
            System.exit(0);
        }
    }
}
