package class2.week4;

import java.util.HashSet;

//  ���⼭�� �� method�� nOfHops�� return ��
// ���� Ŀ�� ���� Chaining�� �� ȿ������.
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
	
	int nOfHops=0;  // ��ġ�� ã�� ���� �̵��� Ƚ�� count 
	
	HashNode [] hTable;
	int m;
	
	public Chaining(int n) {
		m = n;
		hTable = new HashNode[m];
		for (int i=0; i<m; i++)
			hTable[i]=null;  
	}
	private int hashFunction(int d) {
		// ������ �ƴ� ��쿡��  ������ ����� ������ ���� �ʿ�
//		// ���ϱ���
//		double temp = (double)value * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int)(res*m);
		// ������ ���
		return d%m;
	}	
	
//	Insert : hTable[index]���� �ϳ��� �������� ���� ��� linkedlist�� ����
	public int insert(int d) {
		int index = hashFunction(d);
		HashNode newNode = new HashNode(d);
//		 hTable[index]�� �� ������ insert
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
		return -nOfHops; // -nOfHops= �˻� ���н� ���� Ƚ��
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
		HashNode q = p.next;
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
		// ���� �ٸ� ������ 30���� ������
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
