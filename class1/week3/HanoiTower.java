package class1.week3;

public class HanoiTower {
	public void move(int n, int i, int j) { // i = from, j = to
		if(n==1) {
			System.out.println("Plate " + n +": " + i + " ==> " + j);
			return;
		}else {
			int k = 3-i-j;
			move(n-1, i, k);
			System.out.println("Plate " + n +": " + i + " ==> " + j);
			move(n-1, k, j);
			return;
		}
	}
	public static void main(String[] args) {
		HanoiTower myT = new HanoiTower();
		
		myT.move(3, 0, 1);
	}
}
