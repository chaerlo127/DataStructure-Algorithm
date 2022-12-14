package class2.week4;

import java.util.HashSet;
/**
 * @author chaerlo127
 * Hashing Algorithm
 * 같은 index의 값을 가지고 있으면 list 내의 index LinkedList 
 * 맨 뒤 혹은 맨 앞에 key 값을 저장한다.
 *
 */
//  여기서는 각 method가 nOfHops를 return 함
// 갑이 커질 수록 Chaining이 더 효율적임.
// worst case: O(n) -> search
public class Chaining {
	
	private class HashNode {
		int key;
		HashNode next;
		public HashNode(int k) {
			key=k;
			next=null;
		}
		public String toString() {
			return "->"+key;
		}	
	}
	
	int nOfHops=0;  // 위치를 찾기 위해 이동한 횟수 count 
	
	HashNode [] hTable;
	int m;
	
	public Chaining(int n) {
		m = n;
		hTable = new HashNode[m];
		for (int i=0; i<m; i++)
			hTable[i]=null;  
	}
	private int hashFunction(int d) {
		// 정수인 아닌 경우에는  정수로 만드는 절차의 정의 필요
//		// 곱하기방법
//		double temp = (double)value * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int)(res*m);
		// 나누기 방법
		return d%m;
	}	
	
//	Insert : hTable[index]에는 하나도 저장하지 말고 모두 linkedlist로 구성
	public int insert(int d) {
		int index = hashFunction(d);
		HashNode newNode = new HashNode(d);
//		 hTable[index]의 맨 앞으로 insert
		newNode.next = hTable[index];
		hTable[index]=newNode;
		
		nOfHops=1;
		return nOfHops;  // numberOfHops =1 always..
	}

//	Search
	public int search(int d) {
		int index = hashFunction(d);
		HashNode p = hTable[index];
		nOfHops=1;
		while(p!=null) {
			if (p.key==d)
				return nOfHops;
			else {
				nOfHops++;
				p=p.next;
			}
		}
		return -nOfHops; // -nOfHops= 검색 실패시 조사 횟수
	}
	
//	Delete 
	public int delete(int d) {
		int index = hashFunction(d);
		HashNode p = hTable[index];
		nOfHops=1;
		
		if (p==null)
			return -nOfHops;
		else if (p.key==d) {
			hTable[index]=p.next;
			return nOfHops;
		}
		HashNode q = p.next; // 지우고자 하는 key를 갖고 있는 객체
		nOfHops++;
		while (q!=null) {
		
			if (q.key==d) {
				p.next = q.next;
				return nOfHops;
			}
			else {
				p=q;
				q=q.next;
				nOfHops++;
			}
		}
		return -nOfHops; 
	}
	
	public void showTable() {
		System.out.println("\n\n<< Current Table Status >>");
		for (int i=0;i<m; i++) {
			HashNode p = hTable[i];
			System.out.print("\n "+i+" : ");
			while(p!=null) {
				System.out.print(p.toString());
				p=p.next;
			}
		}
	}
	

	public static void main(String[] args) {
		int dataSize = 50;
		int maxKeyValue = 100000;
		int HashTableSize = 57;
		
		int [] data = new int [dataSize];
		// 서로 다른 데이터 30개를 만들자
		HashSet<Integer> rdata = new HashSet<Integer>();
		while (rdata.size()<dataSize) {
			rdata.add((int)(Math.random()*maxKeyValue));
		}
		int k=0;
		for (int d : rdata) {
			data[k]=d;
			k++;
		}
		
		Chaining mh = new Chaining(HashTableSize);

		System.out.println("\n *** Chaining ***");
		int testcase =30;
		System.out.println("<< Insert >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.insert(data[i]));
		}
		
		mh.showTable();
		
		System.out.println("<< Search >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.search(data[i]));
		}
		System.out.println("<< Delete >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.delete(data[i]));
		}
	}
}
