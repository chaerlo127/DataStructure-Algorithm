package class1.week8;

public class NodeDL {
	 MyData data;
	 NodeDL left, right;
	
	public NodeDL() {}
	public NodeDL(MyData d) {
		this.data = d;
		this.left = null;
		this.right = null;
	}
	
	public NodeDL(String key, int value) {
		data = new MyData(key, value);
	}
	
	public String toString() {
		return this.data.toString();
	}
}
