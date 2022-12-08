package class2.wweek14;

import java.util.HashSet;

// ��Ʈ��ŷ�� �ϸ鼭
public class TSP {
	int [][] AdjacentMatrix;
	int numberVertex;
	
	public TSP(int[][] input) {
		AdjacentMatrix = input;
		numberVertex=AdjacentMatrix.length;
	}
	
	public int minDistance(int start) {
		HashSet<Integer> thruSet = new HashSet<>(); // �߰��� �� ������ Ȯ���ϱ� ���ؼ� thruSet�� �����ص�. ���� ��ü�� ���� ���� �� ���� �ʱ� ������
		// thruSet�� ��� �� ����� �ؾ� ��. �����ϴ� ���� �����ϰ�� ��� ���� �ؾ���. ������ index 0������ ��� ����
		for (int i=0; i<numberVertex; i++) {
			if (i!=start) 
				thruSet.add(i);
		}
		return minDistance(start, thruSet, start);
	}

	private int minDistance(int start, HashSet<Integer> thruSet, int returnPoint) { // �����ϴ� ��ġ, �߰����� �� ���� ��ġ,  �ٽ� ���ƿ� ��ġ
		if (thruSet.size()==0) {
			return AdjacentMatrix[start][returnPoint];
		}
		int min=999; // �ϳ��ϳ� �� ���鼭 ���� Ȯ���ϰ�, ���� Ȯ���� �Ŀ� �� ���� ���� �ּ��� ������ ������ Ȯ���Ѵ�. 
		for (int i : thruSet) { // thruSet��ŭ ����.
			HashSet<Integer> next = reduce(thruSet,i); // thruSet�� ������ �Ǿ����� �ƿ� ������ ������, for �� ���� thruSet�� ���� �ֱ� ������ ���� �����ϸ� ������ �߻�
			// �ǹ������� ������ �� ������ �� ���� ���� ����� �� ������ recursion�� �����ϱ��
			if (AdjacentMatrix[start][i]!=999) { // ���� �ڽŰ� ������ ���� ���� 999�� �ƴ϶��
				int tempDist = AdjacentMatrix[start][i]+minDistance(i, next, returnPoint); // start���� i���ٰ� �������� ���İ��� ���� �ּҰŸ��� ���� ���̴�. 
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
