package com.xdli.deepfirstsearch;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Stack;

public class DemoTree {

    public TreeNode root;

    public DemoTree(char rootChar){
        root = new TreeNode(rootChar, "", "");
    }

    public Boolean addOneWord(String newWord){
        
        Boolean result = false;

        if (newWord == null) {
            System.out.println("Error, The new word is null...");
            return result;
        }

        TreeNode parent = root;
        String pre = root.prefix;

        for (int i = 0; i < newWord.length(); i++) {
            // 处理当前字符串的第一个字母
            char c = newWord.toCharArray()[i];

            // 如果字母结点已经存在于当前父结点之下，找出它。否则就新生成一个
            if (parent.sons.containsKey(c)) {
                if (i == newWord.length() - 1) {
                    System.out.println("the word has already been in the tree.");
                }
                parent = parent.sons.get(c);
                pre = parent.prefix + c;
            } else {
                TreeNode son = new TreeNode(c, pre, "");
                parent.sons.put(c, son);
                pre += c;
                parent = son;
                if (i == newWord.length() - 1) {
                    result = true;
                }
            }
        }

        if (result){
            System.out.println("The new word " + newWord + " has been successfully added to the tree.");
        }

        return result;
    }

    // 使用栈来实现深度优先搜索
    public void dfsByStack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<TreeNode>(); 
            // 创建堆栈对象，其中每个元素都是 TreeNode 类型
        stack.push(root);		// 初始化的时候，压入根结点

        while (!stack.isEmpty()) {	// 只要栈里还有结点，就继续下去
            
            TreeNode node = stack.pop();	// 弹出栈顶的结点
            
            if (node.sons.size() == 0) {
                // 已经到达叶子结点了，输出
                System.out.println(node.prefix + node.label);
            } else {
                // 非叶子结点，遍历它的每个子结点
                Iterator<Entry<Character, TreeNode>> iter 
                    = node.sons.entrySet().iterator();
                
                // 注意，这里使用了一个临时的栈 stackTemp
                // 这样做是为了保持遍历的顺序，和递归遍历的顺序是一致的
                // 如果不要求一致，可以直接压入 stack
                Stack<TreeNode> stackTemp = new Stack<TreeNode>();
                while (iter.hasNext()) {
                    stackTemp.push(iter.next().getValue());
                }
                while (!stackTemp.isEmpty()) {
                    stack.push(stackTemp.pop());
                }
            }
        }

    }	

}