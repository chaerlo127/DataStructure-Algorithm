package class2.week4;

// ��� �����ð� �˰���
public class SelectionBase {
	
	public int select(int [] data, int p, int r, int i) { // i�� sort �Ŀ� ��ġ�Ǵ� index, k = q - p (p���� q������ ����)
		
//		for (int i1=0; i1<data.length;i1++) {
//			System.out.print("--"+data[i1]);
//		}
//		System.out.println();

		
		int q = partition(data, p, r);
		System.out.println("returned q= "+q+" <== "+i);
		if (i<(q-p)) return select(data, p, q-1, i);
		else if (i==(q-p)) return data[q];
		else return select(data, q+1, r, i+p-q-1); // i���� k��ŭ �� ����. ��ü ���̿��� k�� �� ��ŭ ������ �迭�� sorting �ϱ� ����
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
		// index 0���� data �迭 ������ �߿� i ��° �ִ� �����͸� ã�Ƽ� return �ض�.
		for (int i=0; i<data.length;i++)
			System.out.println(">>> "+i+ "  ==> "+s.select(data, 0, data.length-1, i)+"  "); // 0��°���� ȣ����
		// >>> 0  ==> 2 : index 0���� 2�� ����Ǿ� �ְ�, index 1���� 3�� ���� �Ǿ� ����. 
		System.out.println(); 
		
	}
}
