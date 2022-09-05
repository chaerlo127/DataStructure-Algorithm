package class1.week1;

public class Fibornacci {
	static int count = 0;
	
	public static void main(String[] args) {
		int n = 10;
		int result = 0, r1 = 1, r2 = 1;
		for(int i = 3; i<=n; i++) {
			result = r1 + r2;
			r1 = r2;
			r2  = result;
			count ++;
		}
		System.out.println(result + ", count: "+ count);
		
		count = 0;
		result = fibo(n);
		System.out.println(result + ", count: "+ count);
		
	}

	private static int fibo(int n) {
		count++;
		if(n<=2) {
			return 1;
		}else return fibo(n-1) + fibo(n-2);
	}

}
