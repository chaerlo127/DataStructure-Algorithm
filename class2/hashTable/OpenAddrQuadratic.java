package class2.hashTable;
public class OpenAddrQuadratic { // Quadratic Probing

	int nOfHops =0;
	double threshold = 0.99;
	
	int [] table;
	int tableSize;
	int numberOfItems;
	int i = 1;
	
	public OpenAddrQuadratic (int n) {
		tableSize = n;
		table = new int[tableSize];
		numberOfItems = 0;
		// -1 : null, -999 : deleted
		for (int i=0; i<tableSize; i++)
			table[i]=-1;
	}
	
private int hashFunction(int d) {
	// 곱하기방법
	double temp = (double)d * 0.6180339887;
	double res = temp - Math.floor(temp);
	return (int)(res*tableSize);
}

	public int hashInsert(int d) {
		if (loadfactor()>=threshold)
			enlargeTable();
		int hashCode = hashFunction(d);
		nOfHops = 1;
		if (table[hashCode]==-1) {
			table[hashCode]=d;
			numberOfItems++;
			return nOfHops;
		}
		else { //Collision
			nOfHops++;
			int nOfCollisions = 1;
			//차이점
			int probeIndex = (hashCode+nOfCollisions*nOfCollisions)%tableSize;
			while(table[probeIndex]!=-1 && table[probeIndex]!=-999) {
				nOfCollisions++;
				nOfHops++;
				probeIndex = (hashCode+nOfCollisions*nOfCollisions)%tableSize;
				if (probeIndex==hashCode)
					return 0; 
			}
			table[probeIndex]=d;
			numberOfItems++;
			return nOfHops;
		}
	}

	public int hashSearch(int d) {
		int hashCode = hashFunction(d);
		nOfHops = 1;
		if (table[hashCode]==d) {
			table[hashCode]=d;
			numberOfItems++;
			return nOfHops;
		}
		else { //Collision
			nOfHops++;
			int probeIndex = (hashCode+1)%tableSize;
			while(table[probeIndex]!=-1 && table[probeIndex]!=d) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex+i*i)%tableSize;
				if (probeIndex==hashCode)
					return 0;
			}
			if (table[probeIndex]==d)
				return nOfHops;
			else
				return -nOfHops;
		}
	}
	
	public int hashDelete(int d) {
		int hashCode = hashFunction(d);
		nOfHops = 1;
		if (table[hashCode]==d) {
			table[hashCode]=-999;
			numberOfItems--;
			return nOfHops;
		}
		else { //Collision
			nOfHops++;
			int probeIndex = (hashCode+1)%tableSize;
			while(table[probeIndex]!=-1 && table[probeIndex]!=d) {
				nOfHops++;
				i++;
				probeIndex = (probeIndex+i*i)%tableSize;
				if (probeIndex==hashCode)
					return 0;
			}
			if (table[probeIndex]==d) {
				table[probeIndex]=-999;
				numberOfItems--;
				return nOfHops;
			}
			else
				return -nOfHops;
		}
	}
	
	
	private void enlargeTable() {
		int [] oldTable = table;
		int oldSize = tableSize;
		tableSize *= 2;
		table = new int[tableSize];
		for (int i=0; i<tableSize; i++)
			table[i]=-1;
		for (int i=0; i<oldSize; i++) {
			if (oldTable[i]>=0)
				hashInsert(oldTable[i]);
		}
	}
	
	
	

	public double loadfactor() {
		return (double)numberOfItems/tableSize;
	}
	
	public void showTable() {
		System.out.println("Current Hash Table : ");
		for (int i = 0; i<tableSize; i++)
			System.out.print(table[i]+"  ");
		System.out.println();
	}

	
	public static void main(String[] args) {
		int tableSize = 17;
		
		int [] data = {10, 12, 18, 20, 22, 23, 26, 27, 42, 57};
		int dataSize = data.length;
		
		System.out.println("\n *** Open Addressing - Quadratic Probing ***");
		
		OpenAddrQuadratic myHash = new OpenAddrQuadratic(tableSize);
		// Insert
		int sumOfSuccess = 0;
		int sumOfFailure = 0;
		int maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashInsert(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else
				sumOfFailure += count;
		}
		myHash.showTable();
		System.out.println("\n\n [Insert] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);
		System.out.println("\n Load Factors ="+myHash.loadfactor()); 
		
		// Search with existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 1] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Search with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 2] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Delete with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashDelete(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Delete] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);


	}

}
