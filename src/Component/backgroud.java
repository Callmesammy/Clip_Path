
package Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;


public class backgroud extends JLayeredPane {

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

 
    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the image
     */
    public Icon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Icon image) {
        this.image = image;
    }

    private int size = 300;
    private Icon image;
    private Point point = new Point(size/2, size/2);
    private Shape shape = new Ellipse2D.Double(0, 0, size, size);
    private String title = "Enter Text here";
    
    public backgroud() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new Rectangle());
        if (image != null) {
            drawImage(g2);
        }
        
        g2.dispose();
        super.paintComponent(g); 
    }
    
    private void drawImage (Graphics2D g2){
        
    }
    
    private Rectangle getAutosize (Icon image){
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w/ih;
        double yScale = (double) h/ih;
        double scale = (double) Math.max(xScale, yScale);
        int width = (int) (scale *iw);
        int height = (int) (scale *ih);
        if (width <1) {
            width =1;
        }
        if (height<1) {
            height =1;
        }
        int x = (w-width)/2;
        int y = (h -height)/2;
        
        return new Rectangle(x,y,width,height);
    }
    
    private Image toIcon(Icon image){
        return ((ImageIcon)image).getImage();
    }
    
}
