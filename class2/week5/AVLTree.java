package class2.week5;

// balance tree
// 기본 속성은 같음.
// BST를 상속 받아서 한다. 
public class AVLTree extends BST {

	public AVLTree() {
		super();
	}

	public void AVLdelete(char c) {

		Node x = delete(c);  // it returns parent node of the deleted node
		Node y = null;
		Node z = null;  
		Node w = null;

		while (x!=null) { 

			if (!isBalanced(x)) {

				if (height(x.left)>=height(x.right)) {
					y = x.left;
					if (y.left!=null) {  // LL
						System.out.println(">>>  LL");

						z = y.left;
						w =rotateRight(x);
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString());

					}
					else {  // LR
						System.out.println(">>>  LR");

						z = y.right;
						rotateLeft(y);
						System.out.println("--- After Rotation ");
						showTree();
						w =rotateRight(x);
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString());

					}
				}
				else {
					y = x.right;
					if (y.left!=null) {  // RL
						System.out.println(">>>  RL");

						z = y.left;
						rotateRight(y);
						System.out.println("--- After Rotation ");
						showTree();

						w =rotateLeft(x);
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
					root = w;
				}
				x = w.parent;
			}
			else
				x=x.parent;
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

		if (x!=null) {
			//  x를 기준으로 left에 unbalance insert가 되었음. 
			if (c<x.data) {
				y= x.left;
				if (c<y.data) // LL imbalance
					rotateRight(x);
				else { //LR imbalance
					rotateLeft(y);
					rotateRight(x);
				}
			}
			else {
				y = x.right;
				if (c>y.data) //RR imbalance
					rotateLeft(x);
				else { // RL imbalance
					rotateRight(y);
					rotateLeft(x);
				}
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////

	// 모든 depth를 Count 
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

	private Node rotateLeft(Node x) {
		Node y = x.right;
		y.parent = x.parent;
		if (y.parent==null)
			root=y;
		else {
			if (x==x.parent.left) x.parent.left=y;
			else x.parent.right =y;
		}
		x.parent =y;
		x.right = y.left;
		if (y.left!=null)
			y.left.parent = x;
		y.left = x;
		return y;
	}

	private Node rotateRight(Node x) {
		Node y = x.left;
		y.parent = x.parent;
		if (y.parent==null)
			root=y;
		else {
			if (x==x.parent.left) x.parent.left=y;
			else x.parent.right =y;
		}
		x.parent =y;
		x.left = y.right;
		if (y.right!=null)
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

		AVLTree bt = new AVLTree();

		for (int i=0;i<inputSize;i++) {
			bt.insert(data[i]);   //  normal insert
		}
		
		// 일직선으로 쭉 늘어나게 하는 Tree, unbalance 된 tree
		System.out.println("Initial Skewed Tree");
		bt.showTree();
		System.out.println("Max. Height = "+bt.height());
		System.out.println("IPL = "+bt.IPL());
		System.out.println("\n/////////////////////////////////////////////////////////////////////");

		AVLTree bt1 = new AVLTree();

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