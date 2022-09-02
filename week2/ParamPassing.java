package week2;

import java.util.Arrays;

public class ParamPassing {
	
	int[] data, newData;
	public ParamPassing(int[] array, int n) {
		data = array; // ���� �����Ϳ� �ִ� assign ���� ��ȭ��Ű�� main������ ���� ����ȴ�.
		newData = Arrays.copyOf(array, array.length); 
		/*copy�� array������ array�� �����ص� main������ ���� ������ ���� �ʴ´�.
		 * ��, array ��ȭ�� main������ ������ ���� �ʰ� �Ϸ���, ��ó�� ���� �ȵǰ�
		 * newdata ó�� ����ؾ� �ϴ� ���̴�.
		 */
		n=-1; // n�� �����ص� main������ ���� ������ ���� �ʴ´�. 
	}
	
	public void change1() {
		for(int  i =0;i<newData.length;i++) {
			newData[i] = - newData[i];
		}
	}
	
	public void change2() {
		for(int i=0; i< data.length;i++) {
			data[i]=-data[i];
		}
	}

	public static void main(String[] args) {
		int[] arr1 = {1, 2, 3, 4, 5};
		int newLen=10;
		
		ParamPassing me = new ParamPassing(arr1, newLen);
		printArray("0", arr1);
		System.out.println("n = " + newLen);
		
		me.change1();
		printArray("1", arr1);
		System.out.println("n = " + newLen);
		
		me.change2();
		printArray("2", arr1);
		System.out.println("n = " + newLen);

	}
	
	public static void printArray(String c, int[] arr) {
		System.out.println("-- Case " + c + " : ");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
