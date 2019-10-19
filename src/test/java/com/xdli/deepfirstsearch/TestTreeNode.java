package com.xdli.deepfirstsearch;

import org.junit.Test;

import junit.framework.TestCase;

public class TestTreeNode {

    @Test
    public void testTreeNodeCreation(){
        TreeNode tn1 = new TreeNode('t', "a", "root tree node with t started.");
        TestCase.assertNotNull(tn1);
    }
}