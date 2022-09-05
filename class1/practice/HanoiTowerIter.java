package class1.practice;

import java.util.Stack;

public class HanoiTowerIter {
	
	enum RType{
		PLATE, SUBTOWER;
	}
	private class Record{
		RType type;
		int n, from, to;
		public Record(RType t, int n, int from, int to) {
			this.type = t;
			this.n = n;
			this.from = from;
			this.to = to;
		}
	};

	Stack<Record> stack = new Stack<>();

	public void move(int count, int from, int to) {
		this.stack.push(new Record(RType.SUBTOWER, count, from, to));
		while (!stack.empty()) {
			Record temp = stack.pop();
			if (temp.type == RType.PLATE || temp.n == 1) {
				System.out.println("Plate " + temp.n + " : " + temp.from + " ==> " + temp.to);
			} else {
				int k = 3 - temp.from - temp.to;
				this.stack.push(new Record(RType.SUBTOWER, temp.n - 1, k, temp.to));
				this.stack.push(new Record(RType.PLATE, temp.n, temp.from, temp.to));
				this.stack.push(new Record(RType.SUBTOWER, temp.n - 1, temp.from, k));
			}
		}

	}
	public static void main(String[] args) {
		HanoiTowerIter t  = new HanoiTowerIter();
		t.move(3, 0, 1);
	}
}
