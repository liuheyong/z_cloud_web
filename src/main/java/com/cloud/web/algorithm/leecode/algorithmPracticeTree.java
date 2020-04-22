package com.cloud.web.algorithm.leecode;

import java.util.*;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 树部分
 */
public class algorithmPracticeTree {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 二叉树的最大深度
     */
    //class Solution {
    //    public:
    //    int maxDepth(TreeNode* root) {
    //        if(!root) return 0;
    //        return 1 + max(maxDepth(root->left),maxDepth(root->right));
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 验证二叉搜索树
     */
    //class Solution {
    //    public:
    //    bool isValidBST(TreeNode *root) {
    //        if (!root) return true;
    //        TreeNode *cur = root, *pre, *parent = NULL;
    //        bool res = true;
    //        while (cur) {
    //            if (!cur->left) {
    //                if (parent && parent->val >= cur->val) res = false;
    //                parent = cur;
    //                cur = cur->right;
    //            } else {
    //                pre = cur->left;
    //                while (pre->right && pre->right != cur) pre = pre->right;
    //                if (!pre->right) {
    //                    pre->right = cur;
    //                    cur = cur->left;
    //                } else {
    //                    pre->right = NULL;
    //                    if (parent->val >= cur->val) res = false;
    //                    parent = cur;
    //                    cur = cur->right;
    //                }
    //            }
    //        }
    //        return res;
    //    }
    //};

    static class TreeNode {

        int key;
        TreeNode left;
        TreeNode right;

        public TreeNode(int key) {
            left = null;
            right = null;
            this.key = key;
        }
    }

    //建立一颗二叉树
    public static List<TreeNode> createTree() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<TreeNode> nodeList = new ArrayList<>();
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new TreeNode(array[nodeIndex]));
        }
        //对LastParentIndex-1个父节点按照父节点和子节点的关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            //左孩子
            nodeList.get(parentIndex).left = nodeList.get(parentIndex * 2 + 1);
            //右孩子
            nodeList.get(parentIndex).right = nodeList.get(parentIndex * 2 + 2);
        }
        //最后一个父节点，可能存在没有右孩子的情况，所以拿出来单独处理
        int lastParentIndex = array.length / 2 - 1;
        //左孩子
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        //右孩子，如果长度为奇数则建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }
        return nodeList;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 前序遍历, 递归方式
     */
    public static void preOderTraverse(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.key + " ");
        preOderTraverse(node.left);
        preOderTraverse(node.right);
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 中序遍历, 递归方式
     */
    public static void inOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        inOrderTraverse(node.left);
        System.out.print(node.key + " ");
        inOrderTraverse(node.right);

    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 后序遍历, 递归方式
     */
    public static void postOrdeTraverse(TreeNode node) {
        if (node == null)
            return;
        postOrdeTraverse(node.left);
        postOrdeTraverse(node.right);
        System.out.print(node.key + " ");
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 层序遍历
     */
    public static void LayerTraverse(TreeNode node) {
        if (node == null)
            return;
        LinkedList<TreeNode> mList = new LinkedList<>();
        mList.add(node);
        TreeNode currentNode;
        while (!mList.isEmpty()) {
            currentNode = mList.poll();
            System.out.print(currentNode.key);
            if (currentNode.left != null) {
                mList.add(currentNode.left);
            }
            if (currentNode.right != null) {
                mList.add(currentNode.right);
            }
        }
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 层序遍历
     */
    //public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    //    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    //    if (root == null) return result;
    //    Queue<TreeNode> queue = new LinkedList<>();
    //    queue.offer(root);
    //    while (queue.isEmpty() == false) {
    //        ArrayList<Integer> list = new ArrayList<>();
    //        int size = queue.size();
    //        for (int i = 0; i < size; i++) {
    //            TreeNode curNode = queue.poll();
    //            list.add(curNode.val);
    //            if (curNode.left != null) queue.offer(curNode.left);
    //            if (curNode.right != null) queue.offer(curNode.right);
    //        }
    //        result.add(list);
    //    }
    //    return result;
    //}

    public static void main(String[] args) throws Exception {

        LayerTraverse(createTree().get(0));

        //preOderTraverse(createTree().get(0));

    }

}
