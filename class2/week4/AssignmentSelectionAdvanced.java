package class2.week4;

public class AssignmentSelectionAdvanced {
	int recursiveCount =0;
	
	public void resetCount() {
		recursiveCount =0;
	}
	
	public int getCount() {
		return recursiveCount;
	}
	// 평균 선형시간 알고리즘
	// i 번쩨 작은 원소를 찾기
	public int select(int [] data, int p, int r, int i) {
		recursiveCount++;
		
		if (p>r) return -1;  // Invalid argument!
		if (p==r) return data[p]; // 원소가 하나 뿐인 경우를 의미
		
		int q = partition(data, p, r);
		int k = q-p; // 이렇게 빼면 q-1의 index를 갖게 됨.
		if (i<k) return select(data, p, q-1, i); // 왼쪽의 파트로 recursion
		else if (i==k)	return data[q]; //q는 k번째 작은 원소, k = q 여야 하므로 가장 마지막까지 가서 값을 찾음
		else return select(data, q+1, r, i-(q-p+1)); // i에서 k(q-p+1)만큼 뺀 것임. 전체 길이에서 k를 뺀 만큼 이후의 배열을 sorting 하기 때문
		// (q-p)+1 = k+1
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
//-----------------------------------------------------------

	// 최악의 경우 선형시간 선택 알고리즘
	// 나머지는 선형 시간 선택 알고리즘과 같고, Partition만 다름
	public int linearSelect(int [] data, int p, int r, int i) {
		recursiveCount++;
		
		if (p>r) return -1;  // Invalid argument!
		
		if (p==r)
			return data[p];
		
		int q = linearPartition(data, p, r);
		int k = q-p;
		if (i<k)
			return linearSelect(data, p, q-1, i);
		else if (i==k)
			return data[q];
		else
			return linearSelect(data, q+1, r, i-(q-p+1));
	}
 
	// 첫 번째 p는 무조건 0이고, r은 0부터 data.length -1 (데이터의 길이까지) 까지
	public int linearPartition(int[] data, int p, int r) {
		int medianValue = median(data, p, r); // M의 값 구하기
		// Median Value = 8 between 0 and 15
		System.out.println("\nMedian Value = "+medianValue+" between "+p+" and "+r);

		showData(data, p,r);

		int left = p;
		int right = r;

		while(true) {
			while(data[left]<medianValue && left<right) left++; // 
			while (data[right]>medianValue && left<right) right--;
			if (left<right) swapData(data, left, right);
			else break;
		}
		
		showData(data, p, r);
		
		return right;
	
	}
	
	private void showData(int [] data, int p, int r) {
		System.out.println();
		for (int i1=p; i1<=r;i1++) {
			System.out.print("--"+data[i1]);
		}
		System.out.println();

	}
	private int median(int[] data, int p, int r) {
		if ((r-p+1)<=5) // 전체의 길이가 5보다 작아서 따로 그룹으로 나누지 않아도 되는 경우
			return median5(data, p, r);
		float f = r-p+1; // 현재 전체 데이터의 개수
		int arrSize = (int) Math.ceil(f/5); // 나눠서 올림 처리를 한다.
		int [] medianArr = new int[arrSize]; 
		for (int i=0; i<arrSize; i++) {
			medianArr[i]=median5(data, p+5*i, (int)Math.min(p+5*i+4,r)); // 그룹 내에 있는 데이터를 sorting 해준다. 
		}
		return median(medianArr, 0, arrSize-1); 
		// sorting 된 것을 바탕으로 그룹의 median 값을 구함. 
		// -> 다시 median 값들을 sorting, 현재 arraySize는 기존의 데이터보다 1개가 더 크므로, -1를 해주고 다시 sorting
	}
	
	private int median5(int[] data, int p, int r) {
		if (p==r)
			return data[p];
		sort5(data, p, r);
		return data[p+ (int)((r-p)/2)];  // return value는 중앙 값
	}
	
	private void sort5(int[] data, int p, int r) {
		for (int i=p;i<r;i++)
			for (int j=i+1;j<=r;j++)
				if (data[i]>data[j]) 
					swapData(data, i, j);
		
	}
	
	public static void main(String[] args) {
		int [] data = {5, 27, 24, 6, 35, 3, 7, 8, 18, 71, 77, 9, 11, 32, 21, 4};
		
		AssignmentSelectionAdvanced s = new AssignmentSelectionAdvanced();
//		for (int i=0; i<data.length;i++)
//			System.out.print(s.select(data, 0, data.length-1, i)+"  "); // 0번째부터 호출함
//		System.out.println(); 
//		System.out.println("# of Recursive Calls of Select = "+s.getCount());
		
		s.resetCount();
		for (int i=0; i<data.length;i++)
			System.out.print(s.linearSelect(data, 0, data.length-1, i)+"  ");
		System.out.println(); 
		System.out.println("# of Recursive Calls of LinearSelect = "+s.getCount());
	}
}
