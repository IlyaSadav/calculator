package com.company;

import java.util.Locale;

public class Operand {
    boolean isRoman;
    int value;

    public Operand() {
        isRoman = false;
        value = 0;
    }

    int convertRomanNumeral(String x) {
        int number = 0;
        boolean a = false;
        for (int i = 0; i < x.length(); i++)
        {
            if (x.charAt(i) == 'i' && !a) {
                number--;
            }
            else {
                a = true;
                if (x.charAt(i) == 'v')
                    number += 5;
                else if (x.charAt(i) == 'x')
                    number += 10;
                else if (x.charAt(i) == 'i')
                    number += 1;
            }
        }
        if (!a) {
            number *= -1;
        }
        return number;
    }

    void initialize(String x) throws InputException {
        x = x.toLowerCase();
        int a;
        try {
            a = Integer.parseInt(x);
            if (a >= 1 && a <= 10) {
                this.value = a;
            }
            else
            {
                throw new InputException("Exception: operands have to be in range [1; 10]");
            }
        }
        catch (NumberFormatException nfe) {
            for (int i = 0; i < x.length(); i++)
            {
                if (!(x.charAt(i) == 'x' || x.charAt(i) == 'v' || x.charAt(i) == 'i')) {
                    throw new InputException("Exception: incorrect operand(s)");
                }
            }
            this.isRoman = true;
            a = convertRomanNumeral(x);
            if (a >= 1 && a <= 10) {
                this.value = a;
            }
            else
            {
                throw new InputException("Exception: operands have to be in range [1; 10]");
            }
        }
    }
}
