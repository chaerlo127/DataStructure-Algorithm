package class2.wweek14;

import java.util.Arrays;

// ���� �׸��� Edge�� �� ������ ��ġ�� �ǰ�, ����� ��� �� �� ���İ��� �ȴ�.  
// �� �ٷ� �׸���. 
// ��� ���� �������� ���� ������ �� �� ��Ÿ���� �͸� �����ϰ�, ��� ������ �� ������ �������� �ϴ� ��
public class Hamiltonian {
	int [][] adjacentMatrix;
	
	public Hamiltonian(int [][] in) {
		adjacentMatrix = in;
	}
	public void findAllPath() {
		boolean [] visited = new boolean[adjacentMatrix.length];
		int [] path = new int[adjacentMatrix.length];
		int start = 0;
		int vCount =0;
		Arrays.fill(visited, false);
		Arrays.fill(path, -1);
		
		visited[start]=true;
		vCount++;
		path[0]=start;
		findAllPath(start, visited, path, start, vCount); // start�� �����ؼ� start�� ���ƿ�, 1���� ���� �����̴�. (vCount)
	}
	private void findAllPath(int index, boolean[] visited, int[] path, int end, int count) {
		for (int i = 0; i<adjacentMatrix.length; i++) {
			if (i==end && count==adjacentMatrix.length) { // �������� �Ͱ� ���ų� ���� ���ƴٴ� ���� ������ ����� ���� ���� ���
				System.out.print("Path found ");
				for (int j = 0; j<adjacentMatrix.length; j++)
					System.out.print("-"+path[j]); // path�� �ϳ��� Ȯ�����ش�.
				System.out.println();

				return;
			}
			if (visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999 ) { 
				// 0�� �ƴ� ���� �ڱ��ڽ��� �ƴ� �� + �ڽŰ� ������ ����� ���� 999�� �ƴ϶��
				visited[i]=true;
				path[count]=i;
				count++;
				findAllPath(i, visited, path, end, count);
				//----------------------------------------
				visited[i]=false; // �湮 �ߴ� ��츦 ��� �� ������ �ϱ� ������, ��� ��츦 �� ���� �� �� �� ����� �ٽ� ����ؾ� �ϱ� ����
				// �����ߴ� �ڵ带 recursion�� �ϸ鼭 ����
				count--;
			    path[count]=-1;
			}
		}
	}

	public static void main(String[] args) {
		int [][] input = { {0,10,10,30,25}, {10,0,14,21,10}, 
				{10,18,0,7,9}, {8,11,7,0,3}, {14,10,10,3,0} };
		Hamiltonian me = new Hamiltonian(input);
		me.findAllPath();
	}

}
