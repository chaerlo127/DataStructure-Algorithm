package class1.week7;

public class MyArrayQueue {
	MyArrayList3 queue;
	public MyArrayQueue(int size) {
		this.queue = new MyArrayList3(size);
		
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
		System.out.println(queue.capacity());
	}

	public static void main(String[] args) {
		MyArrayQueue me = new MyArrayQueue(10);
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
