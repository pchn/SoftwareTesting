package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;
public class SumTests {
    private Calculator calculator;

    @DataProvider(name = "sumOperandsDouble")
    public Object[][] sumOperandsDouble() {
        return new Object[][] {
                {"12.21 21.12", "33.33"},
                {"42.42 0.0", "42.42"},
                {"0.0 42.42", "42.42"},
                {"0.0 0.0", "0.0"},
                {"-42.42 42.42", "0.0"}
        };
    }

    @DataProvider(name = "sumOperandsLong")
    public Object[][] sumOperandsLong() {
        return new Object[][] {
                {"1234567890 9876543210", "11111111100"},
                {"123123 0", "123123"},
                {"0 321321", "321321"},
                {"0 0", "0"},
                {"-123456789 123456789", "0"}
        };
    }

    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }

    @Test(dataProvider = "sumOperandsDouble")
    public void sumTestDouble(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        double firstOperand = Double.parseDouble(operandsList[0]);
        double secondOperand = Double.parseDouble(operandsList[1]);

        Assert.assertEquals(Double.parseDouble(expectedValues), calculator.sum(firstOperand, secondOperand), Double.MIN_VALUE);
    }

    @Test(dataProvider = "sumOperandsLong")
    public void sumTestLong(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        long firstOperand = Long.parseLong(operandsList[0]);
        long secondOperand = Long.parseLong(operandsList[1]);

        Assert.assertEquals(Long.parseLong(expectedValues), calculator.sum(firstOperand, secondOperand));
    }
}
