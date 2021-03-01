package sample.calculations;

import java.math.BigInteger;

public class DivOperation extends Operation{
    public DivOperation(Argument arg1, int mode) {
        super(arg1, mode);
    }

    public BigInteger execute()
    {
        return argument1.calculate().divide(argument2.calculate());
    }

    public String toString(int mode)
    {
        return execute()+"";
    }

    @Override
    public String getSign() {
        return "/";
    }
}
