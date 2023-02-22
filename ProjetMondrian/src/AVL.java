/*
 * @author Andy TORRES, Stéphane RAMAHEFARINAIVO 585k
 * Create an AVL 
 */

public class AVL {
  private NodeAvl r;

  public AVL(NodeAvl r) {
    this.r = r;
  }

  public void setRoot(NodeAvl insertNode) {
    r = insertNode;
  }

  public NodeAvl getRoot(){
    return r;
  }

  public NodeAvl searchMax(NodeAvl na){
    if (na.getRight() == null)
      return na;
    return searchMax(na.getRight());
  }

  public int getBalanced(NodeAvl n) {
    if (n == null)
      return 0;
    return NodeAvl.getHeight(n.getLeft()) - NodeAvl.getHeight(n.getRight());
  }

//////////////////////////////////////////////////////////////////////////////////////

  /*
   * Search the max between two int
   */
  public int max(int g, int d) {
    if (g < d)
      return d;
    return g;
  }

  /*
   * Do a right rotate 
   */
  public NodeAvl rightRotate(NodeAvl y) {
    NodeAvl x = y.getLeft();
    y.setLeft(x.getRight());
    x.setRight(y);
    
    y.setHeight(max(NodeAvl.getHeight(y.getLeft()), NodeAvl.getHeight(y.getRight())) + 1);
    x.setHeight(max(NodeAvl.getHeight(x.getLeft()), NodeAvl.getHeight(x.getRight())) + 1);
    return x;
  }

  /*
   * Do a left rotate
   */
  public NodeAvl leftRotate(NodeAvl A) {
    NodeAvl B = A.getRight();
    A.setRight(B.getLeft());
    B.setLeft(A);
    //Equilibre des balances
    A.setHeight(max(NodeAvl.getHeight(A.getLeft()), NodeAvl.getHeight(A.getRight())) + 1);
    B.setHeight(max(NodeAvl.getHeight(B.getLeft()), NodeAvl.getHeight(B.getRight())) + 1);
    return B;
  }

  /*
   * Balanced the balance factor of a node
   */
  public NodeAvl equilibrium(Node node, NodeAvl na){
    int balanceFactor = getBalanced(na);
      if (balanceFactor > 1) {
        if (node.getWeight() < na.getLeft().getWeightN()) {
          return rightRotate(na);
        } else if (node.getWeight() > na.getLeft().getWeightN()) {
          na.setLeft(leftRotate(na.getLeft()));
          return rightRotate(na);
        }
      }
      if (balanceFactor < -1) {
        if (node.getWeight() > na.getRight().getWeightN()) {
          return leftRotate(na);
        } else if (node.getWeight() < na.getRight().getWeightN()) {
          na.setRight(rightRotate(na.getRight()));
          return leftRotate(na);
        }
      }return na;
    }

  /*
   * Insert a node in the AVL
   */
  public NodeAvl insertNode(Node node, NodeAvl na) {

    // Search 
    if (na == null)
      return new NodeAvl(node);
    if (na.getWeightN() < node.getWeight())
    	na.setRight(insertNode(node, na.getRight()));
    else if (na.getWeightN() >= node.getWeight())
    	na.setLeft(insertNode(node, na.getLeft()));
    else
    	return na;

    // Update the balance factor of each node
    // And, balance the tree
    na.setHeight(1 + max(NodeAvl.getHeight(na.getLeft()), NodeAvl.getHeight(na.getRight())));
    return equilibrium(node, na);
  }

  /*
   * Search the node with the minimum value
   */
NodeAvl nodeWithMimumValue(NodeAvl node) {
    NodeAvl current = node;
    while (current.getLeft() != null)
      current = current.getLeft();
    return current;
}

/*
 * Delete a node
 */
 NodeAvl deleteNode(Node node, NodeAvl na) {

    // Find the node to be deleted and remove it
    if (na == null)
      return na;
    if (node.getWeight() < na.getWeightN())
      na.setLeft(deleteNode(node, na.getLeft()));
    else if (node.getWeight() > na.getWeightN())
      na.setRight(deleteNode(node, na.getRight()));
    else {
      if ((na.getLeft() == null) || (na.getRight() == null)) {
        NodeAvl temp = null;
        if (temp == na.getLeft())
          temp = na.getRight();
        else
          temp = na.getLeft();
        if (temp == null) {
          temp = na;
          na = null;
        } else
          na = temp;
      } else {
        NodeAvl temp = nodeWithMimumValue(na.getRight());
        na.setN(temp.getN());
        na.setRight(deleteNode(temp.getN(), na.getRight()));
      }
    }
    if (na == null)
      return na;

    // Update the balance factor of each node and balance the tree
    na.setHeight(max(NodeAvl.getHeight(na.getLeft()), NodeAvl.getHeight(na.getRight())) + 1);
    int balanceFactor = getBalanced(na);
    if (balanceFactor > 1) {
      if (getBalanced(na.getLeft()) >= 0) {
        return rightRotate(na);
      } else {
        na.setLeft(leftRotate(na.getLeft()));
        return rightRotate(na);
      }
    }
    if (balanceFactor < -1) {
      if (getBalanced(na.getRight()) <= 0) {
        return leftRotate(na);
      } else {
        na.setRight(rightRotate(na.getRight()));
        return leftRotate(na);
      }
    }return na;
  }


}

  