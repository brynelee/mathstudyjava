package com.xdli.JUnit4Demo;

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
    public double squareRoot(double a){
 
    /*  
        //originally programmed for the relative testcase to fail by timing out     
        for(; ;)
            ;
    */

        return Math.sqrt(a);

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

