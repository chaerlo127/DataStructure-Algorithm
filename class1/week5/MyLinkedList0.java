package week5;

public class MyLinkedList0 {

	int capa;
	int [][] mPool;
	int freePointer;
	int listPointer;
	int size;
	
	public MyLinkedList0() {
		capa = 20;
		mPool = new int[capa][2];
		initPool();
		freePointer = 0; // 할당할 수 있는 다음 인덱스를 가리킴
		listPointer = -1; // next index
		size = 0;
	}

	private void initPool() {
		for(int i=0;i<capa-1;i++) {
			mPool[i][1] = i+1; // next index
			mPool[capa-1][1] = -1; // 마지막에 존재하지 않는 것
		}
	}
	
	public int add(int data) { // add last, return index
		// 더이상의 capa 가 있다 없다 기준이 freePointer에 의한 것
		int index = 0;
		if (freePointer == -1) {
			System.out.println("Out-of-Memory!");
			return -1;
		}else {
			if(listPointer==-1) { // listPointer가 첫 번째라면
				listPointer = freePointer;
				freePointer = mPool[freePointer][1]; // new freePointer -> next index
				mPool[listPointer][0] = data; // data 
				mPool[listPointer][1] = -1; // next index
			}else {
				int temp = listPointer; // 0
				while(mPool[temp][1]!=-1) {  // 1 1  -> -1 index 1에는 값이 차있다. 
					temp = mPool[temp][1]; // temp의 next index 값을 저장
					index ++; //.?
				}
				mPool[temp][1] = freePointer; // 새 노드를 붇여 넣는다. 1, 1 -> 2
				freePointer = mPool[freePointer][1]; // [1][1] 이면 2로 free = 2, 1 (3)
				temp = mPool[temp][1]; // 새로운 노드로 변경 됨 temp = 2 ( 1, 1 -> 2 )
				mPool[temp][0]=data;
				mPool[temp][1]=-1;
			}
		}
		size ++;
		return index;
		
	}
	
	public int addFirst(int data) {
		if (freePointer == -1) {
			System.out.println("Out-of-Memory!");
			return -1;
		}else {
			int temp = listPointer;
			listPointer = freePointer;
			freePointer = mPool[freePointer][1]; 
			mPool[listPointer][0] = data; // data 
			mPool[listPointer][1] = temp; // next index
			size ++;
			return 0;
		}

	}
	
	//추가된 내용-----------------------------
	public int remove(int index) {
		if(listPointer==-1) {
			return -1;
		}else {
			int temp = 0;
			while(mPool[temp][1] != index) {
				temp = mPool[temp][1];
			}
			mPool[temp][1] = mPool[index][1];
			mPool[index][0] = 0;
			return 0;
		}
		
	}
	//추가된 내용--------------------------------
	public int sizeOf() {
		return size;
	}
	//추가된 내용--------------------------------
	public int arrSize() {
		return capa;
	}
	
	public String toString() {
		String ret = "";
		int temp = listPointer;
		
		while(temp!=-1) {
			ret = ret + mPool[temp][0] + ", ";
			temp = mPool[temp][1];
		}
		return ret;
	}
	
}


