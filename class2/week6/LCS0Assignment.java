package class2.week6;

public class LCS0Assignment {
	   int [][] table;
	   String x;
	   int count;

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

			return lcs(a, m - 1, b, n - 1);
		}

		// dynamic programming
		// assignment 
		public int lcsDP(String a, String b) {
			char[] m = a.toCharArray();
			char[] n = b.toCharArray();
			this.table = new int[m.length+1][n.length+1];

			return lcsDP(m, m.length, n, n.length);
		}

		// dynamic programming
		// assignment 
		private int lcsDP(char[] a, int m, char[] b, int n) {
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
			if (m == -1 || n == -1)
				return 0;
			else if (a.charAt(m) == b.charAt(n)) // 같다면
				return lcs(a, m - 1, b, n - 1) + 1; // 이전 결과물 + 1
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
			if (m == -1 || n == -1)
				return "";
			else if (a.charAt(m) == b.charAt(n)) // 같다면
				return lcss(a, m - 1, b, n - 1) + String.valueOf(a.charAt(m)); // 이전 결과물 + String
			else
				if(lcss(a, m - 1, b, n).length()>lcss(a, m, b, n - 1).length()) {
					return lcss(a, m - 1, b, n);
				}else {
					return lcss(a, m, b, n - 1);
				}
		}

	   public static void main(String[] args) {
	      String a= "abcdbcdad";
	      String b= "bcdadcb";
	      
	      LCS0Assignment s = new LCS0Assignment();
	      s.setVal(a);
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(b));
	      s.setCountReset();
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" DP is  :  "+s.lcsDP(a, b)+ " Count: " + s.getCount());
	      s.setCountReset();
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(a, b) + " Count: " + s.getCount());
	      s.setCountReset();
	      System.out.println("Longest Common Subsequence  String of  "+a+" and "+b+" is  :  "+s.lcss(a, b));
	      
	   }
	}