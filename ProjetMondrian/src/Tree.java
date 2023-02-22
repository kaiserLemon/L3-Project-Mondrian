import java.awt.*;
import java.util.Random;
import java.util.Vector;




public class Tree {
  private int nbFeuille;
  private double minDimensionCoupe;
  private double memeCouleurProb;
  private double proportionCoupe;
  private double largeurLigne;
  private Vector<Color> TabC;
  private Node r;
  private Random rand;
  private Color[] tab = {Color.WHITE, Color.red, Color.black, Color.BLUE, Color.yellow};

  public Tree(Node _r, int _nbFeuille, double _proportionCoupe, double _minDC, double _memeCouleurProb, double _largeurLigne, Random _rand) {
    nbFeuille = _nbFeuille;
    minDimensionCoupe = _minDC;
    r = _r;
    proportionCoupe = _proportionCoupe;
    largeurLigne = _largeurLigne;
    rand = _rand;
    this.memeCouleurProb = _memeCouleurProb;
  }

  /*
   * Choose a color by evaluating the odd of getMemeCouleurProb
   * 
   */
  public Color chooseColor(Node r) {
    if (rand.nextDouble() <= getMemeCouleurProb()) {
      return r.getCol();
    } else {
      int i = (int) (rand.nextDouble() * (5));
      return tab[i];
    }
  }

  public boolean isEmpty(Node n) {
    return n == null;
  }

  /*
   * Comparison of right and left weight leaf to pick the heaviest for division
   */
  public NodeAvl chooseLeaf(AVL Avl) {
	  NodeAvl temp = Avl.searchMax(Avl.getRoot());
	  return temp;
  }

  /*
   *Choose a division 
   */
  public double chooseDivision(Node r) {
    double prob = rand.nextDouble();
    double div = 0;
    if (r.getCol() == Color.WHITE && r.getLeft() == null && r.getRight() == null) {
      // If Y is out of the autorized cut zone then re-set Y
      div = (rand.nextDouble() * (r.getH()));
      r.setChoosenDiv_X(false);
      while ((div < (r.getH() * proportionCoupe)) || (div > (r.getH() * (1 - proportionCoupe)))) {
        div = (rand.nextDouble() * r.getH());
      }

    } else {
      // Choose X or Y

      if (prob <= r.getW() / (r.getH() + r.getW())) {
        div = (rand.nextDouble() * r.getW());
        r.setChoosenDiv_X(true);
        while (div < r.getW() * proportionCoupe || div > r.getW() * (1 - proportionCoupe)) {
          
          div = (rand.nextDouble() * r.getW());
        }

      } else {
        r.setChoosenDiv_X(false);
        while (div < r.getH() * proportionCoupe || div > r.getH() * (1 - proportionCoupe)) {
          div = (rand.nextDouble() * (r.getH()));
        }
      }
    }
    return div;
  }


  /*
   * Count the number of leaf
   * 
   */
  public int nbOfLeaves(Node r) {
    if (r.getRight() == null && r.getLeft() == null) {
      return 1;
    } else {
      return nbOfLeaves(r.getLeft()) + nbOfLeaves(r.getRight());
    }
  }

  /*
   * Generate a random tree
   *
   */
  public void generateRandomTree(Node r, AVL tree) {

    // numberOfLeaves reach the treshold or the dimension of the region is not big
    // enough
    NodeAvl al = chooseLeaf(tree);
    Node l = al.getN();
    tree.setRoot(tree.deleteNode(l, tree.getRoot()));
    if (nbOfLeaves(r) >= nbFeuille || minDimensionCoupe > l.getH() * l.getW()) {
      return;

    } else {

      double div = chooseDivision(l);
    
      if (l.isChoosenDiv_X()) {

        l.setLeft(new Node(div, l.getH(), l.getX(), l.getY(), chooseColor(l) ));
        tree.setRoot(tree.insertNode(l.getLeft(), tree.getRoot()));
        
        l.setRight(
            new Node(l.getW()- div, l.getH(), l.getX()+div, l.getY(), chooseColor(l)) );
        tree.setRoot(tree.insertNode(l.getRight(), tree.getRoot()));
        generateRandomTree(r, tree);
        
      } else {
        
        l.setLeft(new Node(l.getW(), div, l.getX(), l.getY(), chooseColor(l)));
        tree.setRoot(tree.insertNode(l.getLeft(), tree.getRoot()));
        
        l.setRight( new Node(l.getW(), l.getH() - div, l.getX(), l.getY()+div, chooseColor(l)) );
        tree.setRoot(tree.insertNode(l.getRight(), tree.getRoot()));
        generateRandomTree(r, tree);
      }
      
      if (nbOfLeaves(r) > nbFeuille) {
        l.setRight(null);

      } else {
        generateRandomTree(r, tree);
      }

    }
    
  }

  ///////////////////////////////////////////////////////////////////////////////
  public int getNbFeuille() {
    return nbFeuille;
  }

  public double getMinDimensionCoupe() {
    return minDimensionCoupe;
  }

  public double getMemeCouleurProb() {
    return memeCouleurProb;
  }

  public double getProportionCoupe() {
    return proportionCoupe;
  }

  public Vector<Color> getTabC() {
    return TabC;
  }

  public Node getRoot() {
    return r;
  }
  public double getlargeurLigne(){
    return largeurLigne;
  }

  
}
