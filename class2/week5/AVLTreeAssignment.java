package class2.week5;

/*Height: O(log n) -> worst case������ log n
 *Insert: O(log n)
 *delete: O(log n)
 * 
 * */
// balance binary search tree
// �⺻ �Ӽ��� ����.
// BST�� ��� �޾Ƽ� �Ѵ�. 
public class AVLTreeAssignment extends BST {

	public AVLTreeAssignment() {
		super();
	}

	// AVL Tree�� ���� ��, ������ �°� ����� ����. 
	public void AVLdelete(char c) {

		Node x = delete(c);  // it returns parent node of the deleted node, ������ ����� �θ� ��� �ҷ�����
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
						w =rotateRight(x); // shift right, ������ ���� ��, ������ ���� ���� Tree �߿��� root�� �ش�
						// root�� ���� ����� root�� �ȴ�.
						System.out.println("--- After Rotation ");
						showTree();

						if (w!=null)
							System.out.println("> Rotate returns >> "+w.toString()); // �������� ����

					}
					else {  // LR
						System.out.println(">>>  LR");
						// LR�� ��߳� ������ ���ʴ�� z, y, x��� �Ѵٸ� z�� y���� ũ��, y�� x���� �۴�.
						// �̷��� ������ ó���� z, y�� ��ġ�� �������ִ� rotateLeft�� ���ְ�
						// LL ������� ������ �Ǹ� �ٽ� rotateRight�� ���ش�.
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
					root = w; // w: rotate �Ŀ� balanced �� Tree�� root ��
				}
				x = w.parent; // x�� null�� ����� �ְ� �Լ��� ���� ���´�.
			} 
			else
				x=x.parent; // x�� ���� �ö� imbalance �� Tree�� �� ã�´�.
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
		// c�� ���� ���� ���� root node�� ����
		if (x!=null) {
			//  x�� �������� left�� imbalance insert�� �Ǿ���. 
			if (c<x.data) {
				y= x.left;
				if (c<y.data) // LL imbalance: x�� parent ���� ��� ���� �۴ٸ�
					rotateRight(x);
				else { //LR imbalance: x�� ���θ𺸴ٴ� �۰� �θ𺸴ٴ� ũ�ٸ� 
					rotateLeft(y);
					rotateRight(x);
				}
			}
			else {
				y = x.right;
				if (c>y.data) //RR imbalance: x�� parent ���� ��� ���� ũ�ٸ� 
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
	// ��� ����� depth�� Count 
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

	// ���������� ġ������ �ִ� ���, �� ������ ������ �ű��. 
	private Node rotateLeft(Node x) {
		Node y = x.right; // x�� �������� ���� ������ ġ������ �ֱ� ������ x.right 
		y.parent = x.parent; // y�� ������ �ø�
		if (y.parent==null) // y�� root���
			root=y;
		else {
			if (x==x.parent.left) x.parent.left=y; // x�� parent�� left �ڽ��̶�� -> x.parent.left = y
			else x.parent.right =y; // �ƴ϶��?
		}
		x.parent =y; // x�� �θ� y��
		x.right = y.left; // x�� ���� �� �ڽ��� y�� ���� �ڽ����� ���� ��. 
		// y�� x���� ū �����. ����, y.left�� x���� ũ�鼭 y ���� ���� ��츦 �ǹ�
		if (y.left!=null) // y.left�� ���� �ִٸ�
			y.left.parent = x; //y.left�� parent�� y -> x �� ���� 
		y.left = x; // y.left�� x�� ��. 
		return y;
	}

	private Node rotateRight(Node x) {
		Node y = x.left; // rotateRight �� �ϱ� ���ؼ��� �������� ġ������ ����. 
		y.parent = x.parent; // y�� root ��ġ�� �̵�
		if (y.parent==null)
			root=y; // parent �� ������ y�� root
		else {
			if (x==x.parent.left) x.parent.left=y; // x�� �θ𿡰� ���� �ڽ��̸� y�� ���� �ڽ����� ����
			else x.parent.right =y; // ������ �ڽ��̸� �θ��� ���� �� �ڽ����� ����
		}
		x.parent =y; // x�� parent�� ������ y�� ����������, x�� �θ� ����
		x.left = y.right; // y�� �ڽ��� x�� �̵� ��Ŵ. 
		if (y.right!=null) // y�� right�� ���� �ִٸ�, x�� �̵������ ��.
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

		AVLTreeAssignment bt = new AVLTreeAssignment();

		for (int i=0;i<inputSize;i++) {
			bt.insert(data[i]);   //  normal insert
		}
		
		// ���������� �� �þ�� �ϴ� Tree, unbalance �� tree
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