package class2.week7;

import java.util.HashSet;
import java.util.Set;

public class DisjointLinkedListPro {	
	
	DisjointLinkedListPro next;
	DisjointLinkedListPro head;
	String data;
	int weight;

	DisjointLinkedListPro tail;
	
	DisjointLinkedListPro() { 
		this.head = null;
		this.next = null;
		this.data = null;
		this.weight=0;
		tail=null;
	}
		
	public String toString() {
		return "" + data + "[" + weight + "]";
	}
		
	public boolean equals(DisjointLinkedListPro other) {
		if (data==other.data) 
			return true;
		else 
			return false;
		}
		
	public void showParent() {
		DisjointLinkedListPro node = this.head;
		System.out.print(node.toString());
		while(node.next != null) {
			node = node.next;
			System.out.print(" --> "+node.toString());
		}
		System.out.println();
	}
	
	public DisjointLinkedListPro makeSet(String k) {
		this.head = this;
		this.data = k;
		this.weight =1;
		this.tail=this;
		return this ;
	}
	
	public DisjointLinkedListPro findSet() {
		return this.head;
	}
	
	public DisjointLinkedListPro union(DisjointLinkedListPro other) {
		DisjointLinkedListPro a = this.head;  
		DisjointLinkedListPro b = other.head;
		
		if(a.weight >= b.weight) {
			System.out.println("--- union : "+ b.toString()+" > " +b.toString());

			a.tail.next = b;  // root를 a와 연결
			b.head=a;
			while(b.next != null) {
				b = b.next;
				b.head = a;
			}
			a.tail = b.tail;
			a.weight += b.weight;
			return this;
		} else {
			System.out.println("--- union : "+ a.toString()+" > " +b.toString());

			b.tail.next=a;
			a.head=b;
			while(a.next != null) {
				a= a.next;
				a.head = b;
			}
			b.tail = a.tail;
			b.weight += a.weight;
			return other;
		}
	}

	public static void main(String[] args) { 
		String [] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan","Mokpo", "Chuncheon", "Kyeongju"};
		int [][] distance = {{0,1,59},{0,2,140},{0,3,237},{0,7,313},{0,8,75},
							{1,7,295},{1,8,105},
							{2,3,122},{2,4,141},
							{3,4,173},{3,5,88},{3,6,74},{3,8,236},
							{4,5,202},{4,7,57},
							{5,9,76},
							{6,8,293},{6,9,36}};
		int n = distance.length; 
		
		// Sort distance array
		for (int i=0; i<n-1;i++)
			for (int j=i+1;j<n;j++) {
				if (distance[i][2]>distance[j][2]) {
					int a,b,c;
					a=distance[i][0]; b=distance[i][1]; c=distance[i][2];
					distance[i][0]=distance[j][0]; distance[i][1]=distance[j][1]; distance[i][2]=distance[j][2];
					distance[j][0]=a; distance[j][1]=b; distance[j][2]=c;
				}
			}

		System.out.println("<< Edge : Sorted List >>");
		for (int i=0;i<n;i++)
			System.out.println(i+" : "+distance[i][0]+"  "+distance[i][1]+"  "+distance[i][2]);
				
		DisjointLinkedListPro [] nodeSet = new DisjointLinkedListPro[cities.length];

		System.out.println("\n<< MakeSet >>");

		for (int i=0;i<cities.length;i++) {
			nodeSet[i]=new DisjointLinkedListPro();
			nodeSet[i]=nodeSet[i].makeSet(cities[i]); 
			
			nodeSet[i].showParent();
		}
		
		System.out.println("\n<< Union Processing... >>");

		Set<Integer> edgeSelected=new HashSet<>(); 
		
		for (int i=0;i<n;i++) {
			
			DisjointLinkedListPro tempA = nodeSet[distance[i][0]].findSet(); 
			DisjointLinkedListPro tempB = nodeSet[distance[i][1]].findSet();
			if (tempA!=tempB) { 
					edgeSelected.add(i);
					tempA.union(tempB);
					System.out.println(" >>> edge "+i+" : SELECTED ");
			}
			else {
				System.out.println(" xxx edge "+i+" : REJECTED ");
			}
		}
		
		System.out.println("\n<< Union Result >>");

		for (int i=0;i<cities.length; i++) {  
			nodeSet[i].showParent();
		}

		System.out.println("\n<< Edge Selected >>");
		for (int i : edgeSelected) {
			System.out.println(i+" : "+distance[i][0]+"  "+distance[i][1]+"  "+distance[i][2]);
		}
		
	}

}