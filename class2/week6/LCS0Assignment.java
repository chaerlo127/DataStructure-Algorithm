package class2.week6;


// Not DP -> O(2^n)
// DP table -> O(mn)
public class LCS0Assignment {
	   int [][] table;
	   String x;
	   int count;
	   int [][] dptable;
	   String[][] dpStringTable;

		public void setCountReset() {
			this.count = 0;
		}

		public int getCount() {
			return this.count;
		}

		public void setVal(String a) {
			this.x = a;
		}

		// 객체 지향 코드
		public int lcs(String b) {
			int m = x.length();
			int n = b.length();

			return lcs(x, m - 1, b, n - 1);
		}

		public int lcs(String a, String b) {
			int m = a.length();
			int n = b.length();

			// String a, a.length -1, String b, b.length -1
			return lcs(a, m - 1, b, n - 1);
		}
		
		public int lcsDP2(String a, String b) {
			int m = a.length();
			int n = b.length();
			dptable = new int[m][n];
			// String a, a.length -1, String b, b.length -1
			return lcsDP2(a, m-1, b, n-1);
		}

		private int lcsDP2(String a, int m, String b, int n) {
			count++;//			
			if (m == -1 || n == -1) // length가 -1이면
				return 0;
			else if (a.charAt(m) == b.charAt(n)) {// 같다면, 마지막 원소들이 같다면
				if(this.dptable[m][n] == 0)	this.dptable[m][n] = lcsDP2(a, m - 1, b, n - 1) + 1;
				return this.dptable[m][n];
			}
			 // 이전 결과물 + 1, 이전 결과물에 1을 더해준 것이 가장 큰 길이
			else {
				dptable[m][n] = Math.max(lcsDP2(a, m - 1, b, n),lcsDP2(a, m, b, n-1));
				return this.dptable[m][n];
			} // 본인 제외(본인-1) 하고의 값과 본인을 포함하고 다른 것의 -1을 한 값
		}
   
		// Iteration
		// assignment 
		public int lcsIterBU(String a, String b) {
			char[] m = a.toCharArray();
			char[] n = b.toCharArray();
			this.table = new int[m.length+1][n.length+1];

			return lcsIterBU(m, m.length, n, n.length);
		}

		// dynamic programming Iteration : Bottom - up 방식
		// assignment 
		private int lcsIterBU(char[] a, int m, char[] b, int n) {
			for(int i = 0; i<=m ; i++) {
				for(int j = 0 ; j <= n; j++) {
					count++;
					if(i == 0 || j == 0) {
						this.table[i][j] = 0;
					} else if(a[i-1] == b[j-1]) {
						this.table[i][j] = this.table[i-1][j-1] + 1;
					} else {
						this.table[i][j] = Math.max(this.table[i][j-1], this.table[i-1][j]);
					}
				}
			}
			return this.table[m][n];
		}
	
		private int lcs(String a, int m, String b, int n) {
			count++;
			if (m == -1 || n == -1) // length가 -1이면 
				return 0;
			else if (a.charAt(m) == b.charAt(n)) // 같다면, 마지막 원소들이 같다면
				return lcs(a, m - 1, b, n - 1) + 1; // 이전 결과물 + 1, 이전 결과물에 1을 더해준 것이 가장 큰 길이
			else
				return Math.max(lcs(a, m - 1, b, n), lcs(a, m, b, n - 1)); // 본인 제외(본인-1) 하고의 값과 본인을 포함하고 다른 것의 -1을 한 값
		}
	   
		public String lcss(String a, String b) {
			int m = a.length();
			int n = b.length();

			return lcss(a, m - 1, b, n - 1);
		}

		// return String, 가장 긴 LCS 문자 값 return
		private String lcss(String a, int m, String b, int n) {
			count++;
			if (m == -1 || n == -1) // base 
				return "";
			else if (a.charAt(m) == b.charAt(n)) // 같다면
				return lcss(a, m - 1, b, n - 1) + String.valueOf(a.charAt(m)); // 이전 결과물 + String
			else {
				String tempA = lcss(a, m - 1, b, n);
				String tempB = lcss(a, m, b, n - 1);
				if (tempA.length() > tempB.length()) {
					return tempA;
				} else {
					return tempB;
				}
			}
		}
		
		// String return DP Table
		public String lcssDP(String a, String b) {
			int m = a.length();
			int n = b.length();
			this.dpStringTable = new String [m][n];
			return lcssDP(a, m - 1, b, n - 1);
		}

		private String lcssDP(String a, int m, String b, int n) {
			count++;
			if (m == -1 || n == -1) // base
				return "";
			else if (a.charAt(m) == b.charAt(n)) {
				if(this.dpStringTable[m][n] == null) this.dpStringTable[m][n] = lcssDP(a, m - 1, b, n - 1) + String.valueOf(a.charAt(m)); // 이전 결과물 + String
				return this.dpStringTable[m][n];// 같다면
			}
			else {
				String tempA = lcssDP(a, m - 1, b, n);
				String tempB = lcssDP(a, m, b, n - 1);
				if (tempA.length() > tempB.length()) {
					dpStringTable[m][n] = tempA;
				} else {
					dpStringTable[m][n] = tempB;
				}
				return dpStringTable[m][n];
			}
		}
		
		public void showDPIteration() {
			for (int i=1 ; i<this.table.length; i++) { // 0인 부분을 표현할 필요가 없음
				for (int j=1; j<table[0].length; j++) {
					System.out.printf("%4d", table[i][j]);
				}
				System.out.println();
			}
		}
		
		
		public void showDPRecursion() {
			for (int i=1 ; i<this.dptable.length; i++) { // 0인 부분을 표현할 필요가 없음
				for (int j=1; j<dptable[0].length; j++) {
					System.out.printf("%4d", dptable[i][j]);
				}
				System.out.println();
			}
		}
		
		public void showDPStringRecursion() {
			for (int i=1 ; i<this.dpStringTable.length; i++) { // 0인 부분을 표현할 필요가 없음
				for (int j=1; j<dpStringTable[0].length; j++) {
					System.out.printf("%4s \t", dpStringTable[i][j]);
				}
				System.out.println();
			}
		}
		public static void main(String[] args) {
			String a = "abcdbcdad";
			String b = "bcdadcb";

	      LCS0Assignment s = new LCS0Assignment();
	      

	
	      s.setVal(a);
	      // Recursion DP
//	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(b) + " Count: " + s.getCount());
//	      s.setCountReset();
	      // Iteration
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" Iteration is  :  "+s.lcsIterBU(a, b)+ " Count: " + s.getCount());
	      s.showDPIteration(); // Iteration
	      s.setCountReset();
	      // Recursion DP with DP Table -> Table에 값을 저장 함. 
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" DP is  :  "+s.lcsDP2(a, b)+ " Count: " + s.getCount());
	     s.showDPRecursion();
	      s.setCountReset();
	      // Recursion DP
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" Recursion is  :  "+s.lcs(a, b) + " Count: " + s.getCount());
	      s.setCountReset();
	      // Recursion String
	      System.out.println("Longest Common Subsequence  String of  "+a+" and "+b+" Recursion String is  :  "+s.lcss(a, b) + " Count: " + s.getCount());
	      s.setCountReset();
	      System.out.println("Longest Common Subsequence  String of  "+a+" and "+b+" DP String is  :  "+s.lcssDP(a, b) + " Count: " + s.getCount());
	      s.showDPStringRecursion();
	   }
	}