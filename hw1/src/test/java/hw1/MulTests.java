package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;
public class MulTests {
    private Calculator calculator;

    @DataProvider(name = "mulOperandsDouble")
    public Object[][] mulOperandsDouble() {
        return new Object[][] {
                {"12.21 21.12", "257.8752"},
                {"42.42 0.0", "0.0"},
                {"0.0 42.42", "0.0"},
                {"0.0 0.0", "0.0"},
                {"-42.42 42.42", "-1799.4564"},
                {"42.42 1", "42.42"},
                {"1 42.42", "42.42"}
        };
    }

    @DataProvider(name = "mulOperandsLong")
    public Object[][] mulOperandsLong() {
        return new Object[][] {
                {"123456 987654", "121931812224"},
                {"123123 0", "0"},
                {"0 321321", "0"},
                {"0 0", "0"},
                {"-123456789 123456789", "-15241578750190521"},
                {"123123 1", "123123"},
                {"1 123123", "123123"}
        };
    }

    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }

    @Test(dataProvider = "mulOperandsDouble")
    public void mulTestDouble(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        double firstOperand = Double.parseDouble(operandsList[0]);
        double secondOperand = Double.parseDouble(operandsList[1]);

        Assert.assertEquals(Double.parseDouble(expectedValues), calculator.mult(firstOperand, secondOperand), Double.MIN_VALUE);
    }

    @Test(dataProvider = "mulOperandsLong")
    public void mulTestLong(String operands, String expectedValues) {
        String[] operandsList = operands.split("\s+");

        long firstOperand = Long.parseLong(operandsList[0]);
        long secondOperand = Long.parseLong(operandsList[1]);

        Assert.assertEquals(Long.parseLong(expectedValues), calculator.mult(firstOperand, secondOperand));
    }
}
