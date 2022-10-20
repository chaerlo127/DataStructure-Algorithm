package class2.week4;

import java.util.Arrays;
import java.util.HashSet;

public class AssignmentOpenAddressingDouble {

	int nOfHops=0;
	
	int [] hTable;
	int m; // table size
	
	int numberOfItems;
	double threshold = 0.7; 
	
	public AssignmentOpenAddressingDouble(int tableSize) {
		m=tableSize;
		hTable = new int [m];
		Arrays.fill(hTable, -1); // htable �� ��� ����ҿ� -1�� ����.
		
		numberOfItems = 0;
	}

	public double loadfactor() {
		return (double)numberOfItems/m;
	}
	
	private int hashFunction2(int value) {
		// ���ϱ���
		return (value%m);
	}
	
	private void enlargeTable() {
		int [] oldTable =hTable;
		int oldSize = m;
		m *= 2;                  // table ����Ҹ� �ι� �÷��ش�.
		hTable = new int[m];
		for (int i=0;i<m; i++)
			hTable[i]=-1;
		numberOfItems=0;
		for (int i =0; i<oldSize;i++) { // rehashing
			if (oldTable[i]>=0)   // -999?
				doubleInsert(oldTable[i]);
		}
	}
	
	
	public int doubleInsert(int d) {
		if (loadfactor()>=threshold) {
			System.out.println(">>>  Hash Table is Enlarged !!");
			System.out.print(">>>  Table Size : "+m+", Load Factor : "+loadfactor());
			enlargeTable();
			System.out.println("  ==>  Table Size : "+m+", Load Factor : "+loadfactor());
		}
		int index = hashFunction(d);
		nOfHops =1;
		if (hTable[index]==-1) {
			hTable[index]=d;
			numberOfItems++;
//			return nOfHops;
			return index;
		}
		else { // Collision
			System.out.println("Collision! at "+index);

			nOfHops++;
			int nOfCollisions = 1;
			int probeIndex = (index + nOfCollisions * hashFunction2(d)) % m;
			while (hTable[probeIndex] != -1 && hTable[probeIndex] != -999) {
				System.out.println("nCollision= " + nOfCollisions + "  probeIndex= " + probeIndex);

				nOfCollisions++;
				nOfHops++;
				probeIndex = (index + nOfCollisions * hashFunction2(d)) % m;
				
				if (probeIndex==index) return -99999;  
					 
			}
			hTable[probeIndex]=d;
			numberOfItems++;
//			return nOfHops;
			return probeIndex;

		}
	}

	public int doubleSearch(int d) {
		int index = hashFunction(d);
		nOfHops =1;
		if (hTable[index]==d) {
			return nOfHops;
		}
		else { // Collision
			nOfHops++;
			int nOfCollisions = 1;
			int probeIndex  = (index+ nOfCollisions * hashFunction2(d))%m;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=d) {
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+ nOfCollisions * hashFunction2(d))%m;

				if (probeIndex==index) return -99999;  
			}
			if (hTable[probeIndex]==d)
				return nOfHops;
			else
				return -nOfHops;
		}
	}
	
	public int doubleDelete(int d) {
		int index = hashFunction(d);
		nOfHops =1;
		if (hTable[index]==d) {
			hTable[index]=-999;
			numberOfItems--;
			return nOfHops;
		}
		else { // Collision
			nOfHops++;
			int nOfCollisions = 1;
			int probeIndex  = (index+ nOfCollisions * hashFunction2(d))%m;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=d) {
				nOfCollisions++;
				nOfHops++;
				probeIndex = (index + nOfCollisions * hashFunction2(d)) % m;

				if (probeIndex==index) return -99999;  // �ѹ����� ���Ҵµ��� �������� ����
			}
			if (hTable[probeIndex]==d) {
				hTable[index]=-999;
				numberOfItems--;
				return nOfHops;
			}
			else
				return -nOfHops;
		}
	}
