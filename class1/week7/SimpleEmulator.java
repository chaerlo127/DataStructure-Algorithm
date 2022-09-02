package week7;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class SimpleEmulator {
	Stack<Character> cStack = new Stack<>(); // 괄호 저장, infix -> postfix 변경
	Stack<String> sStack = new Stack<>(); // 계산을 하면 두 자리 숫자가 될 수 있어 String으로
	Queue<Character> queue = new ArrayDeque<>(); // 괄호를 없앤 postfix data 열 저장
	String expression;
	
	public int compute(String s) {
		expression=s;
		getPostfix(); // queue에 postfix data 열 저장
		return stackOperation(); // queue data 하나씩 꺼내면서 sStack으로 계산을 하는 것
	}

	public void getPostfix() {
		for (int i=0; i<expression.length();i++) { // 한 글자씩 꺼내오기
			char ch=expression.charAt(i);
			switch(ch){
			case '(' : cStack.push(ch); break;
			case ')' : char tempC='0';
					while(!cStack.empty()&&((tempC=cStack.pop())!='(')) {
						queue.add(tempC); // '('가 나오지 않을 때 까지 operator(+-*/) 저장
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
				queue.add(ch); // 숫자일경우 그냥 저장
			}
			System.out.println(">>> cStack : "+cStack.toString());

		} // 모든 charactor 훑어봄

		while(!cStack.empty()) { // operator가 남아 있을 수도 있음
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
			ch=queue.poll(); // 지우면서 값 가져오기

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
		// postfix 연산으로 변경하여 setOperation으로 바꾸고자 함.
		// only single digit number, 오직 한 자리 숫자만 사칙연산이 가능
		// unary operator X, only binary operator

		SimpleEmulator e = new SimpleEmulator();
		
		int result=e.compute(s);
	
		System.out.println("Computation Result = "+result);

	}
}
