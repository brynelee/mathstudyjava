package JUnit4Demo;

// Calculator.java
public class Calculator {


    public int add(int a, int b){
        return a + b;
    }

    public int minus(int a, int b){
        return a - b;
    }

    public int square(int a){
        return a * a;
    }

    // Bug: 死循环
    public void squareRoot(int a){
        for(; ;)
            ;
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public int divide(int a, int b) throws Exception{
        if(0 == b){
            throw new ArithmeticException("除数不能为零!");
        }
        return a/b;
    }
}

