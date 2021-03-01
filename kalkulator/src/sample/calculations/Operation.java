package sample.calculations;

import java.math.BigInteger;

public abstract class Operation {
    Argument argument1;
    Argument argument2;
    int mode;
    public Operation(Argument arg1, int mode)
    {
        this.argument1 = arg1;
        this.mode = mode;

    }
    public void setArg2(Argument arg2)
    {
        this.argument2 = arg2;
    }

    public BigInteger execute()
    {
        return null;
    }

    public String getSign()
    {
        return "=";
    }

}
