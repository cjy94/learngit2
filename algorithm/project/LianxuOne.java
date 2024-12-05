package com.chenjunyi.project;

/**
 *  连续1的个数，3个连续的1是一个大卡车，2个连续的1是一个火车，1个连续的1是小车
 *  要最少的车数量
 */
public class LianxuOne {
    public static void main(String[] args) {
        int[] arr = {1,0,1};
        System.out.println(compute(arr));
    }

    /**
     * 1个1 是小车
     * 2个1 是货车
     * 3个1 是卡车
     */
    public static int compute(int[] arr) {
        int sum = 0;
        int oneCount = 0;
        int index = 0;
        int len = arr.length;
        while (index < len) {
            if (arr[index] == 1) {
                oneCount++;

            } else {  // 结算连续1的个数
                int bigCars = oneCount/3;
                int middleCar = (oneCount - (bigCars * 3) ) / 2;
                int smallCars = (oneCount - (bigCars * 3) - (middleCar * 2));
                sum += bigCars + middleCar + smallCars;
                oneCount = 0;
            }
            index++;
        }

        if (oneCount > 0) {
            int bigCars = oneCount/3;
            int middleCar = (oneCount - (bigCars * 3) ) / 2;
            int smallCars = (oneCount - (bigCars * 3) - (middleCar * 2));
            sum += bigCars + middleCar + smallCars;
        }
        return sum;

    }
}
