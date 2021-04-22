package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;
public class SubTests {
    private Calculator calculator;

    @DataProvider(name = "subOperandsDouble")
    public Object[][] subOperandsDouble() {
        return new Object[][] {
                {"12.21 21.12", "-8.91"},
                {"42.42 0.0", "42.42"},
                {"0.0 42.42", "-42.42"},
                {"0.0 0.0", "0.0"},
                {"42.42 42.42", "0.0"}
        };
    }

    @DataProvider(name = "subOperandsLong")
    public Object[][] subOperandsLong() {
        return new Object[][] {
                {"9876543210 1234567890", "8641975320"},
                {"123123 0", "123123"},
                {"0 321321", "-321321"},
                {"0 0", "0"},
                {"123456789 123456789", "0"}
        };
    }

    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }

    @Test(dataProvider = "subOperandsDouble")
    public void subTestDouble(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        double firstOperand = Double.parseDouble(operandsList[0]);
        double secondOperand = Double.parseDouble(operandsList[1]);

        Assert.assertEquals(Double.parseDouble(expectedValues), calculator.sub(firstOperand, secondOperand), Double.MIN_VALUE);
    }

    @Test(dataProvider = "subOperandsLong")
    public void subTestLong(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        long firstOperand = Long.parseLong(operandsList[0]);
        long secondOperand = Long.parseLong(operandsList[1]);

        Assert.assertEquals(Long.parseLong(expectedValues), calculator.sub(firstOperand, secondOperand));
    }
}
