package com.xdli.doublewaybfs;

import java.io.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.csv.*;

import java.util.*;

public class FriendRelGraph {

    public int userNum = 0;
    public int relationNum = 0;
    public DWNode[] userNodes = null;

    public FriendRelGraph(int user_num, int relation_num, String filepath) {

        userNum = user_num;
        relationNum = relation_num;
        userNodes = new DWNode[user_num];

        // 生成所有表示用户的结点
        for (int i = 0; i < user_num; i++) {
            userNodes[i] = new DWNode(i);
        }

        initializeRelShips(filepath);
    }

    protected void initializeRelShips(String filePath) {

        //CSV文件换行符
        final String NEW_LINE_SEPARATOR="\n";

        try {
            //CSV文件生成
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");

            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("id_a", "id_b");
            csvFormat.withRecordSeparator(NEW_LINE_SEPARATOR);

            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

            Random rand = new Random();

            // 生成所有表示好友关系的边
            for (int i = 0; i < this.relationNum; i++) {
                int friend_a_id = rand.nextInt(userNum);
                int friend_b_id = rand.nextInt(userNum);
                if (friend_a_id == friend_b_id) continue;
                // 自己不能是自己的好友。如果生成的两个好友 id 相同，跳过
                DWNode friend_a = userNodes[friend_a_id];
                DWNode friend_b = userNodes[friend_b_id];

                friend_a.friends.add(friend_b_id);
                friend_a.degrees.put(friend_b_id, 1);
                friend_b.friends.add(friend_a_id);
                friend_b.degrees.put(friend_a_id, 1);
                System.out.println("Relationship added for user ID: " + friend_a_id + " and " + friend_b_id);
                csvPrinter.printRecord(new String[]{Integer.toString(friend_a_id), Integer.toString(friend_b_id)});
            }

            csvPrinter.flush();
            csvPrinter.close();

        }catch (Exception e){
            e.printStackTrace();
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

        //计算当前queue中的节点数（当前degree的所有节点数）
        int size = queue.size();
        HashMap<Integer, Integer> degrees = userNodes[user_id_a].degrees;

        //遍历当前level的节点
        for (int i =0; i < size; i++){
            int currentID = queue.poll();
            //将当前level节点的子节点放入visited当中
            for (int friend_id: userNodes[currentID].friends){
                if (userNodes[friend_id] == null) continue;
                if (visited.contains(friend_id)) continue;
                queue.offer(friend_id);
                visited.add(friend_id);	// 记录已经访问过的结点
                degrees.put(friend_id, degree);
                System.out.println(String.format("\t%d 度好友：%d",  userNodes[friend_id].degree, friend_id));
            }
        }
    }

    public static Boolean hasOverlap(HashSet<Integer> visited_a, HashSet<Integer> visited_b){

        for (int visited_a_id: visited_a){
            if (visited_b.contains(visited_a_id)) return true;
        }
        return false;
    }

}