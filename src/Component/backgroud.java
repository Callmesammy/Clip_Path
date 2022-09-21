
package Component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
    private Point point = new Point(-size/2, -size/2);
    private Shape shape = new Ellipse2D.Double(0, 0, size, size);
    private String title = "Enter Text here";
    
    public backgroud() {
        setOpaque(true);
        setBackground(new Color(70,70,70));
        setForeground(new Color(200,200,200));
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
              int x = getX()-size/2;
              int y = getY()-size/2;
              point = new Point(x, y);
              repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new Rectangle.Double(0, 0, getWidth(), getHeight()));
        if (image != null) {
            drawImage(g2);
        }
        
        g2.dispose();
        super.paintComponent(g); 
    }
    
    private void drawImage (Graphics2D g2){
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = img.createGraphics();
        Rectangle tan = getAutosize(image);
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform tran =g1.getTransform();
        g1.translate(point.x, point.y);
        g1.fill(shape);
        g1.setComposite(AlphaComposite.SrcIn);
        g1.setTransform(tran);
        
        g1.drawImage(toIcon(image), tan.x, tan.y, tan.width, tan.height, null);
        g1.dispose();
        g2.drawImage(img, 0, 0, null);
        
        
        
        
    }
    
    private Rectangle getAutosize (Icon image){
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
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
