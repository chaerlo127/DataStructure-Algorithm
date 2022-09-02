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

	Stack<Record> stack = new Stack<>(); // Record ���� ���� �� stack�� �����ߴ�.

	public void move(int n, int i, int j) {

		stack.push(new Record(Rtype.SUBTOWER, n, i, j));

		while (!stack.empty()) {
			Record temp = stack.pop();
			if (temp.type == Rtype.PLATE || temp.n == 1) {
				System.out.println("Plate " + temp.n + " : " + temp.from + " ==> " + temp.to);
			} else {
				// ������ ���� Ÿ���� ���
				int k = 3 - temp.from - temp.to;
				// ���ÿ� �ִ� ���� ���߿� ó���� ���� ���� ���� ��
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
