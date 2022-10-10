package class2.week5;

// balance tree
// �⺻ �Ӽ��� ����.
// BST�� ��� �޾Ƽ� �Ѵ�. 
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
		// ���� �� node return 
		Node r = insert(c);
		// find x
		Node p = r.parent;
		// root �� �ƴϸ� ��� ã�Ƴ����� 
		// ������ ���� ���� ã�Ƴ�����.
		// ������ ���� ���� ���� ���� ã�Ƴ�. 
		while (p!=null) {
			if (!isBalanced(p))
				break;
			p=p.parent;
		}
		// ������ ���� ���� root node
		Node x = p;
		Node y = null;

		if (x!=null) {
			//  x�� �������� left�� unbalance insert�� �Ǿ���. 
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

	// ��� depth�� Count 
	// �����ٰ� ������ depth�� �� ���ϴ� ��
	// ����: 1 + IPL(x.left) + IPL(x.right) return count �� �ϰ� �Ǹ� 
	// --> Count�Ǿ� �ִ� node�� �ٽ� count �ȴ�. 
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

	// balance�� �Ǿ� �ִ��� �ƴ��� Ȯ���ϴ� method
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
		
		// ���������� �� �þ�� �ϴ� Tree, unbalance �� tree
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