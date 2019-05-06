package sort;

import java.util.Arrays;

/**
 * 排序方法总结
 *
 * @author Misaki
 * @date 2019/5/6/006 11:02
 **/
public class Sort {
    /**
     * 快速排序（挖坑法递归）
     *
     * @param arr  待排序数组
     * @param low  左边界
     * @param high 右边界
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        //挖坑1：保存基准的值
        int temp = arr[left];

        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }
            //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
            arr[right] = arr[left];
        }
        //基准值填补到坑3中，准备分治递归快排
        arr[left] = temp;
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 7, 5, 10, 9, 1, 6, 8, 2};
        quickSort(array, 0, array.length-1);
    }
}
