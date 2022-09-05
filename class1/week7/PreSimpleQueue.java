package class1.week7;
// array는 의미가 없음 
// circle queue로 변경
public class PreSimpleQueue {
	private int[] queue;
	private int front, rear;
	private int maxSize;

	public PreSimpleQueue(int n) {
		maxSize = n;
		queue = new int[n];
		front = 0; 
		rear = 1; //저장 될 위치
	}

	public void enqueue(int data) { // add
		if (rear == maxSize) { 
			System.out.println("Queue Full");
		} else {
			queue[rear] = data;
			rear++;
		}
	}

	public int dequeue() {
		if (empty()) {
			System.out.println("Queue Empty");
			return -999;
		} else {
			front++;
			return queue[front];
		}
	}

	public boolean empty() {
		return (front+1) == rear;
	}

	public void showQueue() {
		for (int i = 0; i < maxSize; i++) {
			System.out.print(queue[i] + " ");
		}
		System.out.println("f = " + front + " r = " + rear);
	}

	public static void main(String[] args) {
		PreSimpleQueue me = new PreSimpleQueue(10);
		for (int i = 0; i < 100; i++) {
			if (Math.random() > 0.5) {
				me.enqueue(i);
			} else {
				me.dequeue();
			}me.showQueue();
		}
	}
}
