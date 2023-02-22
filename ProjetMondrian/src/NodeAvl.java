
public class NodeAvl {
	
	private Node n;
	private NodeAvl left;
	private NodeAvl right;
	private int height;

	public NodeAvl(Node _n) {
			this.n = _n;
			this.left = null;
			this.right = null;
			this.height = 1;
			
	}
	public NodeAvl() {
		this.n = null;
		this.left = null;
		this.right = null;
		this.height = 1;
		
}
	
	public int getHeight() {
		return height;
	}
	
	public static int getHeight(NodeAvl n) {
		if (n == null) 
			return 0;
		return n.getHeight();
	}
	
	
	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeightN() {
		return n.getWeight();
	}


	public NodeAvl getLeft() {
		return left;
	}


	public void setLeft(NodeAvl left) {
		this.left = left;
	}


	public NodeAvl getRight() {
		return right;
	}


	public void setRight(NodeAvl right) {
		this.right = right;
	}


	public Node getN() {
		return n;
	}
	
	public void setN(Node _n) {
		this.n = _n;
	}
	
}