//  -------------------------------------------------------------------------------------
	private int hashFunction(int value) {
		// ��������
		return (value%m);
	}	
	
	public int insert(int value) {
//		return quadInsert(value);
		int index = hashFunction(value);
		nOfHops =1;
		
		if (hTable[index]!=-1) {
			System.out.println("Collision! at "+index);
		// open addressing-linear
		// �浹�� �߻��ϸ� index���� �ϳ��� ������Ű�鼭 ����� ã�� insert
		// �� ���� ã���� insert�ϰ� �� index�� return 
		// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
		// ó�� ��ġ���� �� ���� ��ã���� -777�� return
			int i = 1;
			int probeIndex = (index + i * hashFunction2(value)) % m;
			nOfHops++;
			
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {  
				System.out.println("Collision= "+index+"  probeIndex= "+probeIndex);
				nOfHops++;
				i++;
				probeIndex = (probeIndex + i * hashFunction2(value)) % m;
				
				if (probeIndex==index) return -777;  
			}
			index=probeIndex;
		}
		hTable[index]=value;
		return index;

	}
	
	public int search(int value) {
//		return quadSearch(value);
		int index = hashFunction(value);
		nOfHops =1;

		if (value==hTable[index])
			return index;

		else {
			// �浹�� �߻��ϸ� insert�� ���� ������ �Ȱ��� �����Ͽ� �ش� ���� ����� index�� ã�ư�
			// �ش� ���� ã���� �� index�� return 
			// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
			// ó�� ��ġ�� ���ƿ� ������ ��ã���� -888�� return --> �������� Ȯ�εǸ� -888�� �ƴ϶� -nOfHops�� return�ϵ��� ����
			System.out.println("Search new location of "+index);
			int i = 1;
			int probeIndex = (index + i * hashFunction2(value)) % m;
			nOfHops++;

			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex + i * hashFunction2(value)) % m;
				if (probeIndex==index)  // �ѹ����� �� ���Ҵµ� ��ã��
					return -8888;  		// Not found�� �ǹ�
			}
			if (hTable[probeIndex]==value)
				return probeIndex;
			else
				return -888;  // Not found�� �ǹ�
		}
	}
	
	public int delete(int value) {
//		return quadDelete(value);
		int index = hashFunction(value);
		nOfHops =1;
		if (value==hTable[index]) {
			hTable[index]=-999; // -1 ==> -999
			return index;
		}
		else {
			// �浹�� �߻��ϸ� insert�� ���� ������ �Ȱ��� �����Ͽ� �ش� ���� ����� index�� ã�ư�
			// �ش� ���� ã���� ���� ����� (�ϴ� -1�� ��ħ), �� index�� return 
			// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
			// ó�� ��ġ�� ���ƿ� ������ ��ã���� -888�� return --> �������� Ȯ�εǸ� -888�� �ƴ϶� -nOfHops�� return�ϵ��� ����
			System.out.println("Search new location of "+index+" to delete");
			int i = 1;
			int probeIndex = (index + i * hashFunction2(value)) % m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex + i * hashFunction2(value)) % m;
				if (probeIndex==index)
					return -888;  // Not found�� �ǹ�
			}
			if (hTable[probeIndex]==value) {
				hTable[probeIndex]=-999; // -1 ==> -999
				return probeIndex;
			}
			else
				return -888; // Not found�� �ǹ�
		}
		
	}

	public static void main(String[] args) {
		
		long beforeTime = System.currentTimeMillis();
		int dataSize = 1000;
		int maxKeyValue = 100000;
		int HashTableSize = 57;
		
		int [] data = new int [dataSize];
		// ���� �ٸ� ������ 1000���� ����� ���� Set���� �����ϸ� ���ϴ� ������ ����� ����
		// data[]�� �Ű��־���
		HashSet<Integer> rdata = new HashSet<Integer>();
		while (rdata.size()<dataSize) {
			rdata.add((int)(Math.random()*maxKeyValue));
		}
		int k=0;
		for (int d : rdata) {
			data[k]=d;
			k++;
		}
		
		AssignmentOpenAddressingDouble mh = new AssignmentOpenAddressingDouble(HashTableSize);
		
		System.out.println("\n *** Open Addressing ***");
		int testcase =50;
		System.out.println("<< Insert >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.insert(data[i]));
			System.out.println("Count: " + mh.nOfHops);
		}
		System.out.println("<< Search >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.search(data[i]));
			System.out.println("Count: " + mh.nOfHops);
		}
		System.out.println("<< Delete >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.delete(data[i]));
			System.out.println("Count: " + mh.nOfHops);
		}
		long afterTime = System.nanoTime();
		System.out.println("���� �ð� : "+ (afterTime - beforeTime));
	}

}

