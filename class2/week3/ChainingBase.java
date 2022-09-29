package class2.week3;

import java.util.HashSet;

public class ChainingBase {

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
	
	public ChainingBase(int n) {
		m = n;
		hTable = new HashNode[m];
		for (int i=0; i<m; i++)
			hTable[i]=null;  
	}
	private int hashFunction(int d) {
		// ������ ���
		return d%m;
	}	
	
//	Insert : hTable[index]���� �ϳ��� �������� ���� ��� linkedlist�� ����
//  ���⼭�� �� method�� nOfHops�� return ��

	public int insert(int d) {
		int index = hashFunction(d);
		HashNode newNode = new HashNode(d);
		// hTable[index]�� �� ������ insert�ϴ� code�� �߰��Ͻÿ�.
		if(hTable[index]==null) {
			hTable[index]= newNode;
		}else {
			newNode.next = hTable[index];
			hTable[index] = newNode;
		}
		/////////////////////////////////////
		nOfHops=1;
		return nOfHops;  // numberOfHops =1 always..
	}

//	Search
	public int search(int d) {
		int index = hashFunction(d);
		HashNode p = hTable[index];
		nOfHops=1;
		while(p!=null) {
			// p�� link�� ���󰡸� p.key==d �� ã��, 
			// link�� ���� ������ nOfHops�� ������Ű�� code�� �߰��Ͻÿ�.
			if(p.key == d) break;
			else p = p.next;
			nOfHops++;
			/////////////////////////////////////
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
			// p�� link�� ���󰡸� p.key==d �� ã�� �ش� node�� �����ϰ�, 
			// link�� ���� ������ nOfHops�� ������Ű�� code�� �߰��Ͻÿ�.
			if(q.key == d) {
				p.next = q.next;
				break;
			}else q = q.next;
			nOfHops++;
			/////////////////////////////////////
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

		int dataSize = 30;
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
		
		ChainingBase mh = new ChainingBase(HashTableSize);

		System.out.println("\n *** Chaining ***");
		int testcase =20;
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
