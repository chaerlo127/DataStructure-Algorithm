package class1.week15;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

//  final exam-2 
public class WithdrawableQueue {

	LinkedList<Integer> queue=new LinkedList<>();
	
	public void enqueue(int d) {
		this.queue.addLast(d);
	}
	
	public int dequeue() {
		return this.queue.removeFirst();
	}
	
	public void withdraw(int d) {
		if (queue.contains(d)) {
			int q = this.queue.indexOf(d);
			this.queue.remove(q);
		}
		else 
			System.out.println("존재하지 않는 data : "+d);
	}
	
	public void showQueue() {
		String result = " ";
		for(int i = 0; i<this.size(); i++) {
			result = result + this.queue.get(i) + ", ";
		}
		System.out.println(result);
	}
	
	public int size() {
		int size = 0;
		int i = 0;
		while(!(this.queue.get(i) == this.queue.getLast())) {
			size++;
			i++;
		}
		if(this.queue.getLast()!=null) { // while문을 사용하면 마지막 데이터는 size up이 되지 않기 때문에 추가를 해준다.
			size++;
		}
		return size;
	}

	public static void main(String[] args) {
		int max=30;
	
		WithdrawableQueue s = new WithdrawableQueue();
		
		Random rand1 = new Random(7);
		Random rand2 = new Random(21);
		ArrayList<Integer> data = new ArrayList<>() ;
		
		for (int i=0;i<max;i++) {
			if (rand1.nextInt(10)>=2) {
				int d=rand2.nextInt(1000);
				data.add(d);
				s.enqueue(d);
				System.out.println(d+" is enqueued ");

			}
			else {
				int del=s.dequeue();
				System.out.println(del+" is deleted ");
				data.remove(data.indexOf(del));
			}
		}
		System.out.println("current size is "+s.size());
		for (int i=0;i<s.size()/2;i++) {
			s.showQueue();
			System.out.println(data.get(2*i));
			s.withdraw(data.get(2*i));
			System.out.println("current size is "+s.size());

		}
		s.withdraw(1234);
	}
}
