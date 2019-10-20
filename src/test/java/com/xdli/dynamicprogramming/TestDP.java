package com.xdli.dynamicprogramming;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDP {

    private static TriangleNumMaxCalc tn;
    private static int level4Testing = 30;
    private static int width4Testing = 30;
    private int level2start = 0;
    private int width2start = 0;

    /**
     * initialize the Triangle Number Matrix for common usage
     */

    @BeforeClass
    public static void testTriangleNum(){
        tn = new TriangleNumMaxCalc(level4Testing, width4Testing);
    }

    @Test
    public void testRecursiveMethod(){
        tn.callingCount = 0;
        System.out.println("starting calculation by Recursive method ...");
        System.out.println(String.format("i is %d, j is %d", level2start, width2start));
        long startTime = System.nanoTime();
        int sum = tn.maxSumByRecursiveMethod(level2start, width2start);
        long endTime = System.nanoTime();
        long elapseTime = endTime - startTime;
        System.out.println("the maximum sum is " + sum);
        System.out.println("The time consumed for calculation is: " + elapseTime);
        System.out.println("the calling count is " + tn.callingCount);
    }

    @Test
    public void testRecursiveMethodWithState(){
        tn.callingCount = 0;
        System.out.println("starting calculation by Recursive method with state ...");
        System.out.println(String.format("i is %d, j is %d", level2start, width2start));
        long startTime = System.nanoTime();
        int sum = tn.maxSumByRecursiveMethodWithState(level2start, width2start);
        long endTime = System.nanoTime();
        long elapseTime = endTime - startTime;
        System.out.println("the maximum sum is " + sum);
        System.out.println("The time consumed for calculation is: " + elapseTime);
        System.out.println("the calling count is " + tn.callingCount);
    }


    @Test
    public void testDP(){
        System.out.println("starting calculation by DP ...");
        System.out.println(String.format("i is %d, j is %d", level2start, width2start));
        long startTime = System.nanoTime();
        int sum = tn.maxSumByDP(level2start, width2start);
        long endTime = System.nanoTime();
        long elapseTime = endTime - startTime;
        System.out.println("the maximum sum is " + sum);
        System.out.println("The time consumed for DP calculation is: " + elapseTime);
    }

}
