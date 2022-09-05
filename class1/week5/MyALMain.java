package class1.week5;

public class MyALMain {

	public static void main(String[] args) {
		// 개수에 구애받지 않음
		MyArrayList1 me = new MyArrayList1();

		for(int i = 0; i<17; i++) {
			me.add((int) (100*Math.random()));
		}
		
		me.add(20);
		
		System.out.println(me.toString());
		System.out.println(me.get(3));

		me.remove(10);
		System.out.println(me.toString());
		
		me.add(2, 10);
		System.out.println(me.toString());
	}

}
