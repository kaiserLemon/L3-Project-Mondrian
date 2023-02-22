import java.awt.*;
import java.util.Random;

public class BetterTree{
    private Node node;
    private int nbFeuille;
    private double memeCouleurProb;
    private int largeurLigne;;
    private Random rand;
    private Color[] tab = {Color.WHITE, Color.red, Color.black, Color.BLUE, Color.yellow};

    public BetterTree(Node node_, int nbFeuille_, double memeCouleurProb_, Random rand_){
        node = node_;
        nbFeuille = nbFeuille_;
        memeCouleurProb = memeCouleurProb_;
        if(node.getH() < node.getW()){
          largeurLigne = (int)(node.getW()*0.01);
        }else{
          largeurLigne = (int)(node.getH()*0.01);
        }
        
        rand = rand_;
        
    }

    public int getlargeurLigne(){
      return largeurLigne;
    }
    public double getMemeCouleurProb() {
        return memeCouleurProb;
      }

    public Node getRoot() {
        return node;
    }

        // Count the number of leaf
    public int nbOfLeaves(Node r) {
      if (r.getRight() == null && r.getLeft() == null) {
        return 1;
      } else {
        return nbOfLeaves(r.getLeft()) + nbOfLeaves(r.getRight());
      }
    }

    public Color chooseColor(Node r) {
        if (rand.nextDouble() <= getMemeCouleurProb()) {
          return r.getCol();
        } else {
          int i = (int) (rand.nextDouble() * (5));
          return tab[i];
        }
      }
   
      /*
       * Choose which division is better, if the width is higher than height then divide width.
       */
    public double chooseDivision(Node node){
      double div;
        if (node.getW() <= node.getH()){
          div = rand.nextDouble()*node.getH();
          node.setChoosenDiv_X(false);
          while ((div < (largeurLigne)) || (div > (node.getH()- largeurLigne))) {
            div = (rand.nextDouble() * node.getH());
          }
        }else{
          div = rand.nextDouble()*node.getW();
          node.setChoosenDiv_X(true);
          while ((div < (largeurLigne)) || (div > (node.getW() - largeurLigne))) {
            div = (rand.nextDouble() * node.getW());
          }
        }
        
        return div;
      }

    public NodeAvl chooseLeaf(AVL Avl) {
        NodeAvl temp = Avl.searchMax(Avl.getRoot());
        return temp;
    }

    public void generateBetterTree(Node r, AVL tree){
      NodeAvl al = chooseLeaf(tree);
      Node l = al.getN();
      tree.setRoot(tree.deleteNode(l, tree.getRoot()));
        if (nbOfLeaves(node) >= nbFeuille ){
            return;
          } else {

            double div = chooseDivision(l);
          
            if (l.isChoosenDiv_X()) {
      
              l.setLeft(new Node(div, l.getH(), l.getX(), l.getY(), chooseColor(l) ));
              tree.setRoot(tree.insertNode(l.getLeft(), tree.getRoot()));
              
              
              l.setRight(
                  new Node(l.getW()- div, l.getH(), l.getX()+div, l.getY(), chooseColor(l)) );
              tree.setRoot(tree.insertNode(l.getRight(), tree.getRoot()));
              
             
            } else {
              
              l.setLeft(new Node(l.getW(), div, l.getX(), l.getY(), chooseColor(l)));
              tree.setRoot(tree.insertNode(l.getLeft(), tree.getRoot()));
              
              l.setRight( new Node(l.getW(), l.getH() - div, l.getX(), l.getY()+div, chooseColor(l)) );
              tree.setRoot(tree.insertNode(l.getRight(), tree.getRoot()));
            }
            
            if (nbOfLeaves(r) > nbFeuille) {
              l.setRight(null);
      
            } else {
              generateBetterTree(r, tree);
            }
      
          }
        }
        

}