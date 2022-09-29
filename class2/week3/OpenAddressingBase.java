package class2.week3;

import java.util.Arrays;
import java.util.HashSet;

public class OpenAddressingBase {

	int nOfHops=0;
	
	int [] hTable;
	int m; // table size
	
	public OpenAddressingBase(int tableSize) {
		m=tableSize;
		hTable = new int [m];
		Arrays.fill(hTable, -1);
	}
	
	private int hashFunction(int value) {
		// ��������
		return (value%m);
	}	
	
	public int insert(int value) {
		int index = hashFunction(value);
		nOfHops =1;
		
		if (hTable[index]!=-1) {
			System.out.println("Collision! at "+index);
		// open addressing-linear
		// �浹�� �߻��ϸ� index���� �ϳ��� ������Ű�鼭 ����� ã�� insert
		// �� ���� ã���� insert�ϰ� �� index�� return 
		// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
		// ó�� ��ġ���� �� ���� ��ã���� -777�� return
			int probeIndex  = (index+1)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 ) {  // �������� ���� �ڵ�-> ���߿� ���� ����
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)  // �ѹ����� �� ���Ҵµ� �� ���� ���� ���
					return -777;        // No Space! 
			}
			index=probeIndex;
		}
		hTable[index]=value;
		return index;

	}
	
	public int search(int value) {
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
			int probeIndex  = (index+1)%m;
			nOfHops++;

			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)  // �ѹ����� �� ���Ҵµ� ��ã��
					return -888;  		// Not found�� �ǹ�
			}
			if (hTable[probeIndex]==value)
				return probeIndex;
			else
				return -888;  // Not found�� �ǹ�
		}
	}
	
	public int delete(int value) {
		int index = hashFunction(value);
		nOfHops =1;
		if (value==hTable[index]) {
			hTable[index]=-1; // �������� ���� �ڵ�-> ���߿� ���� ����
			return index;
		}
		else {
			// �浹�� �߻��ϸ� insert�� ���� ������ �Ȱ��� �����Ͽ� �ش� ���� ����� index�� ã�ư�
			// �ش� ���� ã���� ���� ����� (�ϴ� -1�� ��ħ), �� index�� return 
			// --> �������� Ȯ�εǸ� index�� �ƴ϶� nOfHops�� return�ϵ��� ����
			// ó�� ��ġ�� ���ƿ� ������ ��ã���� -888�� return --> �������� Ȯ�εǸ� -888�� �ƴ϶� -nOfHops�� return�ϵ��� ����
			System.out.println("Search new location of "+index+" to delete");
			int probeIndex  = (index+1)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)
					return -888;  // Not found�� �ǹ�
			}
			if (hTable[probeIndex]==value) {
				hTable[probeIndex]=-1; // �������� ���� �ڵ�-> ���߿� ���� ����
				return probeIndex;
			}
			else
				return -888; // Not found�� �ǹ�
		}
	}


	public static void main(String[] args) {
		int dataSize = 30;
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
		
		OpenAddressingBase mh = new OpenAddressingBase(HashTableSize);
		
		System.out.println("\n *** Open Addressing ***");
		int testcase =20;
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
