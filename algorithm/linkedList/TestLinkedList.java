package com.chenjunyi.linkedList;

public class TestLinkedList {
    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        LinkedNode node1 = new LinkedNode("a", null);
//        LinkedNode node2 = new LinkedNode("b", null);
//        LinkedNode node3 = new LinkedNode("c", null);
//        LinkedNode node4 = new LinkedNode("d", null);
//
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);
//        singleLinkedList.add(node4);
//
//        singleLinkedList.show();

//        System.out.println(0xFFFF0000);
//        System.out.println(1 << 31);
//        long l = -2;
//        System.out.println(Long.toBinaryString(-2));
//        long l1 = l >>> 2;
//        System.out.println(Long.toBinaryString(l1));
//        System.out.println(Long.bitCount(l1));
//
//        System.out.println((long)-2 >>> 2);

 







    }

}

class SingleLinkedList {
    private LinkedNode head = new LinkedNode(null, null);

    public LinkedNode getHead() {
        return head;
    }

    public void setHead(LinkedNode head) {
        this.head = head;
    }

    /**
     * 添加节点, 将节点插入到链表尾部
     * 找到要当前链表的最后节点，将最后节点的next指向新节点
     * @param node
     */
    public void add(LinkedNode node) {
        LinkedNode current = head;

        // 遍历链表, 当退出循环时，
        while (true) {
            if (current.getNext() == null)
                break;
            current = current.getNext();
        }

        current.setNext(node);
    }

    /**
     * 根据名字找到位置
     * @param name
     * @return
     */
    public LinkedNode find(String name) {
        LinkedNode current = head;

        while (current.getNext() != null) {
            
            // 位置找到， 
            if (current.getName().compareTo(name) > 0) {
                
            }
        }
        return current;

    }



    /**
     * 按照name的排序插入节点
     * @param node
     */
    public void add1(LinkedNode node) {


    }

    /**
     * 打印链表
     *
     */
    public void show() {
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        LinkedNode current = head.getNext();

        while (true) {
            if (current == null) {
                break;
            }
            System.out.println(current);
            current = current.getNext();
        }
    }

}

class LinkedNode {

    private String name;
    private LinkedNode next;

    public LinkedNode(String name, LinkedNode next) {
        this.name = name;
        this.next = next;

    }

    public String  getName() {
        return name;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkedNode{" + "name='" + name + '\''  + '}';
    }
}
