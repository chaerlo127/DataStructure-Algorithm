package class2.week2;

// 시간 복잡도 : O(1)
public class Fibonacci {

	int count;
	int [] fibo = new int[50];
	public void reset() {
		this.count = 0;
		for(int i = 3; i<50; i++) {
			fibo[i] = 0;
		}
		fibo[1] = 1; fibo[2] = 1;
	}
	public int getCount() {
		return count;
	}
	public int fiboRec(int n) {
		count ++;
		if(n <=2) {
			return 1;
		}
		return fiboRec(n-1) + fiboRec(n-2);
	}
	public int fiboIter(int n) {
		for(int i = 3; i<=n; i++) {
			count ++;
			fibo[i] = fibo[i-1]+ fibo[i-2];
		}
		return fibo[n];
	}
	
	// dynamic programming
	public int fiboDP(int n) {
		count++;
		if(fibo[n]!=0) {
			return fibo[n];
		}else {
			fibo[n] = fiboDP(n-1) + fiboDP(n-2);
			return fibo[n];
		}
	}
	
	public void showDP() {
		for(int i = 0; i<fibo.length; i++) {
			System.out.print(fibo[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		int c0, c1, c2;
		// compare f.fiboIter(n) & count, f.fiboRec(n) & count, f.fiboDP(n) & count
		for (int n = 3; n<30; n++) {
			f.reset();
			f.fiboIter(n);
			c0 = f.getCount();
			f.reset();
			f.fiboRec(n);
			c1 = f.getCount();
			f.reset();
			f.fiboDP(n);
			c2 = f.getCount();
			System.out.printf("Iteration = %-5d ==> Recursion = %-10d ==> Memoization = %-10d%n", c0, c1, c2);
//			f.showDP();
		}

	}

}
