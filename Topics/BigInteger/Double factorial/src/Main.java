import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        BigInteger bigInteger = BigInteger.ONE;
        if (n % 2 == 0) {
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 0) {
                    bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (i % 2 != 0) {
                    bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
                }
            }
        }
        return bigInteger;
    }
}
