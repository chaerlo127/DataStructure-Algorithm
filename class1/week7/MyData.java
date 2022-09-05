package class1.week7;

public class MyData {
	private String key; // 달라야 함.
	private int value; // 같을 수 있음
	
	public MyData() {
		
	}
	
	public MyData(String s, int v) {
		this.key = s;
		this.value = v;
	}
	
	public boolean equals(MyData that) {
		if(this.key == that.key) {
			return true;
		}
		else return false;
	}
	
	//compareTo int : 크다(1), 같다(0), 작다(-1)
	//compareTo String: 같다(0), 양수/음수값
	
	public int compareTo(MyData that) {
		if(this.key.compareTo(that.key)>0) {
			return 1;
		}else if(this.key.compareTo(that.key)<0) return -1;
		else return 0;
	}
	
	public String toString() {
		return "" +"(" +this.key+","+value+") " ;
	}
	
}
