package com.xdli.deepfirstsearch;

import org.junit.Test;

import junit.framework.TestCase;

public class TestDemoTree {

    @Test
    public void testAddWord() {
        DemoTree demoTree = new DemoTree('t');

        String[] newWordArray = new String[]{"Good", "Bad", "Test", "Testing", "Excellent", "Ah", "Name", "People"};

        for (int i = 0; i < newWordArray.length; i++) {
            Boolean result = demoTree.addOneWord(newWordArray[i]);
            TestCase.assertTrue(result);
        }

        demoTree.dfsByStack(demoTree.root);

    }
}