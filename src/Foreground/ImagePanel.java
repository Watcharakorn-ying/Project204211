
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;

import javax.swing.JPanel;

/**
 *
 * @author HawksSalatan
 */
public class ImagePanel extends JPanel {
    public ImagePanel(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(0, 0, 710, 350);
    }
    /*public ImagePanel(int x, int y) {
	setPreferredSize(new Dimension(710, 430));
	setBounds(x, y, 710, 430);
    }*/
    public void paint(Graphics g) {
	// Paint the image
	BufferedImage image = DrawWin.r.getImage();
	int[] rCoords = DrawWin.r.getRectangleCoords();
	for (int i = 0; i < rCoords.length; i++) rCoords[i] += 50;
	boolean[][] bits = DrawWin.r.getImageBits();
	if (image != null)
            g.drawImage(image, 50, 50, this);
	
	// Paint the image container
	if (rCoords != null) {
            g.setColor(Color.RED);
            g.drawRect(rCoords[0] - 1, rCoords[1] - 1,
		rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);	
	}
        // Paint binarized image grid
        g.setColor(Color.BLACK);
        g.fillRect(380, 50, 280, 280);
        g.setColor(Color.WHITE);
        for (int i = 408; i < 660; i+= 28) {
            g.drawLine(i, 50, i, 330);
            g.drawLine(380, i - 330, 660, i - 330);
        }
        
        // Paint the binarized image's bits
	if (bits != null)
            for (int i = 0; i < 10; i++)
            	for (int j = 0; j < 10; j++)
                    if (bits[i][j])
                    	g.fillRect(380 + 28*i, 50 + 28*j, 28, 28);
	
    }
}
    
/*    
    public void paint() {
	// Paint the image
        Graphics g = getGraphics();
	BufferedImage image = DrawWin.r.getImage();
	int[] rCoords = DrawWin.r.getRectangleCoords();
	for (int i = 0; i < rCoords.length; i++) rCoords[i] += 10;
	boolean[][] bits = DrawWin.r.getImageBits();
	if (image != null)
            g.drawImage(image, 10, 10, this);
	
	// Paint the image container
	if (rCoords != null) {
            g.setColor(Color.RED);
            g.drawRect(rCoords[0] - 1, rCoords[1] - 1,
		rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);	
	}}    
}
class RepeatDraw extends JPanel{
    private Graphics g;
    private Image image;
    
    public RepeatDraw(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(300, 10, 280, 280);
        setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));        
    }
    
    public void repeat(int px, int py, int x, int y){        
        //g = getGraphics();
        if (g != null){
            g.drawLine(px, py, x, y);
            repaint();
        }
    }
    
    public void paint(Graphics g2){
        if (image == null){
            image = createImage(getSize().width, getSize().height);
            g = image.getGraphics();
            clear();
        }
        g2.drawImage(image, 0, 0, null);
    }
    
    public void clear() {        
	getGraphics().clearRect(0, 10, 280, 280);
	setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
class CropDraw extends JPanel{
    private Graphics c;    
    private int[] prCoords;
    private Image image;
    
    public CropDraw(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(300, 10, 280, 280);
        setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
        prCoords = new int[4];
    }
    
    public void crop() {
        DrawWin.r.loadImage();
        //c = getGraphics();
        int[] rCoords = DrawWin.r.getRectangleCoords();
        if (rCoords != null) {
            c.clearRect(prCoords[0], prCoords[1], prCoords[2], prCoords[3]);
            c.setColor(Color.RED);
            c.drawRect(rCoords[0] - 1, rCoords[1] - 1,
		rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);
	}
        prCoords = rCoords;
        repaint();
        
    }
    
    public void paint(Graphics g3){
        if (image == null){
            image = createImage(getSize().width, getSize().height);
            c = image.getGraphics();
            clear();
        }
        g3.drawImage(image, 0, 0, null);        
    }
    
    public void clear() {        
	getGraphics().clearRect(0, 10, 280, 280);
	setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
}
*/