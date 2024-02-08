package a3;

public class Calculator {
    public int ComplexAdd(int a, int b)
    {
        if (a < 2) { return (a+b) * -1; }
        else { return a+b; }
    }
}
