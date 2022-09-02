package week7;

// 동적 구조
public class SimpleQueue {
	private int[] queue;
	private int front, rear;
	private int maxSize;
	private int size;

	public SimpleQueue(int n) {
		maxSize = n;
		queue = new int[n];
		front = 0; 
		rear = 1; //저장 될 위치
	}

	public void enqueue(int data) { // add
		if (rear == front) { 
			System.out.println("Queue Full");
		} else {
			size ++;
			queue[rear] = data;
			rear = (rear+1) % maxSize;
		}
	}

	public int dequeue() {
		if (empty()) {
			System.out.println("Queue Empty");
			return -999;
		} else {
			size --;
			front = (front+1) % maxSize;
			return queue[front];
		}
	}

	public boolean empty() {
		return (front+1)%maxSize == rear;
	}

	public void showQueue() {
		for (int i = 0; i < maxSize; i++) {
			System.out.print(queue[i] + " ");
		}
		System.out.println("f = " + front + " r = " + rear + " size = " + size);
	}

	public static void main(String[] args) {
		SimpleQueue me = new SimpleQueue(10);
		for (int i = 0; i < 100; i++) {
			if (Math.random() > 0.5) {
				me.enqueue(i);
			} else {
				me.dequeue();
			}me.showQueue();
		}
	}
}
