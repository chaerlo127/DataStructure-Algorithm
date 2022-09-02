package week4;

import java.util.Stack;

public class HanoiTowerIteration {

	enum Rtype {
		PLATE, SUBTOWER,
	}

	private class Record {
		Rtype type;
		int n;
		int from;
		int to;

		private Record(Rtype t, int n, int i, int j) {
			type = t;
			this.n = n;
			from = i;
			to = j;
		}
	};

	Stack<Record> stack = new Stack<>(); // Record 들이 저장 될 stack을 정의했다.

	public void move(int n, int i, int j) {

		stack.push(new Record(Rtype.SUBTOWER, n, i, j));

		while (!stack.empty()) {
			Record temp = stack.pop();
			if (temp.type == Rtype.PLATE || temp.n == 1) {
				System.out.println("Plate " + temp.n + " : " + temp.from + " ==> " + temp.to);
			} else {
				// 무조건 서브 타입인 경우
				int k = 3 - temp.from - temp.to;
				// 스택에 넣는 것은 나중에 처리될 것이 먼저 들어가야 함
				stack.push(new Record(Rtype.SUBTOWER, temp.n-1, k, temp.to)); // 1~2, 2, 1
				stack.push(new Record(Rtype.PLATE, temp.n, temp.from, temp.to)); // 3, 0, 1
				stack.push(new Record(Rtype.SUBTOWER, temp.n - 1, temp.from, k)); // (1~2), 0, 2
			}

		}
	}

	public static void main(String[] args) {
		
		HanoiTowerIteration myT = new HanoiTowerIteration();
		myT.move(3, 0, 1);
	}

}
