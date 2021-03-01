package sample.calculations;

import java.math.BigInteger;

public class NewtonOperation extends Operation{
    public NewtonOperation(Argument arg1, int mode) {
        super(arg1, mode);
    }

    public BigInteger execute()
    {
        BigInteger up = factorial(argument1.calculate());
        BigInteger down = factorial(argument1.calculate().subtract(argument2.calculate())).multiply(factorial(argument2.calculate()));
        return up.divide(down);
    }

    public String toString(int mode)
    {
        return execute()+"";
    }

    @Override
    public String getSign() {
        return "(N)";
    }

    private BigInteger factorial(BigInteger argToFact)
    {
        BigInteger valueToReturn = new BigInteger("1", mode);
        for(BigInteger i = new BigInteger("2", mode); i.compareTo(argToFact)<=0; i = i.add(new BigInteger("1", mode)))
        {
            valueToReturn = valueToReturn.multiply(i);
        }
        return valueToReturn;
    }
}
