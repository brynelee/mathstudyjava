package com.xdli.breadthfirstsearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class LinkageGraph {

    public int userNum = 0;
    public int relationNum = 0;
    public Node[] userNodes = null;

    public LinkageGraph(int user_num, int relation_num){
        userNum = user_num;
        relationNum = relation_num;
        userNodes = new Node[user_num];

        Random rand = new Random();

        // 生成所有表示用户的结点
        for (int i = 0; i < user_num; i++) {
            userNodes[i] = new Node(i);
        }

        // 生成所有表示好友关系的边
        for (int i = 0; i < relation_num; i++) {
            int friend_a_id = rand.nextInt(user_num);
            int friend_b_id = rand.nextInt(user_num);
            if (friend_a_id == friend_b_id) continue;
            // 自己不能是自己的好友。如果生成的两个好友 id 相同，跳过
            Node friend_a = userNodes[friend_a_id];
            Node friend_b = userNodes[friend_b_id];

            friend_a.friends.add(friend_b_id);
            friend_b.friends.add(friend_a_id);
            System.out.println("Relationship added for user ID: " + friend_a_id + " and " + friend_b_id);
        }
    }

    /**
     * 使用队列Queue的先进先出方法来排队处理所有需要处理的节点，因为是先进先出，所以优先处理平级的邻居节点，后处理下一层次的节点（广度优先）
     * @Description:	通过广度优先搜索，查找好友
     * @param user_id - 用户的结点；user_id- 给定的用户 ID，我们要为这个用户查找好友
     * @return void
     */

    public void bfs(int user_id) {

        if (user_id > userNodes.length) return;	// 防止数组越界的异常

        Queue<Integer> queue = new LinkedList<Integer>();	// 用于广度优先搜索的队列

        queue.offer(user_id);		// 放入初始结点
        HashSet<Integer> visited = new HashSet<Integer>();	// 存放已经被访问过的结点，防止回路
        visited.add(user_id);

        while (!queue.isEmpty()) {
            int current_user_id = queue.poll();		// 拿出队列头部的第一个结点
            if (userNodes[current_user_id] == null) continue;

            // 遍历刚刚拿出的这个结点的所有直接连接结点，并加入队列尾部
            for (int friend_id : userNodes[current_user_id].friends) {
                if (userNodes[friend_id] == null) continue;
                if (visited.contains(friend_id)) continue;
                queue.offer(friend_id);
                visited.add(friend_id);	// 记录已经访问过的结点
                userNodes[friend_id].degree = userNodes[current_user_id].degree + 1;		// 好友度数是当前结点的好友度数再加 1
                System.out.println(String.format("\t%d 度好友：%d",  userNodes[friend_id].degree, friend_id));
            }
        }

    }

}
