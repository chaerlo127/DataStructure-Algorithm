package class2.wweek14;

import java.util.HashSet;

// 백트래킹을 하면서
public class TSP {
	int [][] AdjacentMatrix;
	int numberVertex;
	
	public TSP(int[][] input) {
		AdjacentMatrix = input;
		numberVertex=AdjacentMatrix.length;
	}
	
	public int minDistance(int start) {
		HashSet<Integer> thruSet = new HashSet<>(); // 중간에 간 곳까지 확인하기 위해서 thruSet을 생성해둠. 어디는 전체를 가고 어디는 다 가지 않기 때문에
		// thruSet은 모두 다 통과를 해야 함. 시작하는 것을 제외하고는 모두 저장 해야함. 시작은 index 0번으로 모두 같음
		for (int i=0; i<numberVertex; i++) {
			if (i!=start) 
				thruSet.add(i);
		}
		return minDistance(start, thruSet, start);
	}

	private int minDistance(int start, HashSet<Integer> thruSet, int returnPoint) { // 시작하는 위치, 중간까지 간 곳의 위치,  다시 돌아올 위치
		if (thruSet.size()==0) {
			return AdjacentMatrix[start][returnPoint];
		}
		int min=999; // 하나하나 다 돌면서 값을 확인하고, 값을 확인한 후에 그 값이 가장 최소의 값보다 작은지 확인한다. 
		for (int i : thruSet) { // thruSet만큼 돈다.
			HashSet<Integer> next = reduce(thruSet,i); // thruSet이 선택이 되었으면 아예 지워야 하지만, for 문 내의 thruSet이 돌고 있기 때문에 값을 변경하면 에러가 발생
			// 의미적으로 복제를 한 다음에 그 곳에 값을 지우고 그 것으로 recursion을 진행하기로
			if (AdjacentMatrix[start][i]!=999) { // 현재 자신과 인접한 것의 수가 999가 아니라면
				int tempDist = AdjacentMatrix[start][i]+minDistance(i, next, returnPoint); // start에서 i에다가 나머지를 거쳐가는 것의 최소거리를 더한 것이다. 
				if (tempDist<=min) {
					min=tempDist;
				}
			}
		}
		System.out.println(start+" -> "+thruSet+" ("+min+")");
		return min; 
	}


	private HashSet<Integer> reduce(HashSet<Integer> set, int i) {
		HashSet<Integer> result= new HashSet<>();
		for (int j : set) result.add(j);
		result.remove(i);
		return result;
	}
	
	public static void main(String[] args) {
		int [][] input = { {0,10,10,30,25}, {10,0,14,21,10}, 
				{10,18,0,7,9}, {8,11,7,0,3}, {14,10,10,3,0} };
		
		TSP me = new TSP(input);
		System.out.println("Result = "+me.minDistance(0));

	}
}
