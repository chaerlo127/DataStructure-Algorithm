package class2.wweek12;

import java.util.ArrayList;

public class ListTest {


	public static void main(String[] args) {
		SCNode a=new SCNode(1);
		a.addNext(new SCNode(2));
		a.addNext(new SCNode(3));
		a.addPrev(new SCNode(4));

		System.out.println(a.toString());
		
		SCNode b = a.prev;
		System.out.println(a.delete());
		System.out.println(b.toString());
		
		System.out.println("\n\n[Array of LinkedLists]\n");
		
		
		ArrayList<SCNode> list = new ArrayList<>();
		list.add(0, new SCNode(1));
		list.add(1, new SCNode(2));
		list.add(2, new SCNode(3));
		list.add(3, new SCNode(4));
		
		list.get(0).addNext(new SCNode(5));
		list.get(0).addNext(new SCNode(6));
		list.get(2).addNext(new SCNode(7));
		list.get(3).addNext(new SCNode(8));
		
		for (int i=0;i<list.size();i++)
			System.out.println(list.get(i).toString());

	}

}