package class2.wweek12;

public class SCNode {
	int key;
	SCNode prev;
	SCNode next;

	public SCNode(int input) {
		key = input;
		prev = null;
		next = null;
	}
	
	public void addNext(SCNode other) {
		if (this.next!=null) {
			other.next=this.next; // 현재 next 를 other의 next로 변경
			this.next.prev=other; // next의 앞은 other로 변경
		}
		this.next=other; // next는 other이고
		other.prev=this; // other 앞은 현재 나임. 
	}
	public void addPrev(SCNode other) {
		if (this.prev!=null) {
			other.prev=this.prev;
			this.prev.next=other;
		}
		this.prev=other;
		other.next=this;
	}
	public SCNode delete() {   // 현재의 자신을 삭제 함. 
		SCNode ret = this;
		if (this.prev!=null) { 
			if (this.next!=null) { // 앞 뒤에 값이 다 있다면
				this.prev.next=this.next;
				this.next.prev=this.prev;
			}
			else
				this.prev.next=null; // 앞에는 값이 있고, 뒤에는 값이 없다면?
		}
		else { // 앞에 값이 없다면
			if (this.next!=null) // 뒤에 값이 있다면
				this.next.prev=null;
		}
		this.prev=null;
		this.next=null;
		return ret;
	}
	public String toString() {
		String ret = "";
		
		return prevString(this.prev)+" -> "+key+" -> "+nextString(this.next);
	}

	private String prevString(SCNode p) {
		if (p==null)
			return "";
		else return prevString(p.prev)+" -> "+p.key;
	}
	private String nextString(SCNode p) {
		if (p==null)
			return "";
		else return p.key+" -> "+nextString(p.next);
	}

}