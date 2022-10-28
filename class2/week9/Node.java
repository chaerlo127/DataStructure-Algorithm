package class2.week9;

class Node	{
	Node next;
	int data ;  

	public Node (int k) {
		next = null;
		data=k;
	}
	
	public String toString() {
		return ""+data;
	}
}