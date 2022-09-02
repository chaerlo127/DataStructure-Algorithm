package week9;

public class TestMain {

	public static void main(String[] args) {
		MyArrayList3 al = new MyArrayList3(10);
		
		al.add(new MyData("xyz", 50));
		al.add(new MyData("abc", 10));
		al.add(new MyData("ghi", 30));
		al.add(new MyData("def", 20));
		al.add(new MyData("jkl", 40));  
		
		System.out.println(al.toString());
		al.sort();
		System.out.println(al.toString());
		System.out.println(al.indexOf(new MyData("xyz", 10)));		
	}

}
