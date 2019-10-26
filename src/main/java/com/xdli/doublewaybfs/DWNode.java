package com.xdli.doublewaybfs;

import java.util.HashMap;
import java.util.HashSet;
import com.xdli.breadthfirstsearch.Node;

//Double Way BFS demo, node definition
public class DWNode extends Node {

    //扩展Node，增加degrees信息
    public HashMap<Integer, Integer> degrees;    // 存放从不同用户出发，当前用户结点是第几度

    // 初始化结点
    public DWNode(int id) {
        super(id);
        degrees = new HashMap<Integer, Integer>();
        degrees.put(id, 0);
    }

}
