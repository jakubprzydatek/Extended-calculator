package sample.calculations;

import java.math.BigInteger;

public class SubstractOperation extends Operation{
    public SubstractOperation(Argument arg1, int mode) {
        super(arg1, mode);
    }

    public BigInteger execute()
    {
        return argument1.calculate().subtract(argument2.calculate());
    }

    public String toString(int mode)
    {
        return execute()+"";
    }
    @Override
    public String getSign() {
        return "-";
    }
}
