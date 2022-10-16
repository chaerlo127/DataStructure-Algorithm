package class2.week4;

import java.util.Arrays;
import java.util.HashSet;

public class OpenAddressingDouble {

	int nOfHops=0;
	
	int [] hTable;
	int m; // table size
	int n = 11;
	int numberOfItems;
	
	public OpenAddressingDouble(int tableSize) {
		m=tableSize;
		hTable = new int [m];
		Arrays.fill(hTable, -1);
		
		numberOfItems = 0;
	}
	
	private int hashFunction(int value) {
		// ��������
		return (value%m);
	}	
	private int hashFunction2(int value) {
		return (value%n);
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
		
		OpenAddressingDouble mh = new OpenAddressingDouble(HashTableSize);
		
		System.out.println("\n *** Open Addressing ***");
		int testcase =50;
		System.out.println("<< Insert >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.insert(data[i]));
		}
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

