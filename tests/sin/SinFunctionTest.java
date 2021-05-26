package sin;

import Sin.SinFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class SinFunctionTest {
    private SinFunction sinFunction = new SinFunction();

    @ParameterizedTest
    @CsvFileSource(resources = "/sin/testDate.csv")
    void calc(double input, double expected) {
        Assertions.assertEquals(expected, Math.round(sinFunction.calc(input) * 100000.0) / 100000.0);
    }
}