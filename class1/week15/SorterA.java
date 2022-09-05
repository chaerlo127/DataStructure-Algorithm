package class1.week15;
import java.util.Random;

public class SorterA {
	int [] input;
	int[] data;
	public SorterA(int [] data) {
		this.data = data;
		input=new int[data.length];
		System.arraycopy(data, 0, input, 0, data.length);
	}
	
	public int[] sort() {
		//위에 attribute로 data를 작성하였습니다. data.length 대신 숫자 30으로 적어도 가능합니다.!
		// return quickSort(0, 30-1);
		return quickSort(0, data.length-1);  // arguments는 각자 정의하여 추가
	}

	private int[] quickSort(int p, int r) {
//  final exam-1  : 필요한 private method는 각자 정의 및 구현
		if(p<r) {
			int q = partition(p, r);
			quickSort(p, q-1);
			quickSort(q+1, r);
		}
		return input;
	}
	private int partition(int p, int r) {
		int pivot = r;
		int left=p;
		int right=r;
		
		while(left<right) {
			while((input[left]<(input[pivot])) && left<right) {
				left++;
			}
			while((input[right]>input[pivot]) && left<right) {
				right--;
			}
			if (left<right)
				swap(left, right);
		}
		swap(pivot, right);
		return right;
	}

	private void swap(int left, int right) {
		int swap = input[left];
		input[left] = input[right];
		input[right] = swap;
	}

	public static void main(String[] args) {
		int max=30;
		Random rand = new Random(7);
		int[] data = new int [max];

		System.out.println("<Before Sorting>");
		for (int i=0;i<max;i++) {
			data[i]=rand.nextInt(1000);
			System.out.printf("%5d", data[i]);
		}
		System.out.println();
		
		SorterA s = new SorterA(data);
		int [] sortedData= s.sort();
		System.out.println("<After Sorting>");
		for (int i=0;i<max;i++) {
			System.out.printf("%5d", sortedData[i]);
		}
	}

}

