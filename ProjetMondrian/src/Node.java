
import java.awt.*;

public class Node {
	private double w,h,x,y,weight;
	private Node left;
	private Node right;
	private Color col;
	private boolean choosenDiv_X;
  private boolean isDivided;
	
	/* Construction of the root node
	 * 
	 * 
	 */
	public Node(double _w, double _h) {
		this.w = _w;
		this.h = _h;
		this.x = 0;
		this.y = 0;
		this.left = null;
		this.right = null;
		this.weight = (w*h)/Math.pow((w+h),1.5);
		this.col = Color.WHITE;
    this.choosenDiv_X = false;
    this.isDivided = false;
  }

	
	/*
	 * Construction of a node
	 */
	public Node(double _w, double _h, double _x, double _y, Color _col) {
		this.w = _w;
		this.h = _h;
		this.x = _x;
		this.y = _y;
		this.left = null;
		this.right = null;
		this.weight = (w*h)/(Math.pow((w+h),1.5));
		this.col = _col;
		this.choosenDiv_X = true;
		this.isDivided = false;
	}
	
	public boolean isChoosenDiv_X() {
		return choosenDiv_X;
	}

	public void setChoosenDiv_X(boolean choosenDiv_X) {
		this.choosenDiv_X = choosenDiv_X;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWeight() {
		return weight;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Color getCol() {
		return col;
	}

	
	public boolean getIsDivided(){
    return isDivided;
    }

  public void setIsDivided(boolean b){
    isDivided = b;
    }

    
	
}