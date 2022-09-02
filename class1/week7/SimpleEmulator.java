package week7;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class SimpleEmulator {
	Stack<Character> cStack = new Stack<>(); // ��ȣ ����, infix -> postfix ����
	Stack<String> sStack = new Stack<>(); // ����� �ϸ� �� �ڸ� ���ڰ� �� �� �־� String����
	Queue<Character> queue = new ArrayDeque<>(); // ��ȣ�� ���� postfix data �� ����
	String expression;
	
	public int compute(String s) {
		expression=s;
		getPostfix(); // queue�� postfix data �� ����
		return stackOperation(); // queue data �ϳ��� �����鼭 sStack���� ����� �ϴ� ��
	}

	public void getPostfix() {
		for (int i=0; i<expression.length();i++) { // �� ���ھ� ��������
			char ch=expression.charAt(i);
			switch(ch){
			case '(' : cStack.push(ch); break;
			case ')' : char tempC='0';
					while(!cStack.empty()&&((tempC=cStack.pop())!='(')) {
						queue.add(tempC); // '('�� ������ ���� �� ���� operator(+-*/) ����
					}
					if (tempC!='('){
						System.out.println("Parenthesis Pairing Error :type 1");
						System.exit(0);
					}
					break;
			case '+' : cStack.push(ch); break;
			case '-' : cStack.push(ch); break;
			case '*' : cStack.push(ch); break;
			case '/' : cStack.push(ch); break;
			default : 
				queue.add(ch); // �����ϰ�� �׳� ����
			}
			System.out.println(">>> cStack : "+cStack.toString());

		} // ��� charactor �Ⱦ

		while(!cStack.empty()) { // operator�� ���� ���� ���� ����
			char t = cStack.pop();
			if (t=='('||t==')') {
				System.out.println("Parenthesis Pairing Error :type 2");
				System.exit(0);
			}
			else
				queue.add(t);
		}

		System.out.print("Postfix Expression : "+queue.toString());
		System.out.println();

	}

	public int stackOperation() {
		char ch;
		while(!queue.isEmpty()) {
			ch=queue.poll(); // ����鼭 �� ��������

			switch(ch){
			case '+' : add(); break;
			case '-' : sub(); break;
			case '*' : mul(); break;
			case '/' : div(); break;
			default : 
				sStack.push(""+ch);
			}
			System.out.println(">>> sStack : "+sStack.toString());

		}

		return Integer.parseInt(sStack.pop());
	}

	private void add() {
		int a=Integer.parseInt(sStack.pop());
		int b=Integer.parseInt(sStack.pop());
		int result=b+a;
		sStack.push(Integer.toString(result));
	}
	private void sub() {
		int a=Integer.parseInt(sStack.pop());
		int b=Integer.parseInt(sStack.pop());
		int result=b-a;
		sStack.push(Integer.toString(result));
	}
	private void mul() {
		int a=Integer.parseInt(sStack.pop());
		int b=Integer.parseInt(sStack.pop());
		int result=b*a;
		sStack.push(Integer.toString(result));
	}
	private void div() {
		int a=Integer.parseInt(sStack.pop());
		int b=Integer.parseInt(sStack.pop());
		int result=b/a;
		sStack.push(Integer.toString(result));
	}

	public static void main(String[] args) {

		String s = "((3+(6-1))*(7-4))";  
		// postfix �������� �����Ͽ� setOperation���� �ٲٰ��� ��.
		// only single digit number, ���� �� �ڸ� ���ڸ� ��Ģ������ ����
		// unary operator X, only binary operator

		SimpleEmulator e = new SimpleEmulator();
		
		int result=e.compute(s);
	
		System.out.println("Computation Result = "+result);

	}
}
