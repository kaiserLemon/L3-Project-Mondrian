import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Vector;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * Helper class to create, modify and save images.
 * @author Mathieu Vavrille
 * For example:
 * ```java
 * Image img = new Image (100, 200);
 * img.setRectangle(10, 20, 50, 30, Color.YELLOW); // Color should be imported with `import java.awt.Color`
 * img.save("test.png");
 * ```
 */
public class Image
{
  private final BufferedImage image; // The image

  /** Constructs an empty image (initially black) of width `width` and height `height` */
  public Image(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Sets one pixel of the image.
   * WARNING : NO CHECK IS DONE. IF YOU WRITE OUTSIDE THE IMAGE IT WILL RAISE AN ERROR
   */
  public void setPixel(int x, int y, Color col) {
    image.setRGB(x,y,col.getRGB());
  }
  /**
   * Sets all the pixels in the given region to the given color.
   * WARNING : END COORDINATES EXCLUDED.
   * WARNING : NO CHECK IS DONE. IF YOU WRITE OUTSIDE THE IMAGE IT WILL RAISE AN ERROR
   */
  public void setRectangle(int startX, int endX, int startY, int endY, Color color) {
    for(int x = startX; x < endX; x++) {
      for(int y = startY; y < endY; y++) {
        setPixel(x,y,color);
      }
    }
  }

  /**
   * Saves the image to a file, in PNG format
   */
  public void save(String filename) throws IOException {
    File fic = new File(filename);
    fic = new File(fic.getAbsolutePath());
    ImageIO.write(image,"png",fic);
  }

  /**
   * Number of pixels in X dimension
   */
  public int width() {
    return image.getWidth();
  }

  /**
   * Number of pixels in Y dimension
   */
  public int height() {
    return image.getHeight();
  }
  
  
  public void toImage(Node r, Tree a) {
    
    if(r.getLeft() == null && r.getRight() == null){
      setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()),r.getCol());
      /////////////////////////GRAY///////////////////////////////
      //Upper side
      setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)(r.getY()),(int)(r.getY()+(a.getlargeurLigne()/2)), Color.GRAY);
      //Left side
      setRectangle((int)r.getX(),(int)(r.getX()+((a.getlargeurLigne()/2))), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
      //Right side
      setRectangle((int)((r.getX()+r.getW())-(a.getlargeurLigne()/2)),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
      //Lower side
      setRectangle((int)((r.getX())),(int)(r.getX()+r.getW()), (int)((r.getY()+r.getH())-(a.getlargeurLigne()/2)),(int)(r.getY()+r.getH()),Color.GRAY);
      
    }else{
      toImage(r.getLeft(), a);
      toImage(r.getRight(),a);
    }
    
  }

  public void toImageB(Node r, BetterTree a) {
    
    if(r.getLeft() == null && r.getRight() == null){
      setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()),r.getCol());
      /////////////////////////GRAY///////////////////////////////
      //Upper side
      setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)(r.getY()),(int)(r.getY()+(a.getlargeurLigne()/2)), Color.GRAY);
      //Left side
      setRectangle((int)r.getX(),(int)(r.getX()+((a.getlargeurLigne()/2))), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
      //Right side
      setRectangle((int)((r.getX()+r.getW())-(a.getlargeurLigne()/2)),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
      //Lower side
      setRectangle((int)((r.getX())),(int)(r.getX()+r.getW()), (int)((r.getY()+r.getH())-(a.getlargeurLigne()/2)),(int)(r.getY()+r.getH()),Color.GRAY);
      
    }else{
      toImageB(r.getLeft(), a);
      toImageB(r.getRight(),a);
    }
    
  }
  
  @SuppressWarnings("resource")
public static void main(String[] args) throws IOException {
	    /*
	     * Image img = new Image(100,200);
	     * img.setRectangle(0, 10, 0, 20, Color.RED);
	     * img.save("test1.png");
	     */

      System.out.println("Veuillez choisir une largeur et hauteur");
      Scanner Sw = new Scanner(System.in);
      Scanner Sh = new Scanner(System.in);
	    Node R = new Node(Sw.nextInt(), Sh.nextInt());
      Image img = new Image((int) R.getW(), (int)R.getH());
      
	    System.out.println("Voulez vous utilisez la strategie par default ? (oui/non)");
	    Scanner useSeed = new Scanner(System.in);
	    String b = useSeed.nextLine();

	    switch(b) {
	    case "oui":
        System.out.println("Voulez donnez dans l'ordre :(nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne) ");
        Scanner nbf = new Scanner(System.in);
        int nb= nbf.nextInt();
        Scanner pc = new Scanner(System.in);
        double dpc = pc.nextDouble();
        Scanner mdc = new Scanner(System.in);
        double dmdc = mdc.nextDouble();
        Scanner mdb = new Scanner(System.in);
        double dmdb = mdb.nextDouble();
        Scanner ll = new Scanner(System.in);
        double dll = ll.nextDouble();
          System.out.println("Donnez un seed");
          Scanner iseed = new Scanner(System.in);
          strategy st = new strategy(R, nb,dpc,dmdc,dmdb, dll, new Random(iseed.nextInt()));
          st.generate();
          img.toImage(st.getTree().getRoot(), st.getTree());
          img.save("Mondarian.png");
         System.out.println("Nb leaf : "+st.getTree().nbOfLeaves(st.getTree().getRoot()));
	 	    break;
	    case "non":
        System.out.println("Voulez donnez dans l'ordre :(nbFeuille, memeCouleurProb) ");
        Scanner nbfb = new Scanner(System.in);
        int nbb= nbfb.nextInt();
        Scanner mcb = new Scanner(System.in);
        double dmcb = mcb.nextDouble();
        System.out.println("Donnez un seed");
        Scanner iseedb = new Scanner(System.in);
        strategy stb = new strategy(R, nbb,dmcb, new Random(iseedb.nextInt()));
        stb.generateBetter();
          img.toImageB(stb.getBetterTree().getRoot(), stb.getBetterTree());
          img.save("Mondarian.png");
         System.out.println("Nb leaf : "+stb.getBetterTree().nbOfLeaves(stb.getBetterTree().getRoot()));
		    break;
	    }
}
}
