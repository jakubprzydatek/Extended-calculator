package sample.calculations;

import java.math.BigInteger;

public class Calculation {
    private int mode;
    private String latestResult;
    private Operation operation;
    private String currentArgument;

    public Calculation(int mode)
    {
        this.mode = mode;
        latestResult = null;
        operation = null;
        currentArgument = "";
    }



    public void setCurrentArgument(String currentArgument) {
        this.currentArgument = currentArgument;
    }
    public String getCurrentArgument()
    {
        return currentArgument;
    }

    public String getLatestResult() {
        return latestResult;
    }

    public void setLatestResult(String latestResult) {
        this.latestResult = latestResult;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public static String convertResult(String result, int prevMode, int mode)
    {
        BigInteger integer = new BigInteger(result, prevMode);
        return integer.toString(mode);
    }
}
