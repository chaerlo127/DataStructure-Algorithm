package class2.week4;

// 평균 선형시간 알고리즘
public class SelectionBase {
	
	public int select(int [] data, int p, int r, int i) { // i는 sort 후에 위치되는 index, k = q - p (p에서 q까지의 길이)
		
//		for (int i1=0; i1<data.length;i1++) {
//			System.out.print("--"+data[i1]);
//		}
//		System.out.println();

		
		int q = partition(data, p, r);
		System.out.println("returned q= "+q+" <== "+i);
		if (i<(q-p)) return select(data, p, q-1, i);
		else if (i==(q-p)) return data[q];
		else return select(data, q+1, r, i+p-q-1); // i에서 k만큼 뺀 것임. 전체 길이에서 k를 뺀 만큼 이후의 배열을 sorting 하기 때문
	}
	
	private int partition(int[] data, int p, int r) {
		int pivot = r;   // <=== r  ie. random pivot selection
		
		int left = p; 
		int right = r;
		
		while(true) {
			while(data[left]<data[pivot] && left<right) left++;
			while (data[right]>=data[pivot] && left<right) right--;
			if (left<right) swapData(data, left, right);
			else break;
		}
		swapData(data, pivot, right);
		return right;
		
	}	
	
	private void swapData(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;	
	}

	public static void main(String[] args) {
		int [] data = {15,3,11,9,12,2,6,8};
		
		SelectionBase s = new SelectionBase();
		// index 0부터 data 배열 끝까지 중에 i 번째 있는 데이터를 찾아서 return 해라.
		for (int i=0; i<data.length;i++)
			System.out.println(">>> "+i+ "  ==> "+s.select(data, 0, data.length-1, i)+"  "); // 0번째부터 호출함
		// >>> 0  ==> 2 : index 0에는 2가 저장되어 있고, index 1에는 3이 저장 되어 있음. 
		System.out.println(); 
		
	}
}
