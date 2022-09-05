package class1.week1;

public class Euclidean {
	// 최대공약수 구하기 algorithm
		public static void main(String[] args) {
			
			//Iteration
			int a = 424, b = 38;
			int r;

			do {
				if (a < b) {
					int temp = a;
					a = b;
					b = temp;
				}
				r = a % b;
				if (r == 0) {
					System.out.println("Answer= " + b);
					break;
				}
				a = b;
				b = r;
			} while (true);
			
			a = 424;
			b = 38;
			
			//Recursion
			int gcd = greatestCommonDivisor(a,b);
			System.out.println("Answer= " + gcd);
		}

	private static int greatestCommonDivisor(int a, int b) {
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		int r = a % b;
		if (r == 0) {
			return b;
		}else return greatestCommonDivisor(b,r);

	}

}
