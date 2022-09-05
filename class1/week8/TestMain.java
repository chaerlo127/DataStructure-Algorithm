package class1.week8;
// 1번 문제의 main

public class TestMain {

	public static void main(String[] args) {

		MyLinkedList3Rec ll = new MyLinkedList3Rec();
		
		ll.add(new MyData("xyz", 10));
		ll.add(new MyData("abc", 10));
		ll.add(new MyData("def", 20));
		ll.add(new MyData("ghi", 30));
		ll.add(new MyData("jkl", 40));
		
		System.out.println(ll.toString());
		
		System.out.println(ll.indexOf(new MyData("xyz", 10)));
		System.out.println(ll.indexOf(new MyData("jkl", 10)));
		System.out.println(ll.indexOf(new MyData("abc", 10)));
		System.out.println(ll.indexOf(ll.get(3)));
		MyData temp0,temp1,temp2,temp3,temp4;
		temp0 = ll.get(0);temp1 = ll.get(1);temp4 = ll.get(4);
		ll.remove(1);
		System.out.println("after rm.index1 "+ll.toString());
		ll.remove(temp1);
		System.out.println("after rm.data1 "+ll.toString());
		ll.remove(0);
		System.out.println("after rm.index0 "+ll.toString());
		ll.remove(temp4);
		System.out.println("after rm.data4 "+ll.toString());
		ll.add(temp1);
		System.out.println("after add.data1 "+ll.toString());
		ll.add(temp0);
		System.out.println("after add.data0 "+ll.toString());
		ll.add(temp4);
		System.out.println("after add.data4 "+ll.toString());
	}
}
