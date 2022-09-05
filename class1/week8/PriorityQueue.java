package class1.week8;

import java.util.LinkedList;

// 3번 문제용

public class PriorityQueue {

////////////////////////////////////////////////////////////////
//학번 : 60201976
//이름: 장채은
////////////////////////////////////////////////////////////////
	
	
	
	private LinkedList<MyData> ll;
	
	public PriorityQueue() {
		this.ll = new LinkedList<MyData>();
	}

	
	public  void add(MyData myData) {
		ll.add(myData);
	}


	public void addPriority(MyData myData) {
		for(int i = ll.size()-1; i>=0; i--) {
			if(ll.get(i).compareTo(myData)> 0 && !ll.contains(myData)) {
				ll.add(i, myData);
			}
		}

	}
		



	public void showQueue() {
		System.out.println(ll.toString());
	}

	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();
		
		pq.add(pq.new MyData(3, "ddd"));
		pq.addPriority(pq.new MyData(1, "paaa"));
		pq.add(pq.new MyData(1, "aaa"));
		pq.addPriority(pq.new MyData(5, "pccc"));
		pq.add(pq.new MyData(2, "bbb"));
		pq.add(pq.new MyData(5, "ccc"));
		pq.addPriority(pq.new MyData(2, "pbbb"));
		pq.addPriority(pq.new MyData(3, "pddd"));
		pq.showQueue();
		
		// ==> pccc(5) ddd(3) paaa(1) aaa(1) bbb(2) ccc(5) pddd(3) pbbb(2) 	
	}
	

	public class MyData{
		int priority;
		String name;
		MyData(int p, String s){
			priority=p;
			name=s;
		}
		public boolean equals(MyData that) {
			if (this.name==that.name)
				return true;
			else
				return false;
		}
		int compareTo(MyData that) {
			if (this.priority>that.priority)
				return 1;
			else if (this.priority<that.priority)
				return -1;
			else
				 return 0;
		}
		public String toString() {
			return name+"("+priority+")";
		}
	}
	
	

}
