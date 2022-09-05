package class1.week2;
public class SearchEx {
	
	int count;
	int[] data;
	// 명시적으로 배열을 파라미터로 둔 것을 빼고, 암시적 파라미터로 생성자에서 설정한다.
	//바뀌지 않는 값은 명시적으로 작성해도 크게 달라질 것은 없다.
	public SearchEx(int[] in) {
		data = in;
	}
	public void resetCount() {
		count = 0;
	}
	public int getCount() {
		return count;
	}
	//명시적 파라미터
	public int search(int n, int x) {
		count++;
		if (n==0) {
			return -1;
		}
		if(x==data[n-1]) {
			return n-1;
		}else return search(n-1, x);
	}
	
	public int searchIter(int n, int x) {
		for(int i=n-1;i>0; i--) {
			count++;
			if(data[i]==x) {
				return i;
			}
		}
		return -1;
	}
	
	public int binarySearch(int p, int r, int x) {
		count ++;
		if(p>r) {
			return -1;
		}
		int m=(p+r)/2;
		if(x<data[m])
			return binarySearch(p, m-1, x);
		else if (x>data[m])
			return binarySearch(m+1, r, x);
		else {
			return m;
		}
	}
	
	public static void main(String[] args) {
		int[] input = {1, 3, 5, 7, 9, 11, 14, 18, 20, 22, 26, 30, 32, 35, 41, 46, 55};
		
		SearchEx me = new SearchEx(input);
		me.resetCount();
		System.out.println(me.search(input.length, 26)+" count = "+me.getCount());
		me.resetCount();
		System.out.println(me.searchIter(input.length, 26)+" count = "+me.getCount());
		me.resetCount();
		System.out.println(me.binarySearch(0, input.length, 26)+" count = "+me.getCount());
	}

	

}
