package com.github.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calc;
    
    @BeforeAll
    static void init() {
        System.out.println("BeforAll Running");
    }
    
    @AfterAll
    static void teardown() {
        System.out.println("AfterAll Running");
    }
    
    @BeforeEach
    void start() {
        System.out.println("BeforEach Running");
        // Setup steps.
        this.calc = new Calculator();
    }

    @AfterEach
    void stop() {
        System.out.println("AfterEach Running");
    }

    @Test
    public void testSumTwoNumbers() {
        // Expected value
        int expectedValue = 2;

        // Execute test
        int result = calc.SumTwoNumbers(1, 1);

        // check result with expected value
        assertEquals(expectedValue, result, "1 + 1 must be 2");
    }

    @Test
    public void testSumMoreNumbers() {
        // Expected value
        int expectedValue = 5;

        // Execute test
        int result = calc.SumMoreNumbers(1, 1, 3);

        // check result with expected value
        assertEquals(expectedValue, result, "1 + 1 + 3 must be 5");
    }
}
