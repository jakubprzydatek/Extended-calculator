package sample.calculations;

import java.math.BigInteger;
import java.util.ArrayList;

public class Argument {
    private String argValue;
    private int mode;
    public Argument(String argValue, int mode)
    {
        this.argValue = argValue;
        this.mode = mode;
    }

    public BigInteger calculate()
    {
        BigInteger calculatedValue;
        if(argValue.charAt(0) == '!'){
            //argValue = argValue.replace('!', ' ');
            calculatedValue = factorial(new BigInteger(argValue.substring(1), mode));
        }else{
            calculatedValue = new BigInteger(argValue, mode);
        }
        return calculatedValue;
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

    @Override
    public String toString() {
        return calculate().toString(mode);
    }

    public static boolean checkIfLegalArgument(String arg)
    {
        if(arg.equals("")) return false;
        if(arg.length() == 1){
            return arg.charAt(0) != '!' && arg.charAt(0) != '-';
        }
        return true;
    }
}
