package class2.week6;

public class LCS0Assignment {
	   int [][] table;
	   String x;
	   int count;
	   int [][] dptable;

		public void setCountReset() {
			this.count = 0;
		}

		public int getCount() {
			return this.count;
		}

		public void setVal(String a) {
			this.x = a;
		}

		// ��ü ���� �ڵ�
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
			if (m == -1 || n == -1) // length�� -1�̸�
				return 0;
			else if (a.charAt(m) == b.charAt(n)) {// ���ٸ�, ������ ���ҵ��� ���ٸ�
				this.dptable[m][n] = lcsDP2(a, m - 1, b, n - 1) + 1;
				return this.dptable[m][n];
			}
			 // ���� ����� + 1, ���� ������� 1�� ������ ���� ���� ū ����
			else {
				dptable[m][n] = Math.max(lcsDP2(a, m - 1, b, n),lcsDP2(a, m, b, n-1));
				return this.dptable[m][n];
			} // ���� ����(����-1) �ϰ��� ���� ������ �����ϰ� �ٸ� ���� -1�� �� ��
		}
   
		// dynamic programming
		// assignment 
		public int lcsIterBU(String a, String b) {
			char[] m = a.toCharArray();
			char[] n = b.toCharArray();
			this.table = new int[m.length+1][n.length+1];

			return lcsIterBU(m, m.length, n, n.length);
		}

		// dynamic programming Iteration : Bottom - up ���
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
			if (m == -1 || n == -1) // length�� -1�̸� 
				return 0;
			else if (a.charAt(m) == b.charAt(n)) // ���ٸ�, ������ ���ҵ��� ���ٸ�
				return lcs(a, m - 1, b, n - 1) + 1; // ���� ����� + 1, ���� ������� 1�� ������ ���� ���� ū ����
			else
				return Math.max(lcs(a, m - 1, b, n), lcs(a, m, b, n - 1)); // ���� ����(����-1) �ϰ��� ���� ������ �����ϰ� �ٸ� ���� -1�� �� ��
		}
	   
		public String lcss(String a, String b) {
			int m = a.length();
			int n = b.length();

			return lcss(a, m - 1, b, n - 1);
		}

		// return String, ���� �� LCS ���� �� return
		private String lcss(String a, int m, String b, int n) {
			count++;
			if (m == -1 || n == -1) // base 
				return "";
			else if (a.charAt(m) == b.charAt(n)) // ���ٸ�
				return lcss(a, m - 1, b, n - 1) + String.valueOf(a.charAt(m)); // ���� ����� + String
			else {
				String tempA = lcss(a, m - 1, b, n);
				String tempB = lcss(a, m, b, n - 1);
				if (tempA.length() > tempB.length()) {
					return lcss(a, m - 1, b, n);
				} else {
					return lcss(a, m, b, n - 1);
				}
			}
		}
		
		public void showDPIteration() {
			for (int i=1 ; i<this.table.length; i++) { // 0�� �κ��� ǥ���� �ʿ䰡 ����
				for (int j=1; j<table[0].length; j++) {
					System.out.printf("%4d", table[i][j]);
				}
				System.out.println();
			}
		}
		
		public void showDPRecursion() {
			for (int i=1 ; i<this.dptable.length; i++) { // 0�� �κ��� ǥ���� �ʿ䰡 ����
				for (int j=1; j<dptable[0].length; j++) {
					System.out.printf("%4d", dptable[i][j]);
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
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(b) + " Count: " + s.getCount());
	      s.setCountReset();
	      // Iteration
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" DP is  :  "+s.lcsIterBU(a, b)+ " Count: " + s.getCount());
	      s.showDPIteration();
	      s.setCountReset();
	      // Recursion DP with DP Table -> Table�� ���� ���� ��. 
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" DP is  :  "+s.lcsDP2(a, b)+ " Count: " + s.getCount());
	     s.showDPRecursion();
	      s.setCountReset();
	      // Recursion DP
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(a, b) + " Count: " + s.getCount());
	      s.setCountReset();
	      // Recursion String
	      System.out.println("Longest Common Subsequence  String of  "+a+" and "+b+" is  :  "+s.lcss(a, b));
	      
	   }
	}