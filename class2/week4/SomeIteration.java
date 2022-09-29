package class2.week4;

public class SomeIteration {


	public static void main(String[] args) {

		int n=6;  // suppose 6 by 6 matrix


		for (int j=1; j<=5; j++) {
			for (int i=0; i<=n-j-1;i++) {
				System.out.println((i)+"  "+(j+i));
				// ie., c[i][i+i]=Min(c[i][j-1], c[i+1][j])+ ,,,
			}
		}
		System.out.println();

		for (int j=1; j<=5; j++) {
			for (int i=j-1; i>=0;i--) {
				System.out.println((i)+"  "+(j));
				// ie., c[i][i+i]=Min(c[i][j-1], c[i+1][j])+ ,,,
			} 
		}


	}

}
