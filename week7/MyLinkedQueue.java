package week7;

public class MyLinkedQueue {
	MyLinkedList3 queue;
	public MyLinkedQueue() {
		this.queue = new MyLinkedList3();
		
	}

	public void enqueue(MyData data) { // add
		this.queue.addLast(data);
	}

	public MyData dequeue() {
		return this.queue.removeFirst();
	}

	public boolean empty() {
		return (this.queue.sizeOf() == 0);
	}

	public void showQueue() {
		System.out.println(queue.toString());
	}

	public static void main(String[] args) {
		MyLinkedQueue me = new MyLinkedQueue();
		me.enqueue(new MyData("abc", 1));
		me.enqueue(new MyData("def", 2));
		me.enqueue(new MyData("ghi", 5));
		me.showQueue();
		System.out.println(me.dequeue());
		System.out.println(me.dequeue());
		
		for(int i = 0; i<100 ; i++) {
			me.enqueue(new MyData("" + i, i));
			System.out.println(me.dequeue());
		}
		me.showQueue();

	}

}
