package com.cloud.web.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: LiuHeYong
 * @create: 2020-03-23
 * @description: 图算法篇
 */
public class graphAlgorithm {

    public graphAlgorithm() {
    }

    /**
     * @Date: 2020-04-27
     * @Param:
     * @return:
     * @Description: 广度优先搜索
     */
    private static void bfs(HashMap<Character, LinkedList<Character>> graph, char start, HashMap<Character, Integer> dist) {
        Queue<Character> q = new LinkedList<>();
        q.add(start);//将s作为起始顶点加入队列
        dist.put(start, 0);
        int i = 0;
        while (!q.isEmpty()) {
            char top = q.poll();//取出队首元素
            i++;
            System.out.println("The " + i + "th element:" + top + " Distance from s is:" + dist.get(top));
            int d = dist.get(top) + 1;//得出其周边还未被访问的节点的距离
            for (Character c : graph.get(top)) {
                if (!dist.containsKey(c))//如果dist中还没有该元素说明还没有被访问
                {
                    dist.put(c, d);
                    q.add(c);
                }
            }
        }
    }

    /**
     * @Date: 2020-04-27
     * @Param:
     * @return:
     * @Description: 深度优先搜索
     */
    private static void dfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
        visit(graph, visited, 'u');//为了和图中的顺序一样，我认为控制了DFS先访问u节点
        visit(graph, visited, 'w');
    }

    private static int count = 0;

    private static void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited, char start) {
        if (!visited.containsKey(start)) {
            count++;
            System.out.println("The time into element " + start + ":" + count);//记录进入该节点的时间
            visited.put(start, true);
            for (char c : graph.get(start)) {
                if (!visited.containsKey(c)) {
                    visit(graph, visited, c);//递归访问其邻近节点
                }
            }
            count++;
            System.out.println("The time out element " + start + ":" + count);//记录离开该节点的时间
        }
    }

    /**
     * @Date: 2020-04-27
     * @Param:
     * @return:
     * @Description: 广度优先搜索实现拓补排序
     */
    //vector<DirectedGraphNode*> topSort(vector<DirectedGraphNode*> graph) {
    //    vector<DirectedGraphNode*> ret;
    //    if(graph.empty())
    //        return ret;
    //
    //    map<DirectedGraphNode*,int> in; //in为入度
    //    queue<DirectedGraphNode*>   s;  //保存入度为零的节点
    //    for(auto e:graph){
    //        for(auto i:e->neighbors)
    //            ++in[i];              //记录每个节点的入度
    //    }
    //
    //    for(auto e:graph)
    //        if(0==in[e])
    //            s.push(e);         //入度为零的节点入栈
    //
    //    while(!s.empty()){        //BFS遍历,搜寻入度为零的节点
    //        DirectedGraphNode* cur=s.front();
    //        s.pop();             //当前节点出栈时，它的相邻节点入度都减一
    //        ret.push_back(cur);
    //        for(auto e:cur->neighbors){
    //            if(--in[e]==0)    //减一后为零则入栈
    //                s.push(e);
    //        }
    //    }
    //    return ret;
    //}

    public static void main(String[] args) {

    }

}
