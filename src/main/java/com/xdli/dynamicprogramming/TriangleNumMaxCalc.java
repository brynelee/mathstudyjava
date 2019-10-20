package com.xdli.dynamicprogramming;

import java.util.Random;

public class TriangleNumMaxCalc {

    private int level = 5;
    private int width = 5;
    private int[][] triangleNum;
    private int[][] maxSumState;

    public int callingCount = 0;

    //initialize the triangle Number matrix and print it
    public TriangleNumMaxCalc(int level2set, int width2set){
        if (level2set > 1) level = level2set;
        if (width2set > 1 && width2set >= level) width = width2set;

        triangleNum = new int[level][width];
        maxSumState = new int[level][width];
        Random rand = new Random();

        //initialize the triangle
        for (int i = 0; i < level; i++) {
            for (int j = 0; j < width; j++) {
                if (j > i)
                    triangleNum[i][j] = 0;
                else
                    triangleNum[i][j] = rand.nextInt(10);
                //initialize the maxSumState with -1 as marked into initial state
                maxSumState[i][j] = -1;
                System.out.print(triangleNum[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * calculate max sum by clean recursive method without dynamic programming
     * @param i
     * @param j
     * @return
     */
    public int maxSumByRecursiveMethod(int i, int j){
        callingCount++;
        //System.out.println("maxSumByRecursiveMethod was called by i " + i + " and j " + j);
        if (i == level-1 ) return triangleNum[i][j];
        int x = maxSumByRecursiveMethod(i+1, j);
        int y = maxSumByRecursiveMethod(i+1, j+1);
        return Math.max(x, y) + triangleNum[i][j];
    }

    /**
     * calculate max sum by recursive method with maxSumState used but without pre-calculation
     * @param i
     * @param j
     * @return
     */
    public int maxSumByRecursiveMethodWithState(int i, int j){
        callingCount++;
        //System.out.println("maxSumByRecursiveMethodWithState was called by i " + i + " and j " + j);
        //if state exist, return immediately without recursive calling
        if (maxSumState[i][j] != -1)
            return maxSumState[i][j];
        if (i == level-1 )
            return triangleNum[i][j];
        else {
            maxSumState[i][j] = Math.max(
                    maxSumByRecursiveMethodWithState(i+1, j),
                    maxSumByRecursiveMethodWithState(i+1, j+1))
                    + triangleNum[i][j];
        }
        return maxSumState[i][j];
    }


    private void calcMaxSumState(int startLevel, int startWidth){
        //初始化最底层的maxSumState状态空间
        for (int i = 0; i < width; i++) {
            maxSumState[level - 1][i] = triangleNum[level - 1][i];
            //System.out.print(String.format("%d ", maxSumState[level-1][i]));
        }
        System.out.print("\n");
        //从最底层的状态空间倒推上层空间的状态
        for (int i = level - 2; i >= startLevel ; i--) {
            for (int j = startWidth; j <= i ; j++) {
                maxSumState[i][j] = Math.max(maxSumState[i+1][j], maxSumState[i+1][j+1]) + triangleNum[i][j];
                //System.out.print(String.format("%d ", maxSumState[i][j]));
            }
            //System.out.print("\n");
        }
    }

    public int maxSumByDP(int i, int j){
        calcMaxSumState(i, j);
        return maxSumState[i][j];
    }
}
