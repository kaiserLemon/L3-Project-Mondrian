import java.util.Random;

public class strategy {
    
    private int nbFeuille;
    private double minDimensionCoupe;
    private double memeCouleurProb;
    private double proportionCoupe;
    private double largeurLigne;
    private Random rand;
    private Tree tree_;
    private Node r;
    private NodeAvl nA;
    private AVL avTree;
    private BetterTree treeb;

    /*
     * Default strategy
     */
    public strategy(Node _r, int _nbFeuille, double _proportionCoupe, double _minDC, double _memeCouleurProb, double _largeurLigne, Random _rand){
        nbFeuille = _nbFeuille;
        minDimensionCoupe = _minDC;
        rand = _rand;
        proportionCoupe = _proportionCoupe;
        largeurLigne = _largeurLigne;
        r = _r;
        memeCouleurProb = _memeCouleurProb;
        tree_ = new Tree(_r, _nbFeuille, _proportionCoupe, _minDC, _memeCouleurProb, _largeurLigne, _rand);
        nA = new NodeAvl(_r);
        avTree = new AVL(nA);
    }

    /*
     * Strategy made by us
     */
    public strategy(Node node_, int nbFeuille_, double memeCouleurProb_, Random rand_){
        r = node_;
        nbFeuille = nbFeuille_;
        memeCouleurProb = memeCouleurProb_;
        rand = rand_;
        treeb = new BetterTree(r,nbFeuille_,memeCouleurProb_,rand_);
        nA = new NodeAvl(r);
        avTree = new AVL(nA);
    }

    public Tree getTree(){
        return tree_;
    }
    public BetterTree getBetterTree(){
        return treeb;
    }

    public void generate(){
        getTree().generateRandomTree(r, avTree);
    }

    public void generateBetter(){
        getBetterTree().generateBetterTree(r, avTree);
    }
}
