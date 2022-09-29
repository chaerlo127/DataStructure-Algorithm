package class2.week4;

public class AssignmentSelectionAdvanced {
	
	int recursiveCount =0;
	
	public void resetCount() {
		recursiveCount =0;
	}
	public int getCount() {
		return recursiveCount;
	}
	
	public int select(int [] data, int p, int r, int i) {
		recursiveCount++;
		
		if (p>r) return -1;  // Invalid argument!
			
		if (p==r)
			return data[p];
		
		int q = partition(data, p, r);
		int k = q-p;
		if (i<k)
			return select(data, p, q-1, i);
		else if (i==k)
			return data[q];
		else
			return select(data, q+1, r, i-(q-p+1));
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

	public int linearPartition(int[] data, int p, int r) {
		int medianValue = median(data, p, r);
		
		System.out.println("\nMedian Value = "+medianValue+" between "+p+" and "+r);

		showData(data, p,r);

		int left = p;
		int right = r;

		while(true) {
			while(data[left]<medianValue && left<right) left++;
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
		if ((r-p+1)<=5)
			return median5(data, p, r);
		float f = r-p+1;
		int arrSize = (int) Math.ceil(f/5);
		int [] medianArr = new int[arrSize];
		for (int i=0; i<arrSize; i++) {
			medianArr[i]=median5(data, p+5*i, (int)Math.min(p+5*i+4,r));
		}
		return median(medianArr, 0, arrSize-1);
	}
	
	private int median5(int[] data, int p, int r) {
		if (p==r)
			return data[p];
		sort5(data, p, r);
		return data[p+ (int)((r-p)/2)];  // return value
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
