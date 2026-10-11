package com.leslie.demo.sorting;

// expectation: good neighbors should choose the bigger one to the end
// for every element, compare it with the next element and if it is greater, swap it
public class SortingAlgorithm {
    public void bubbleSort(int[] arr) {
//        boolean hasSwapped = true;
//        while (hasSwapped) {
//            hasSwapped = false;
//            for (int i = 0; i < arr.length - 1; i++) {
//                if (arr[i] > arr[i + 1]) {
//                    // Swap adjacent elements
//                    int temp = arr[i];
//                    arr[i] = arr[i + 1];
//                    arr[i + 1] = temp;
//                    hasSwapped = true;
//                }
//            }
//        }
    }

    // expectation: the first part is sorted, the following part should be greater than the first part
    // for every element, compare it with the previous elements and if it is smaller, swap it
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentIndex = i;
            while (currentIndex > 0 && arr[currentIndex - 1] > arr[currentIndex]) {
                int temp = arr[currentIndex];
                arr[currentIndex] = arr[currentIndex - 1];
                arr[currentIndex - 1] = temp;
                currentIndex -= 1;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {5, 3, 8, 4, 2};
        new SortingAlgorithm().bubbleSort(arr);
        for (int i : arr)
            System.out.print(i + " ");
    }
}