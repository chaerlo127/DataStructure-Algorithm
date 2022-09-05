package class1.practice;


public class Simplequeue {
	private int[] queue;
	private int front, rear;
	private int maxSize, size;
	
	public Simplequeue(int n) {
		this.maxSize = n;
		this.queue = new int[n];
		this.front = 0;
		this.rear = 1;
	}
	public void enqueue(int data) {
		if(rear == front) {
			System.out.println("queue full");
		}else {
			size ++;
			queue[rear] = data;
			rear = (rear+1)%maxSize;
		}
	}
	
	public int dequeue() {
		if(empty()) {
			System.out.println("queue empty");
			return -1;
		}else {
			size --;
			front = (front+1)%maxSize;
			return queue[front];
		}
	}
	public boolean empty() {
		return (front+1)%maxSize == rear;
	}
	
	public void showQueue() {
		for(int i = 0; i< maxSize ; i++) {
			System.out.print(queue[i] + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Simplequeue me = new Simplequeue(10);
		for (int i = 0; i < 100; i++) {
			if (Math.random() > 0.5) {
				me.enqueue(i);
			} else {
				me.dequeue();
			}me.showQueue();
		}
	}
}
