package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;
public class DivTests {
    private Calculator calculator;

    @DataProvider(name = "divOperandsDouble")
    public Object[][] divOperandsDouble() {
        return new Object[][] {
                {"21.12 0.5", "42.24"},
                {"0.0 42.42", "0.0"},
                {"-42.42 42.42", "-1.0"},
                {"42.42 -42.42", "-1.0"},
                {"42.42 1", "42.42"},
                {"42.42 -1", "-42.42"}
        };
    }

    @DataProvider(name = "divOperandsLong")
    public Object[][] divOperandsLong() {
        return new Object[][] {
                {"1234567890 1234567890", "1"},
                {"0 321321", "0"},
                {"-123456789 123456789", "-1"},
                {"123123123 1", "123123123"},
                {"123123123 -1", "-123123123"}
        };
    }

    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }

    @Test(dataProvider = "divOperandsDouble")
    public void divTestDouble(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        double firstOperand = Double.parseDouble(operandsList[0]);
        double secondOperand = Double.parseDouble(operandsList[1]);

        Assert.assertEquals(Double.parseDouble(expectedValues), calculator.div(firstOperand, secondOperand), Double.MIN_VALUE);
    }

    @Test(dataProvider = "divOperandsLong")
    public void divTestLong(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        long firstOperand = Long.parseLong(operandsList[0]);
        long secondOperand = Long.parseLong(operandsList[1]);

        Assert.assertEquals(Long.parseLong(expectedValues), calculator.div(firstOperand, secondOperand));
    }
}
