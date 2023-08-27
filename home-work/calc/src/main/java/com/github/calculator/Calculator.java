package com.github.calculator;

public class Calculator {
    /* 
     * Input: 2 numbers.
     * Output: Sumary of 2 numbers.
     */
    public int SumTwoNumbers(int x, int y){
        return x + y;
    }

    /* 
     * Input: 2 or more numbers.
     * Output: Sumary of numbers.
     */
    public int SumMoreNumbers(int... x){
        int total = 0;

        for (int i : x) {
            total += i;
        }
        return total;
    }
}
