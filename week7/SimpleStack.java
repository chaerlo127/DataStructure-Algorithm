package week7;

public class SimpleStack {
	private int[] stack;
	private int top;
	private int maxSize;

	public SimpleStack(int n) {
		maxSize = n;
		stack = new int[n];
		top = 0; // 저장 될 위치
	}

	public void push(int data) {
		if (top == maxSize) { // 다음에 저장할 위치가 maxSize와 같다면
			System.out.println("Stack Full");
		} else {
			stack[top] = data;
			top++;
		}
	}
	// 추가된 코드 : 값 추가
	public int peek() {
		if(empty()) {
			System.out.println("Stack Empty");
			return -999;
		}else {
			return stack[top];
		}
	}
	
	public int pop() {
		if (empty()) {
			System.out.println("Stack Empty");
			return -999;
		} else {
			top--;
			return stack[top];
		}
	}

	public boolean empty() {
		return top == 0;
	}

	public void showStack() {
		for (int i = 0; i < top; i++) {
			System.out.print(stack[i] + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SimpleStack me = new SimpleStack(10);
		for (int i = 0; i < 100; i++) {
			if (Math.random() > 0.5) {
				me.push(i);
			} else {
				me.pop();
				
			}me.showStack();
		}
	}

}
