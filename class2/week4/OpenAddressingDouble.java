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
		// 나누기방법
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
		// 충돌이 발생하면 index값을 하나씩 증가시키면서 빈곳을 찾아 insert
		// 빈 곳을 찾으면 insert하고 그 index를 return 
		// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
		// 처음 위치까지 빈 곳을 못찾으면 -777을 return
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
			// 충돌이 발생하면 insert할 때의 동작을 똑같이 추적하여 해당 값이 저장된 index를 찾아감
			// 해당 값을 찾으면 그 index를 return 
			// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
			// 처음 위치로 돌아올 때까지 못찾으면 -888을 return --> 정상동작이 확인되면 -888이 아니라 -nOfHops를 return하도록 수정
			System.out.println("Search new location of "+index);
			int i = 1;
			int probeIndex = (index + i * hashFunction2(value)) % m;
			nOfHops++;

			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex + i * hashFunction2(value)) % m;
				if (probeIndex==index)  // 한바퀴를 다 돌았는데 못찾음
					return -8888;  		// Not found를 의미
			}
			if (hTable[probeIndex]==value)
				return probeIndex;
			else
				return -888;  // Not found를 의미
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
			// 충돌이 발생하면 insert할 때의 동작을 똑같이 추적하여 해당 값이 저장된 index를 찾아감
			// 해당 값을 찾으면 값을 지우고 (일단 -1로 고침), 그 index를 return 
			// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
			// 처음 위치로 돌아올 때까지 못찾으면 -888을 return --> 정상동작이 확인되면 -888이 아니라 -nOfHops를 return하도록 수정
			System.out.println("Search new location of "+index+" to delete");
			int i = 1;
			int probeIndex = (index + i * hashFunction2(value)) % m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex + i * hashFunction2(value)) % m;
				if (probeIndex==index)
					return -888;  // Not found를 의미
			}
			if (hTable[probeIndex]==value) {
				hTable[probeIndex]=-999; // -1 ==> -999
				return probeIndex;
			}
			else
				return -888; // Not found를 의미
		}
		
	}

	public static void main(String[] args) {
		int dataSize = 10000;
		int maxKeyValue = 100000;
		int HashTableSize = 57;
		
		int [] data = new int [dataSize];
		// 서로 다른 데이터 1000개를 만들기 위해 Set으로 저장하며 원하는 갯수를 만들고 나서
		// data[]에 옮겨주었음
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

