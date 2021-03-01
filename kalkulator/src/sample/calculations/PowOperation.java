package sample.calculations;

import java.math.BigInteger;

public class PowOperation extends Operation{
    public PowOperation(Argument arg1, int mode) {
        super(arg1, mode);
    }

    public BigInteger execute()
    {
        return power(argument1.calculate(), argument2.calculate());
        //return argument1.calculate().subtract(argument2.calculate());
    }

    public String toString(int mode)
    {
        return execute()+"";
    }
    @Override
    public String getSign() {
        return "^";
    }

    public BigInteger power(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }
}
