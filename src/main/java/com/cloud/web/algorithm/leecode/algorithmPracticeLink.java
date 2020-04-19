package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 链表部分(C语言)
 */
public class algorithmPracticeLink {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 删除某个节点
     */
    //public void deleteNode(ListNode node) {
    //    node.val = node.next.val;
    //    node.next = node.next.next;
    //    node = node.next;
    //}

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 删除链表的倒数第N个节点
     */
    //struct ListNode* removeNthFromEnd(struct ListNode*head, int n) {
    //    struct ListNode *p, *ptr = p = head;
    //    int i;
    //
    //    /*让指针p向前走n步*/
    //    while (n-- > 0) {
    //        p = p -> next;
    //    }
    //
    //    /*当指针p走完n步以后，让指针p和ptr同时向前走，直到p走到最后一个节点，即p->next=NULL，整个过程p和ptr之间相隔n-1个节点*/
    //    while (p && p -> next != NULL) {
    //        ptr = ptr -> next;
    //        p = p -> next;
    //    }
    //
    //    /*此时的ptr指向要删除节点的前一个节点，需要考虑删除的节点是否为首元节点*/
    //    if (p == NULL)
    //        return head = head -> next;
    //    else {
    //        ptr -> next = ptr -> next -> next;
    //        return head;
    //    }
    //}

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 删除链表的倒数第N个节点
     */
    //class Solution {
    //    public:
    //    ListNode*
    //    removeNthFromEnd(ListNode*head, int n) {
    //        struct ListNode*front = head;
    //        struct ListNode*behind = head;
    //        while (front != NULL) {
    //            front = front -> next; /*指针front往前移动n+1次*/
    //
    //            if (n-- < 0) behind = behind -> next;　　/*如果指针behind==0，表明需要删除的节点为第一个节点*/
    //        }
    //        /*循环过后，两个指针之间相隔n-1个节点*/
    //        if (n == 0) head = head -> next;
    //        else behind -> next = behind -> next -> next;
    //        return head;
    //    }
    //};

    public static void main(String[] args) throws Exception {


    }

}
