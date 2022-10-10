package class2.week6;

public class LCS0 {
	   
	   String x;
	   public void setVal(String val) {
	      x=val;
	   }
	   
	   // ��ü ���� �ڵ�
	   public int lcs(String b) {
	      int m = x.length();
	      int n = b.length();
	      
	      return lcs(x, m-1, b, n-1);
	   }
	   
	   public int lcs(String a, String b) {
	      int m = a.length();
	      int n = b.length();
	      
	      return lcs(a, m-1, b, n-1);
	   }

	   private int lcs(String a, int m, String b, int n) {
	      if (m==-1 || n==-1) 
	         return 0;
	      else if (a.charAt(m)==b.charAt(n)) // ���ٸ�
	         return lcs(a, m-1, b, n-1)+1; // ���� ����� + 1
	      else
	         return Math.max(lcs(a, m-1, b, n), lcs(a, m, b, n-1)); // ���� ����(����-1) �ϰ��� ���� ������ �����ϰ� �ٸ� ���� -1�� �� ��
	   }
	   
	   public String lcss(String a, String b) {
	      int m = a.length();
	      int n = b.length();
	      
	      return lcss(a, m-1, b, n-1);
	   }

	   // return String, ���� �� LCS ���� �� return
	   private String lcss(String a, int m, String b, int n) {

	      return null;
	   }

	   public static void main(String[] args) {
	      String a= "abcdbcdad";
	      String b= "bcdadcb";
	      
	      LCS0 s = new LCS0();
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(b));
	      
	      s.setVal(a);
	      System.out.println("Longest Common Subsequence  Length of  "+a+" and "+b+" is  :  "+s.lcs(a, b));
	      System.out.println("Longest Common Subsequence  String of  "+a+" and "+b+" is  :  "+s.lcss(a, b));
	      
	   }
	}