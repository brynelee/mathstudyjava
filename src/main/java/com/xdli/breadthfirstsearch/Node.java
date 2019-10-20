package com.xdli.breadthfirstsearch;

import java.util.HashSet;

public class Node {

    public int user_id;		// 结点的名称，这里使用用户 id
    public HashSet<Integer> friends = null;
    // 使用哈希映射存放相连的朋友结点。哈希便于确认和某个用户是否相连。
    public int degree;		// 用于存放和给定的用户结点，是几度好友

    // 初始化结点
    public Node(int id) {
        user_id = id;
        friends = new HashSet<Integer>();
        degree = 0;
    }
}
