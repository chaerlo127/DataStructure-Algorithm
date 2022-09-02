package week2;

import java.util.Arrays;

public class ParamPassing {
	
	int[] data, newData;
	public ParamPassing(int[] array, int n) {
		data = array; // 갇은 포인터에 있는 assign 값을 변화시키면 main에서도 값이 변경된다.
		newData = Arrays.copyOf(array, array.length); 
		/*copy한 array에서는 array를 변경해도 main에서는 전혀 영향을 받지 않는다.
		 * 즉, array 변화가 main에서는 영향을 받지 않게 하려면, 위처럼 쓰면 안되고
		 * newdata 처럼 사용해야 하는 것이다.
		 */
		n=-1; // n을 변경해도 main에서는 전혀 영향을 받지 않는다. 
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
