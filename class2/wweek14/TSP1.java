package class2.wweek14;

import java.util.HashSet;

public class TSP1 {
	int [][] adjacentMatrix;
	int nV ;
	
	public TSP1(int [][] in) {
		adjacentMatrix = in;
		nV = adjacentMatrix.length;
	}
	public int minDistance(int start) {
		HashSet<Integer> thruSet = new HashSet<>();
		for (int i=0; i<nV; i++)
			thruSet.add(i);
		thruSet.remove(start);
		return minDistance(start, thruSet, start);
	}
	
	private int minDistance(int index, HashSet<Integer> thruSet, int end) {
		if (thruSet.size()==0)
			return adjacentMatrix[index][end];
		
		int min =999;
		for (int i : thruSet) {
			HashSet<Integer> next =reduce(thruSet,i);
			if(adjacentMatrix[index][i]!=999) {
				int tempDist = adjacentMatrix[index][i]+minDistance(i,next, end);
				if (tempDist<min)
					min = tempDist;
			}
		}
		return min;
	}

	private HashSet<Integer> reduce(HashSet<Integer> thruSet, int i) {
		HashSet<Integer> result = new HashSet<Integer>();
		for (int k : thruSet) result.add(k);
		result.remove(i);
		return result;
	}
	public static void main(String[] args) {

	int [][] input = { {0,10,10,30,25}, {10,0,14,21,10}, 
			{10,18,0,7,9}, {8,11,7,0,3}, {14,10,10,3,0} };
	TSP1 me = new TSP1(input);
	System.out.println(me.minDistance(0));
	}
}
