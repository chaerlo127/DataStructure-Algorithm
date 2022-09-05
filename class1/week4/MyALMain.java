package class1.week4;
public class MyALMain {

	public static void main(String[] args) {
		// 개수에 구애받지 않음
		MyArrayList1Assignment me = new MyArrayList1Assignment();

		for(int i = 0; i<19; i++) {
			me.add((int) (100*Math.random()));
		}
		
		me.add(20);
		
		System.out.println(me.toString());
		System.out.println(me.get(3));
		System.out.println(me.search(17));
		
		me.remove(10);
		System.out.println(me.toString());
	}

}
