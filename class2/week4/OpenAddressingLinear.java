package class2.week4;

import java.util.Arrays;
import java.util.HashSet;

public class OpenAddressingLinear {

	int nOfHops=0;
	
	int [] hTable;
	int m; // table size
	
	int numberOfItems;
	double threshold = 0.7; 
	
	public OpenAddressingLinear(int tableSize) {
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
			int probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {
				System.out.println("nCollision= "+nOfCollisions+"  probeIndex= "+probeIndex);

				nOfCollisions++;
				nOfHops++;
				probeIndex  = (index+nOfCollisions*nOfCollisions)%m;
				
				if (probeIndex==index) return -99999;  
					 
			}
			hTable[probeIndex]=d;
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
			else
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

				if (probeIndex==index) return -99999;  // ?????
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

	private int hashFunction(int value) {
		// 나누기방법
		return (value%m);
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
			int probeIndex  = (index+1)%m;
			nOfHops++;
//			while(hTable[probeIndex]!=-1 ) {  // 완전하지 않은 코드-> 나중에 수정 예정
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {  
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
			int probeIndex  = (index+1)%m;
			nOfHops++;

			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
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
			int probeIndex  = (index+1)%m;
			nOfHops++;
			while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=value) {
				probeIndex  = (probeIndex+1)%m;
				nOfHops++;
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
		// 시간 측정 코드
		long beforeTime = System.currentTimeMillis();
		
		int dataSize = 50;
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
		
		OpenAddressingLinear mh = new OpenAddressingLinear(HashTableSize);
		
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

		long afterTime = System.currentTimeMillis();
		System.out.println("실행 시간(Millis time) : "+ (afterTime - beforeTime));
	}
//	private int hashFunction(int d) {
//		// 곱하기방법
//		double temp = (double) d * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int) (res * m);
//	}
//
//	private int hashFunction2(int d) {
//		// 곱하기방법
//		double temp = (double) d * 0.8180339887;
//		double res = temp - Math.floor(temp);
//		return (int) (res * m);
//	}
//
//	public int hashInsert(int d) {
//		if (loadfactor() >= threshold)
//			enlargeTable();
//		int hashCode = hashFunction(d);
//		nOfHops = 1;
//		if (hTable[hashCode] == -1) {
//			hTable[hashCode] = d;
//			numberOfItems++;
//			return nOfHops;
//		} else { // Collision
//			nOfHops++;
//			int probeIndex = (hashCode + 1) % m;
//			while (hTable[probeIndex] != -1 && hTable[probeIndex] != -999) {
//				nOfHops++;
//				i++;
//				probeIndex = (probeIndex + i * hashFunction2(d)) % m;
//				if (probeIndex == hashCode)
//					return 0;
//			}
//			hTable[probeIndex] = d;
//			numberOfItems++;
//			return nOfHops;
//		}
//	}
//
//	public int hashSearch(int d) {
//		int hashCode = hashFunction(d);
//		nOfHops = 1;
//		if (hTable[hashCode] == d) {
//			hTable[hashCode] = d;
//			numberOfItems++;
//			return nOfHops;
//		} else { // Collision
//			nOfHops++;
//			int probeIndex = (hashCode + 1) % m;
//			while (hTable[probeIndex] != -1 && hTable[probeIndex] != d) {
//				nOfHops++;
//				i++;
//				probeIndex = (probeIndex + i * hashFunction2(d)) % m;
//				if (probeIndex == hashCode)
//					return 0;
//			}
//			if (hTable[probeIndex] == d)
//				return nOfHops;
//			else
//				return -nOfHops;
//		}
//	}
//
//	public int hashDelete(int d) {
//		int hashCode = hashFunction(d);
//		nOfHops = 1;
//		if (hTable[hashCode] == d) {
//			hTable[hashCode] = -999;
//			numberOfItems--;
//			return nOfHops;
//		} else { // Collision
//			nOfHops++;
//			int probeIndex = (hashCode + 1) % m;
//			while (hTable[probeIndex] != -1 && hTable[probeIndex] != d) {
//				nOfHops++;
//				i++;
//				probeIndex = (probeIndex + i * hashFunction2(d)) % m;
//				if (probeIndex == hashCode)
//					return 0;
//			}
//			if (hTable[probeIndex] == d) {
//				hTable[probeIndex] = -999;
//				numberOfItems--;
//				return nOfHops;
//			} else
//				return -nOfHops;
//		}
//	}
	
}

