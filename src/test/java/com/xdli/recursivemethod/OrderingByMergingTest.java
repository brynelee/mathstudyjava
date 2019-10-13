package com.xdli.recursivemethod;

import org.junit.Assert;
import org.junit.Test;

public class OrderingByMergingTest{

    @Test
    public void orderDemo(){

        int[] to_sort = {3434, 3356, 67, 12334, 878667, 387};
        int[] sorted = OrderingByMerging.merge_sort(to_sort);

        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }

        Assert.assertEquals(387, sorted[1]);
        Assert.assertEquals(878667, sorted[5]);

    }
}
