package com.xdli.doublewaybfs;

import java.util.HashMap;
import java.util.HashSet;

//Double Way BFS demo, node definition
public class DWNode {

    public int user_id;		// 结点的名称，这里使用用户 id
    public HashSet<Integer> friends = null;
    // 使用哈希映射存放相连的朋友结点。哈希便于确认和某个用户是否相连。
    public int degree;		// 用于存放和给定的用户结点，是几度好友
    public HashMap<Integer, Integer> degrees;    // 存放从不同用户出发，当前用户结点是第几度

    // 初始化结点
    public DWNode(int id) {
        user_id = id;
        friends = new HashSet<Integer>();
        degree = 0;
        degrees = new HashMap<Integer, Integer>();
        degrees.put(id, 0);
    }



}
