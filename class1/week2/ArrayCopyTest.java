package class1.week2;

import java.util.Arrays;

public class ArrayCopyTest {

	public static void main(String[] args) {
		int[] arr1 = new int[] {1, 2, 3, 4, 5};
		int newLen = 10;
		printArray(0, arr1);
		
		// 1. System Ŭ������ arrayCopy() �޼ҵ�
		int[] arr2 = new int[newLen];
		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		printArray(1, arr2);
		
		// 2. Arrays Ŭ������ copyOf() �޼ҵ�
		int[] arr3 = Arrays.copyOf(arr1, newLen);
		printArray(2, arr3);
		
		// 3. Object Ŭ������ clone() �޼ҵ�
		int[] arr4 = (int[])arr1.clone();
		printArray(3, arr4);
		
		// 4. for ���� �ε����� �̿��� ����
		int[] arr5 = new int[newLen];
		
		for(int i = 0; i< arr1.length; i++) {
			arr5[i] = arr1[i];
		}
		
		printArray(4, arr5);
	}
	
	private static void printArray(int c, int[] arr) {
		System.out.println("-- Case" + c + ":");
		for(int i = 0;i<arr.length; i++) {
			System.out.println(arr[i] + " ");
		}
		System.out.println();
	}
}
