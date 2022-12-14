package class2.week4;

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
		// 나누기방법
		return (value%m);
	}	
	
	public int insert(int value) {
		int index = hashFunction(value);
		nOfHops =1;
		
		if (hTable[index]!=-1) {
			System.out.println("Collision! at "+index);
		// open addressing-linear
		// 충돌이 발생하면 index값을 하나씩 증가시키면서 빈곳을 찾아 insert
		// 빈 곳을 찾으면 insert하고 그 index를 return 
		// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
		// 처음 위치까지 빈 곳을 못찾으면 -777을 return
			int probeIndex  = (index+1)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex] != -999) {  
				// 완전하지 않은 코드-> 나중에 수정 예정
				// 수정 완료: 아직 저장되지 않은 위치와 저장된 위치를 구분하기 위해서, 저장이 안된 위치는 -1로, 저장 후에 삭제 된 위치는 -999로
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)  // 한바퀴를 다 돌았는데 빈 곳이 없는 경우
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
			// 충돌이 발생하면 insert할 때의 동작을 똑같이 추적하여 해당 값이 저장된 index를 찾아감
			// 해당 값을 찾으면 그 index를 return 
			// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
			// 처음 위치로 돌아올 때까지 못찾으면 -888을 return --> 정상동작이 확인되면 -888이 아니라 -nOfHops를 return하도록 수정
			System.out.println("Search new location of "+index);
			int probeIndex  = (index+1)%m;
			nOfHops++;

			// -999를 조건에 넣지 않은 이유는 
			// -999 다음에도 Index가 추가되어 값이 저장되어 있는 경우가 있기 때문 => 중간에 값이 지워져도 그 다음의 위치에는 값이 있을 수 있기 때문
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)  // 한바퀴를 다 돌았는데 못찾음
					return -888;  		// Not found를 의미
			}
			if (hTable[probeIndex]==value)
				return probeIndex;
			else
				return -888;  // hTable[probeIndex]!=-1 Not found를 의미
		}
	}
	
	public int delete(int value) {
		int index = hashFunction(value);
		nOfHops =1;
		if (value==hTable[index]) {
			hTable[index]=-999; // 완전하지 않은 코드-> 나중에 수정 예정, 수정 완료
			return index;
		}
		else {
			// 충돌이 발생하면 insert할 때의 동작을 똑같이 추적하여 해당 값이 저장된 index를 찾아감
			// 해당 값을 찾으면 값을 지우고 (일단 -1로 고침), 그 index를 return 
			// --> 정상동작이 확인되면 index가 아니라 nOfHops를 return하도록 수정
			// 처음 위치로 돌아올 때까지 못찾으면 -888을 return --> 정상동작이 확인되면 -888이 아니라 -nOfHops를 return하도록 수정
			System.out.println("Search new location of "+index+" to delete");
			int probeIndex  = (index+1)%m;
			nOfHops++;
			// -999를 조건에 넣지 않은 이유는
			// -999 다음에도 Index가 추가되어 값이 저장되어 있는 경우가 있기 때문 => 중간에 값이 지워져도 그 다음의 위치에는 값이 있을 수 있기 때문
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
				if (probeIndex==index)
					return -888;  // Not found를 의미
			}
			if (hTable[probeIndex]==value) {
				hTable[probeIndex]=-999; 
				// 완전하지 않은 코드-> 나중에 수정 예정
				// 수정 완료: 아직 저장되지 않은 위치와 저장된 위치를 구분하기 위해서, 저장이 안된 위치는 -1로, 저장 후에 삭제 된 위치는 -999로
				return probeIndex;
			}
			else
				return -888; // Not found를 의미, 쭉 index에 1을 추가하면서 이동하다가 -1이 나온 것은 index에 저장한 것이 끝에 왔다. 
		}
	}


	public static void main(String[] args) {
		long beforeTime = System.currentTimeMillis();
		int dataSize = 100000;
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
		
		OpenAddressingBase mh = new OpenAddressingBase(HashTableSize);
		
		System.out.println("\n *** Open Addressing ***");
		int testcase =30;
		System.out.println("<< Insert >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.insert(data[i]));
		}
		System.out.println(mh.nOfHops);
		System.out.println("<< Search >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.search(data[i]));
		}
		System.out.println("<< Delete >>");
		for (int i=0;i<testcase;i++) {
			System.out.printf("%10d ==> %5d%n", data[i],mh.delete(data[i]));
		}
		
		// 측정 시간 받아오기		
		long afterTime = System.currentTimeMillis(); 

		System.out.println("실행 시간 : "+ (afterTime - beforeTime));

	}

}
