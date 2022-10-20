package class2.week4;

import java.util.Arrays;
import java.util.HashSet;

public class AssignmentOpenAddressingQuadratic {

	int nOfHops=0;
	
	int [] hTable;
	int m; // table size
	
	int numberOfItems;
	double threshold = 0.7; 
	
	public AssignmentOpenAddressingQuadratic(int tableSize) {
		m=tableSize;
		hTable = new int [m];
		Arrays.fill(hTable, -1);
		
		numberOfItems = 0;
	}

	public double loadfactor() {
		return (double)numberOfItems/m;
	}
	
	private void enlargeTable() {
		int [] oldTable =hTable;
		int oldSize = m;
		m *= 2;                  //  ???
		hTable = new int[m];
		for (int i=0;i<m; i++)
			hTable[i]=-1;
		numberOfItems=0;
		for (int i =0; i<oldSize;i++) { // rehashing
			if (oldTable[i]>=0)   // -999?
				quadInsert(oldTable[i]);
		}
	}
	
	
	public int quadInsert(int d) {
		if (loadfactor()>=threshold) {
			System.out.println(">>>  Hash Table is Enlarged !!");
			System.out.print(">>>  Table Size : "+m+", Load Factor : "+loadfactor()); // 0.7 �̻� �����Ͱ� á��.
			enlargeTable(); // Table�� �뷮�� ���δ�.
			System.out.println("  ==>  Table Size : "+m+", Load Factor : "+loadfactor()); // ���� loadfactor�� Ȯ���Ѵ�
		}
		int index = hashFunction(d);
		nOfHops =1;
		if (hTable[index] == -1) {
			hTable[index] = d;
			numberOfItems++;
//			return nOfHops;
			return index;
		} else { // Collision
			System.out.println("Collision! at " + index);

			nOfHops++;
			int nOfCollisions = 1;
			int probeIndex = (index + nOfCollisions * nOfCollisions) % m;
			while (hTable[probeIndex] != -1 && hTable[probeIndex] != -999) {
				System.out.println("nCollision= " + nOfCollisions + "  probeIndex= " + probeIndex);

				nOfCollisions++;
				nOfHops++;
				probeIndex = (index + nOfCollisions * nOfCollisions) % m;

				if (probeIndex == index)
					return -99999;

			}
			hTable[probeIndex] = d; // ������ ���� ����Ǿ� ���� �ʰų�, -999�� delete �� ����ҿ� ���� �־��ش�.
			numberOfItems++;
//			return nOfHops;
			return probeIndex;

		}
	}

	public int quadSearch(int d) {
		int index = hashFunction(d);
		nOfHops =1;
		if (hTable[index]==d) {
			return nOfHops;
		}
		else { // Collision
			nOfHops++;
			int nOfCollisions = 1;
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=d) {
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;

				if (probeIndex==index) return -99999;  
			}
			if (hTable[probeIndex]==d)
				return nOfHops;
			else // -1�̸� ã�� ���ߴٴ� ����.
				return -nOfHops;
		}
	}
	
	public int quadDelete(int d) {
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
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=d) {
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;

				if (probeIndex==index) return -99999;  
				// �� ���븦 ���ȴµ���(���� index�� ��ġ�� �Ծ) ã�� ���ߴ�. �ᱹ addressing �ȿ� ���� ���ٴ� ��
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

	//------------------------------------------------------------------------
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
		// open addressing-Quadratic
		// �浹�� �߻��ϸ� index���� �ϳ��� ������Ű�鼭 ����� ã�� insert
		// �� ���� ã���� insert�ϰ� �� index�� return 
		// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
		// ó�� ��ġ���� �� ���� ��ã���� -777�� return
			int nOfCollisions = 1;
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {  
				System.out.println("nCollision= "+nOfCollisions+"  probeIndex= "+probeIndex);
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
				
				if (probeIndex==index) return -777;  // �ѹ����� ���Ҵ�.
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
			int nOfCollisions = 1;
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			nOfHops++;

			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
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
			hTable[index]=-999;
			return index;
		}
		else {
			// �浹�� �߻��ϸ� insert�� ���� ������ �Ȱ��� �����Ͽ� �ش� ���� ����� index�� ã�ư�
			// �ش� ���� ã���� ���� ����� (�ϴ� -1�� ��ħ), �� index�� return 
			// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
			// ó�� ��ġ�� ���ƿ� ������ ��ã���� -888�� return --> �������� Ȯ�εǸ� -888�� �ƴ϶� -nOfHops�� return�ϵ��� ����
			System.out.println("Search new location of "+index+" to delete");
			int nOfCollisions = 1;
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
				if (probeIndex==index)
					return -888;  // Not found�� �ǹ�(�ѹ����� ���Ҵ�)
			}
			if (hTable[probeIndex]==value) {
				hTable[probeIndex]=-999; 
				return probeIndex;
			}
			else
				return -888; // Not found�� �ǹ� hTable[probeIndex]!=-1
		}
		
	}

	public static void main(String[] args) {
		long beforeTime = System.nanoTime();
		int dataSize = 10000;
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
		
		AssignmentOpenAddressingQuadratic mh = new AssignmentOpenAddressingQuadratic(HashTableSize);
		
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

