
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
            for (int i = 0; i < 10; i++){
            	for (int j = 0; j < 10; j++){
                    //System.out.print(bits[i][j] + " ");
                    if (bits[i][j]){                        
                    	g.fillRect(380 + 28*i, 50 + 28*j, 28, 28);
                    }
                }
                //System.out.println();
            }
	
    }
}