package test.com.xdli.doublewaybfs; 

import com.xdli.doublewaybfs.FriendRelGraph;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* FriendRelGraph Tester. 
* 
* @author <Authors name> 
* @since <pre>10月 26, 2019</pre> 
* @version 1.0 
*/ 
public class FriendRelGraphTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: bi_bfs(DWNode[] user_nodes, int user_id_a, int user_id_b) 
* 
*/ 
@Test
public void testBi_bfs() throws Exception {


    int user_num = 50;
    int rel_num = 100;
    FriendRelGraph lg = new FriendRelGraph(user_num, rel_num);

    int pairtimes = 4;
    for (int i = 0; i < pairtimes; i++){
        int distance = FriendRelGraph.bi_bfs(lg.userNodes, i, i+20);
        System.out.print(String.format("User %d and User %d distance is %d", i, i + 20, distance ));

    }

} 

/** 
* 
* Method: getNextDegreeFriend(int user_id_a, DWNode[] userNodes, Queue<Integer> queue, HashSet<Integer> visited, int degree) 
* 
*/ 
@Test
public void testGetNextDegreeFriend() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hasOverlap(HashSet<Integer> visited_a, HashSet<Integer> visited_b) 
* 
*/ 
@Test
public void testHasOverlap() throws Exception { 
//TODO: Test goes here... 
} 


} 
