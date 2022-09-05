package class1.week7;

public class MyData {
	private String key; // �޶�� ��.
	private int value; // ���� �� ����
	
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
	
	//compareTo int : ũ��(1), ����(0), �۴�(-1)
	//compareTo String: ����(0), ���/������
	
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
