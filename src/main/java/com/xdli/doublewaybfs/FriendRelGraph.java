package com.xdli.doublewaybfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FriendRelGraph {

    public int userNum = 0;
    public int relationNum = 0;
    public DWNode[] userNodes = null;

    public FriendRelGraph(int user_num, int relation_num) {
        userNum = user_num;
        relationNum = relation_num;
        userNodes = new DWNode[user_num];

        Random rand = new Random();

        // 生成所有表示用户的结点
        for (int i = 0; i < user_num; i++) {
            userNodes[i] = new DWNode(i);
        }

        // 生成所有表示好友关系的边
        for (int i = 0; i < relation_num; i++) {
            int friend_a_id = rand.nextInt(user_num);
            int friend_b_id = rand.nextInt(user_num);
            if (friend_a_id == friend_b_id) continue;
            // 自己不能是自己的好友。如果生成的两个好友 id 相同，跳过
            DWNode friend_a = userNodes[friend_a_id];
            DWNode friend_b = userNodes[friend_b_id];

            friend_a.friends.add(friend_b_id);
            friend_b.friends.add(friend_a_id);
            System.out.println("Relationship added for user ID: " + friend_a_id + " and " + friend_b_id);
        }
    }


    /**
     * @param user_nodes-用户的结点；user_id_a-用户a的ID；user_id_b-用户b的ID
     * @return void
     * @Description: 通过双向广度优先搜索，查找两人之间最短通路的长度
     */
    public static int bi_bfs(DWNode[] user_nodes, int user_id_a, int user_id_b) {

        if (user_id_a > user_nodes.length || user_id_b > user_nodes.length) return -1;  // 防止数组越界的异常

        if (user_id_a == user_id_b) return 0;    // 两个用户是同一人，直接返回0

        Queue<Integer> queue_a = new LinkedList<Integer>();  // 队列a，用于从用户a出发的广度优先搜索
        Queue<Integer> queue_b = new LinkedList<Integer>();  // 队列b，用于从用户b出发的广度优先搜索

        queue_a.offer(user_id_a);    // 放入初始结点
        HashSet<Integer> visited_a = new HashSet<Integer>();  // 存放已经被访问过的结点，防止回路
        visited_a.add(user_id_a);

        queue_b.offer(user_id_b);    // 放入初始结点
        HashSet<Integer> visited_b = new HashSet<Integer>();  // 存放已经被访问过的结点，防止回路
        visited_b.add(user_id_b);

        int degree_a = 0, degree_b = 0, max_degree = 20;    // max_degree的设置，防止两者之间不存在通路的情况

        while ((degree_a + degree_b) < max_degree) {
            degree_a++;
            getNextDegreeFriend(user_id_a, user_nodes, queue_a, visited_a, degree_a);
            // 沿着a出发的方向，继续广度优先搜索degree + 1的好友
            if (hasOverlap(visited_a, visited_b)) return (degree_a + degree_b);
            // 判断到目前为止，被发现的a的好友，和被发现的b的好友，两个集合是否存在交集

            degree_b++;
            getNextDegreeFriend(user_id_b, user_nodes, queue_b, visited_b, degree_b);
            // 沿着b出发的方向，继续广度优先搜索degree + 1的好友
            if (hasOverlap(visited_a, visited_b)) return (degree_a + degree_b);
            // 判断到目前为止，被发现的a的好友，和被发现的b的好友，两个集合是否存在交集

        }

        return -1;
        // 广度优先搜索超过max_degree之后，仍然没有发现a和b的重叠，认为没有通路
    }

    public static void getNextDegreeFriend(int user_id_a, DWNode[] userNodes, Queue<Integer> queue, HashSet<Integer> visited, int degree){

    }

    public static Boolean hasOverlap(HashSet<Integer> visted_a, HashSet<Integer> visted_b){

        return false;
    }

}