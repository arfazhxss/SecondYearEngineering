import org.junit.*;

public class calculator {
    public int doSum (int a, int b) {
        return a+b;
    }
    public int doProduct (int a, int b) {
        return a*b;
    }
    public boolean compareTwoNums (int a, int b) {
        return a==b;
    }

    @Before
    public void BeforeTest () {
        System.out.println("This is before Test");
    }
    @Test
    public void testSum () {
        System.out.println("TestSum1 "+doSum(3,5)); 
    }
    @After
    public void AfterTest () {
        System.out.println("This is after Test");
    }

}