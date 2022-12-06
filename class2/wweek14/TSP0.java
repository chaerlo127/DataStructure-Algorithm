package class2.wweek14;
import java.util.Arrays;

public class TSP0 {
	int [][] adjacentMatrix;
	int min = 999 ;
	
	public TSP0(int [][] in) {
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
		findAllPath(start, visited, path, start, vCount);
	}
	private void findAllPath(int index, boolean[] visited, int[] path, int end, int count) {
		for (int i = 0; i<adjacentMatrix.length; i++) {
			if (i==end && count==adjacentMatrix.length) {
				int dist = calcDist(path);
				System.out.print("Path found ");
				for (int j = 0; j<adjacentMatrix.length; j++)
					System.out.print("-"+path[j]);
				System.out.print(" distance = "+dist);
				System.out.println();
				if (dist<min)
					min =dist;
				return;
			}
			if (visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999 ) {
				visited[i]=true;
				path[count]=i;
				count++;
				findAllPath(i, visited, path, end, count);
				visited[i]=false;
				count--;
			    path[count]=-1;
			}
		}
	}

	private int calcDist(int[] path) {
		int ret=0;
		for (int i=0;i<path.length-1; i++)
			ret += adjacentMatrix[path[i]][path[i+1]];
		ret += adjacentMatrix[path[path.length-1]][path[0]];
		return ret;
	}
	public static void main(String[] args) {
		int [][] input = { {0,10,10,30,25}, {10,0,14,21,10}, 
				{10,18,0,7,9}, {8,11,7,0,3}, {14,10,10,3,0} };
		TSP0 me = new TSP0(input);
		me.findAllPath();
	}

}
