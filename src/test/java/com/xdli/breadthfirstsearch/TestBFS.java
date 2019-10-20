package com.xdli.breadthfirstsearch;

import org.junit.Test;

public class TestBFS {

    @Test
    public void testBFS(){

        int user_num = 30;
        int rel_num = 50;
        LinkageGraph lg = new LinkageGraph(user_num, rel_num);

        int userID4Search = 0;
        System.out.println("started BFS for user ID " + userID4Search);
        lg.bfs(userID4Search);
    }
}
