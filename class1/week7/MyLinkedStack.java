package week7;

public class MyLinkedStack {
	MyLinkedList3 stack;
	public MyLinkedStack(int size) {
		stack = new MyLinkedList3();
	}
	
	public void push(MyData data) {
		stack.addLast(data); // top에 데이터를 추가하면 되기 때문에
	}

	public MyData pop() {
		return stack.removeLast();
	}
	
	// top 데이터를 지우지 않고 불러오기
	public MyData peek() {
		return stack.get(stack.sizeOf()-1);
	}
	public boolean empty() {
		return stack.sizeOf() == 0;
	}

	public void showStack() {
		System.out.println(stack.toString());
	}

	
	public static void main(String[] args) {
		MyLinkedStack me = new MyLinkedStack(10);
		
		me.push(new MyData("abc", 1));
		me.push(new MyData("def", 2));
		me.push(new MyData("ghi", 3));
		me.showStack();
		System.out.println(me.pop());
		System.out.println(me.pop());
	}

}
