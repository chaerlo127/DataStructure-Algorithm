package class2.wweek12;

import java.util.Arrays;
import java.util.LinkedList;

public class AGraphInTopological extends DGraphInList{

	public void TPSort1() {
		String[] result = new String[Vertices.size()];
		int nOfVertices = Vertices.size();
		for(int i = 0; i<nOfVertices; i++) {
			result[i] = getNextNode();
			deleteVertex(result[i]);
			showGraph();
		}
		
		System.out.println(">> Result: Start");
		for(int i = 0; i<nOfVertices; i++) {
			System.out.println("=> " + result[i]);
		}
		
		System.out.println();
	}
	
	private String getNextNode() {
		for(int i = 0; i<Vertices.size(); i++) {
			int nOfIncoming = 0;
			for(int j = 0; j<Vertices.size(); j++) {
				for(int k =0; k<adjacentList.get(j).size();k++) {
					if(adjacentList.get(j).get(k).key.equals(Vertices.get(i))) nOfIncoming++;
				}
			}
			
			if(nOfIncoming == 0) {
				return Vertices.get(i);
			}
		}
		return null;
	}
	
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[Vertices.size()];
		Arrays.fill(visited, false);
		
		for(String s:Vertices) {
			if(visited[Vertices.indexOf(s)] == false) dfsTS(visited, s, R);
		}
	}

	
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R){
		visited[Vertices.indexOf(s)] = true;
		for(String x: adjacent(s)) {
			if(visited[Vertices.indexOf(x)] == false) dfsTS(visited, x, R);
		}
		
		System.out.println(s + "is added a the first");
		R.addFirst(s);
		return R;
	}
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String[] tasks = {"water", "ignition", "openPack", "noodle", "soup", "egg"};
		int[][] graphEdges = {{0, 1}, {1 ,3}, {1, 4}, {1 ,5},
				{2, 3}, {2 ,4},{3, 5}, {4 ,5}};
		
		AGraphInTopological myGM = new AGraphInTopological();
		
		myGM.createGraph();
		
		for(int i = 0; i<graphEdges.length; i++) {
			myGM.insertEdge(tasks[graphEdges[i][0]], tasks[graphEdges[i][1]], 1);
		}
		myGM.showGraph();
		
		System.out.println("Topological Sort1: start");
		myGM.TPSort1();
		
		AGraphInTopological myGM2 = new AGraphInTopological();

		myGM2.createGraph();

		for (int i = 0; i<graphEdges.length; i++) {
			myGM2.insertEdge(tasks[graphEdges[i][0]], tasks[graphEdges[i][1]], 1);
		}
		myGM2.showGraph();
		
		System.out.println("Topological Sort2: start");
		myGM.TPSort2();
	}



}
