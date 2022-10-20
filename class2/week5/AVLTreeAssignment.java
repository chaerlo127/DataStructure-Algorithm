package class2.week5;

/*Height: O(log n) -> worst case에서도 log n
 *Insert: O(log n)
 *delete: O(log n)
 * 
 * */
// balance binary search tree
// 기본 속성은 같음.
// BST를 상속 받아서 한다. 
public class AVLTreeAssignment extends BST {

	public AVLTreeAssignment() {
		super();
	}

	// AVL Tree를 지울 때, 균형에 맞게 지우기 위함. 
	public void AVLdelete(char c) {

		Node x = delete(c);  // it returns parent node of the deleted node, 삭제된 노드의 부모 노드 불러오기
		Node y = null;
		Node z = null;  
		Node w = null;

		while (x!=null) { 

			if (!isBalanced(x)) {

				if (height(x.left)>=height(x.right)) { // Left
					y = x.left;
					if (y.left!=null) {  // LL
						System.out.println(">>>  LL");

						z = y.left;
						w =rotateRight(x); // shift right, 균형을 맞춘 후, 균형을 맞춘 하위 Tree 중에서 root에 해당
						// root가 없는 경우라면 root가 된다.
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString()); // 데이터의 높이

					}
					else {  // LR
						System.out.println(">>>  LR");
						// LR은 어긋난 균형이 차례대로 z, y, x라고 한다면 z는 y보다 크며, y는 x보다 작다.
						// 이렇기 때문에 처음에 z, y의 위치를 변경해주는 rotateLeft를 해주고
						// LL 모양으로 변경이 되면 다시 rotateRight를 해준다.
						z = y.right;
						rotateLeft(y); // shift left LR -> LL
						System.out.println("--- After Rotation ");
						showTree();
						w =rotateRight(x); // shift right LL -> balanced Tree
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString());

					}
				}
				else { // Right
					y = x.right;
					if (y.left!=null) {  // RL
						System.out.println(">>>  RL");

						z = y.left;
						rotateRight(y); // RL --> RR
						System.out.println("--- After Rotation ");
						showTree();

						w = rotateLeft(x);
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString());

					}
					else {  // RR
						System.out.println(">>>  RR");

						z = y.right;
						w=rotateLeft(x);
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString());

					}
				}
				if (w.parent==null) {
					root = w; // w: rotate 후에 balanced 된 Tree의 root 값
				}
				x = w.parent; // x를 null로 만들어 주고 함수를 빠져 나온다.
			} 
			else
				x=x.parent; // x의 위로 올라가 imbalance 된 Tree를 또 찾는다.
		}  // of while-loop
	}


	public void AVLinsert(char c) {
		// 지금 들어간 node return 
		Node r = insert(c);
		// find x
		Node p = r.parent;
		// root 가 아니면 계속 찾아나가기 
		// 균형이 깨진 곳을 찾아나간다.
		// 균형이 깨진 제일 위의 것을 찾아냄. 
		while (p!=null) {
			if (!isBalanced(p))
				break;
			p=p.parent;
		}
		
		// 균형이 깨진 곳의 root node
		Node x = p;
		Node y = null;
		// c는 균형 깨진 곳의 root node의 손자
		if (x!=null) {
			//  x를 기준으로 left에 imbalance insert가 되었음. 
			if (c<x.data) {
				y= x.left;
				if (c<y.data) // LL imbalance: x가 parent 보다 모두 값이 작다면
					rotateRight(x);
				else { //LR imbalance: x가 조부모보다는 작고 부모보다는 크다면 
					rotateLeft(y);
					rotateRight(x);
				}
			}
			else {
				y = x.right;
				if (c>y.data) //RR imbalance: x가 parent 보다 모두 값이 크다면 
					rotateLeft(x);
				else { // RL imbalance
					rotateRight(y);
					rotateLeft(x);
				}
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////
	// Internal Path Length
	// 모든 경로의 depth를 Count 
	// 나에다가 각각의 depth를 다 더하는 것
	// 오답: 1 + IPL(x.left) + IPL(x.right) return count 로 하게 되면 
	// --> Count되어 있는 node가 다시 count 된다. 
	public int IPL() { 
		int depthCounter=0;
		return iplCount(root, depthCounter);
	}

	private int iplCount(Node p, int depthCounter) {
		if (p==null) return depthCounter;
		depthCounter++;
		int leftCount=0, rightCount=0;
		leftCount = iplCount(p.left,depthCounter);
		rightCount = iplCount(p.right,depthCounter);
		return depthCounter+leftCount+rightCount;
	}

	// 오른쪽으로 치우쳐져 있는 경우, 왼 쪽으로 균형을 옮긴다. 
	private Node rotateLeft(Node x) {
		Node y = x.right; // x를 기준으로 오른 쪽으로 치우쳐져 있기 때문에 x.right 
		y.parent = x.parent; // y를 맨위로 올림
		if (y.parent==null) // y가 root라면
			root=y;
		else {
			if (x==x.parent.left) x.parent.left=y; // x가 parent의 left 자식이라면 -> x.parent.left = y
			else x.parent.right =y; // 아니라면?
		}
		x.parent =y; // x의 부모 y로
		x.right = y.left; // x의 오른 쪽 자식은 y의 왼쪽 자식으로 변경 됨. 
		// y는 x보다 큰 경우임. 따라서, y.left는 x보다 크면서 y 보다 작은 경우를 의미
		if (y.left!=null) // y.left의 값이 있다면
			y.left.parent = x; //y.left의 parent를 y -> x 로 변경 
		y.left = x; // y.left는 x가 됨. 
		return y;
	}

	private Node rotateRight(Node x) {
		Node y = x.left; // rotateRight 를 하기 위해서는 왼쪽으로 치우쳐져 있음. 
		y.parent = x.parent; // y를 root 위치로 이동
		if (y.parent==null)
			root=y; // parent 가 업으면 y가 root
		else {
			if (x==x.parent.left) x.parent.left=y; // x가 부모에게 왼쪽 자식이면 y를 왼쪽 자식으로 변경
			else x.parent.right =y; // 오른쪽 자식이면 부모의 오른 쪽 자식으로 변경
		}
		x.parent =y; // x의 parent의 내용을 y로 변경했으니, x의 부모 변경
		x.left = y.right; // y의 자식을 x로 이동 시킴. 
		if (y.right!=null) // y의 right에 값이 있다면, x로 이동해줘야 함.
			y.right.parent = x;
		y.right = x;
		return y;
	}

	// balance가 되어 있는지 아닌지 확인하는 method
	protected boolean isBalanced(Node p) {
		if (p==null)
			return true;
		if (Math.abs(height(p.left)-height(p.right))<=1)
			return true;
		else return false;
	}

	/////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		int inputSize =26;
		char [] data = new char[inputSize];

		for (int i=0;i<inputSize;i++)
			data[i] =(char)((int)'A'+i);  //  A, B, C, D, ...X, Y, Z

		AVLTreeAssignment bt = new AVLTreeAssignment();

		for (int i=0;i<inputSize;i++) {
			bt.insert(data[i]);   //  normal insert
		}
		
		// 일직선으로 쭉 늘어나게 하는 Tree, unbalance 된 tree
		System.out.println("Initial Skewed Tree");
		bt.showTree();
		System.out.println("Max. Height = "+bt.height());
		System.out.println("IPL = "+bt.IPL());
		System.out.println("\n/////////////////////////////////////////////////////////////////////");

		AVLTreeAssignment bt1 = new AVLTreeAssignment();

		for (int i=0;i<26;i++) {
			bt1.AVLinsert(data[i]);
		}
		System.out.println("\nAVL Tree : BalancedInsert Only");
		bt1.showTree();
		System.out.println("Max. Height = "+bt1.height());
		System.out.println("IPL = "+bt1.IPL());
		System.out.println("\n/////////////////////////////////////////////////////////////////////");

		System.out.println("\nAVL Tree : BalancedDelete");

		System.out.println("\n\nAfter deleting Y ");		
		bt1.AVLdelete('Y');
		bt1.showTree();
		System.out.println("\n\nAfter deleting X");		
		bt1.AVLdelete('X');
		bt1.showTree();
		System.out.println("\nAfter deleting Q ");		
		bt1.AVLdelete('Q');
		bt1.showTree();
		System.out.println("\nAfter deleting T ");		
		bt1.AVLdelete('T');
		bt1.showTree();
		System.out.println("\nAfter deleting U ");		
		bt1.AVLdelete('U');
		bt1.showTree();

	}
}