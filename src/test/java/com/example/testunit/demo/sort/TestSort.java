package com.example.testunit.demo;

import org.junit.Test;

/**
 * 排序测试类
 */
public class TestSort {

    /**
     * 选择排序，数组对象在堆内存中的位置对换
     */
    @Test
    public void choseSort() {
        int[] arr = {7, 2, 4, 1, 5, 3, 6};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int a : arr) {
            System.out.println(a);
        }
    }

    /**
     * 冒泡排序，减少数组对象在堆内存中的位置对换
     */
    @Test
    public void bubbleSort() {
        int[] arr = {7, 2, 4, 1, 5, 3, 6};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int a : arr) {
            System.out.println(a);
        }
    }

}
