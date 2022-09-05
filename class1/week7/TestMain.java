package class1.week7;

public class TestMain {

	public static void main(String[] args) {
		MyArrayList3 al = new MyArrayList3(10);
		
		MyData temp0, temp1, temp4;
		al.add(new MyData("xyz", 10));
		al.add(new MyData("abc", 10));
		al.add(new MyData("def", 20));
		al.add(new MyData("ghi", 30));
		al.add(new MyData("jkl", 40));
		
		System.out.println(al.toString());
		
		System.out.println(al.indexOf(new MyData("abc", 10)));		
		temp0 = al.get(0);temp1 = al.get(1);temp4 = al.get(4);
		
		al.remove(1);
		System.out.println("after rm.index1" + al.toString());
		al.remove(temp1);
		System.out.println("after rm.data1" + al.toString());
		al.remove(0);
		System.out.println("after rm.index0" + al.toString());
		al.remove(temp4);
		System.out.println("after rm.data4" + al.toString());
		al.add(temp1);
		System.out.println("after add.data1" + al.toString());
		al.add(temp0);
		System.out.println("after add.data0" + al.toString());
		al.add(temp4);
		System.out.println("after add.data4" + al.toString());
		
		al.sort();
		System.out.println(al.toString());
		
		MyLinkedList3 ll = new MyLinkedList3();
		ll.add(new MyData("xyz", 10));
		ll.add(new MyData("abc", 10));
		ll.add(new MyData("def", 20));
		ll.add(new MyData("ghi", 30));
		ll.add(new MyData("jkl", 40));
		
		System.out.println(ll.toString());
		
		System.out.println(ll.indexOf(new MyData("abc", 10)));		
		temp0 = ll.get(0);temp1 = ll.get(1);temp4 = ll.get(4);
		
		ll.remove(1);
		System.out.println("after rm.index1" + ll.toString());
		ll.remove(temp1);
		System.out.println("after rm.data1" + ll.toString());
		ll.remove(0);
		System.out.println("after rm.index0" + ll.toString());
		ll.remove(temp4);
		System.out.println("after rm.data4" + ll.toString());
		ll.add(temp1);
		System.out.println("after add.data1" + ll.toString());
		ll.add(temp0);
		System.out.println("after add.data0" + ll.toString());
		ll.add(temp4);
		System.out.println("after add.data4" + ll.toString());
		
		ll.sort();
	}

}
