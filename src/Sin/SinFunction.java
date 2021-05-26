package Sin;

public class SinFunction {
    public double calc(double x) {
        double currency = 0.000000001;
        int n = 0;
        double currentValue = x;
        double sum = 0;
        while (Math.abs(currentValue) > currency && n < 1000) {
            sum += currentValue;
            n += 1;
            currentValue = countNext(x, n, currentValue);
        }
        return sum;
    }

    private double countNext(double x, int n, double prevValue) {
        return (-1) * (prevValue * x * x) / ((2 * n) * (2 * n + 1));
    }
}
