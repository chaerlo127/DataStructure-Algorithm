package class2.wweek14;

import java.util.Arrays;

// 한줄 그리기 Edge만 한 번씩만 거치면 되고, 노드의 경우 두 번 거쳐가도 된다.  
// 한 줄로 그린다. 
// 출발 점과 종료점이 같은 것으로 두 번 나타나는 것만 제외하고, 모든 엣지가 한 번씩만 나오도록 하는 것
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
		findAllPath(start, visited, path, start, vCount); // start로 시작해서 start로 돌아와, 1번을 넣을 차례이다. (vCount)
	}
	private void findAllPath(int index, boolean[] visited, int[] path, int end, int count) {
		for (int i = 0; i<adjacentMatrix.length; i++) {
			if (i==end && count==adjacentMatrix.length) { // 끝내려는 것과 같거나 현재 돌아다닌 수가 인접한 노드의 수와 같은 경우
				System.out.print("Path found ");
				for (int j = 0; j<adjacentMatrix.length; j++)
					System.out.print("-"+path[j]); // path를 하나씩 확인해준다.
				System.out.println();

				return;
			}
			if (visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999 ) { 
				// 0이 아닌 것은 자기자신이 아닌 것 + 자신과 인접한 노드의 값이 999가 아니라면
				visited[i]=true;
				path[count]=i;
				count++;
				findAllPath(i, visited, path, end, count);
				//----------------------------------------
				visited[i]=false; // 방문 했던 경우를 모두 다 지워야 하기 때문에, 모든 경우를 다 나열 할 때 다 지우고 다시 사용해야 하기 때문
				// 변경했던 코드를 recursion을 하면서 변경
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
