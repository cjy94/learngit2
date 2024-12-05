package com.chenjunyi.gaoji二.gaoji1;

/**
 * 相机问题
 */
public class CameraProblem {

    public static class Info{
        long coverHasCamera;  // x被覆盖，x有相机
        long coverNoCamera;   // x被覆盖，但是x没有相机
        long uncover;  // x没相机，x也没有被覆盖的情况

        Info(long coverHasCamera, long coverNoCamera, long uncover) {
            this.coverHasCamera = coverHasCamera;
            this.coverNoCamera = coverNoCamera;
            this.uncover = uncover;
        }
    }

    public static class Node{
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    public static enum Status{
        UNCOVERED, COVERED_HAS_CAMERA, COVERED_NO_CAMERA;
    }

    public static class Info1{
        Status status;
        long camera;
        Info1(Status s, long camera) {
            this.status = s;
            this.camera = camera;
        }
    }

    public static int minCameraCover1(Node root) {
        Info data = process(root);
        return (int) Math.min(data.uncover + 1,
                Math.min(data.coverNoCamera, data.coverHasCamera));
    }

    public static int minCameraCover2(Node root) {
        Info1 data = process1(root);
        return (int) data.camera;
    }


    public static Info1 process1(Node x) {
       if (x == null) {
           return new Info1(Status.COVERED_NO_CAMERA, 0);
       }
       Info1 left = process1(x.left);
       Info1 right = process1(x.right);
       long camera = left.camera + right.camera;
       if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
           return new Info1(Status.COVERED_HAS_CAMERA, camera+1);
       }

        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Info1(Status.COVERED_NO_CAMERA, camera);
        }


        return new Info1(Status.UNCOVERED, camera);


    }

    // 将所有可能情况全都罗列出来
    public static Info process(Node x) {
        // 空节点， 不需要放相机，天然被覆盖
        if (x == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }

        Info left = process(x.left);
        Info right = process(x.right);

//        long uncover;
//        long coverHasCamera;
//        long coverNoCamera;

        // x系欸按不被覆盖， 那么左右孩子节点一定得被覆盖，并且不能有相机
        long uncover =  left.coverNoCamera + right.coverNoCamera;

        // x节点没有相机被覆盖的情况是， 左右 孩子都放置相机； 或者左孩子放相机，右孩子不放相机； 或者左孩子不放相机，右孩子放相机
        long  coverNoCamera = Math.min(
                // 左右孩子都放置相机， x节点被覆盖，
                (left.coverHasCamera + right.coverHasCamera),
                Math.min(

                        // 左孩子有相机，右孩子没有相机，x节点被覆盖
                        (left.coverHasCamera + right.coverNoCamera),
                        // 左孩子没有相机，右孩子有相机，x节点被覆盖
                        (left.coverNoCamera+ right.coverHasCamera)));
        

        // x 节点放相机， 那么左右孩子节点什么状态都可以，
        // 左树中所有状态中的最小值， 右树中所有状态取最小值
        long coverHasCamera = Math.min(
                left.uncover,
                Math.min(
                        left.coverHasCamera,
                        left.coverNoCamera)
                )
                +
                 Math.min(
                 right.uncover,
                 Math.min(
                         right.coverHasCamera,
                         right.coverNoCamera))
                 + 1;

        return new Info(coverHasCamera, coverNoCamera, uncover);
    }




}
