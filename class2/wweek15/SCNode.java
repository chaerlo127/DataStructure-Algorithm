package class2.wweek15;

public class SCNode {
	String key;
	int attr;
	SCNode prev;
	SCNode next;
	public SCNode(String s, int num) {
		key = s;
		attr=num;
		prev=null;
		next=null;
	}
	
	public String get() {
		return this.key;
	}
	public int getAttr() {
		return this.attr;
	}
	public void set(String s) {
		this.key=s;
	}
	public void set(String s, int num) {
		this.key=s;
		this.attr=num;
	}
	
	public boolean equals(SCNode other) {
		return this.key.equals(other.key);
	}
	
	public SCNode search(String d) {

		SCNode p=this;
		while(p!=null) {
			if (p.key.equals(d))
				return p;
			else
				p=p.next;
		}
		p=this.prev;
		while(p!=null) {
			if (p.key.equals(d))
				return p;
			else
				p=p.prev;
		}
		return null;
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

	public void delete() {  // to delete myself
		if (this.prev!=null) {
			if (this.next!=null) {
				this.prev.next=this.next;
				this.next.prev=this.prev;
			}
			else
				this.prev.next=null;
		}
		else 	if (this.next!=null) {
				this.next.prev=null;
		}
	}
	
	public String toString() {
		return ""+this.key+"["+attr+"]";
	}

}