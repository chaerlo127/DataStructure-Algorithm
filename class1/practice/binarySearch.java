package practice;


public class binarySearch {
	private int[] data;
	private int count;
	public binarySearch(int[] input) {
		this.data = input;
	}
	public void resetCount() {
		count = 0;
	}
	public int getCount() {
		return this.count;
	}
	public int binarySearch(int p, int r, int x) {
		count ++;
		 if(p>r) {
			 return -1;
		 }
		 int m = (p+r)/2;
		 if(x>data[m]) {
			 return binarySearch(m+1, r, x);
		 }else if(x<data[m]) {
			 return binarySearch(p, m-1, x);
		 }else {
			 return m;
		 }
	}
	public static void main(String[] args) {
		int[] input = {1, 3, 5, 7, 9, 11, 14, 18, 20, 22, 26, 30, 32, 35, 41, 46, 55};
		binarySearch me = new binarySearch(input);
		me.resetCount();
		System.out.println(me.binarySearch(0, input.length, 26)+" count = "+me.getCount());

	}
}
