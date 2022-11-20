package class2.wweek12;

public class SCNode {
	int key;
	SCNode prev;
	SCNode next;
	public SCNode(int input) {
		key=input;
		prev=null;
		next=null;
	}
	
	public void addNext(SCNode other) {
		if (this.next!=null) {
			other.next=this.next;
			this.next.prev=other;
		}
		this.next=other;
		other.prev=this;
	}
	public void addPrev(SCNode other) {
		if (this.prev!=null) {
			other.prev=this.prev;
			this.prev.next=other;
		}
		this.prev=other;
		other.next=this;
	}
	public SCNode delete() {
		SCNode ret = this;
		if (this.prev!=null) {
			if (this.next!=null) {
				this.prev.next=this.next;
				this.next.prev=this.prev;
			}
			else
				this.prev.next=null;
		}
		else {
			if (this.next!=null) 
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