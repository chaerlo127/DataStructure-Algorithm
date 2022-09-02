package week5;

public class MyLLMain {

	public static void main(String[] args) {
//		MyLinkedList0 me = new MyLinkedList0();
		MyLinkedList1 me = new MyLinkedList1();

		
		
		for(int i = 0; i<17; i++) {
			me.add((int)(100*Math.random()));
		}
		System.out.println(me.toString());
		
		me.addFirst(10);
		System.out.println(me.toString());
		
		me.add(2);
		System.out.println(me.toString());
		
		me.remove(10);
		System.out.println(me.toString());
		
	}

}
