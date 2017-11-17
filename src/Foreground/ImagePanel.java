
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    public ImagePanel(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(0, 0, 710, 350);
    }
    public void paint(Graphics g) {
	// Paint the image
	BufferedImage image = Main.recognition.getImage();
	int[] rCoords = Main.recognition.getRectangleCoords();
	for (int i = 0; i < rCoords.length; i++) rCoords[i] += 50;
	boolean[][] bits = Main.recognition.getImageBits();
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
        for (int i = 400; i < 660; i+= 20) {
            g.drawLine(i, 50, i, 330);
            g.drawLine(380, i - 330, 660, i - 330);
        }
        
        // Paint the binarized image's bits
	if (bits != null)
            for (int i = 0; i < 14; i++){
            	for (int j = 0; j < 14; j++){
                    //System.out.print(bits[i][j] + " ");
                    if (bits[i][j]){                        
                    	g.fillRect(380 + 20*i, 50 + 20*j, 20, 20);
                    }
                }
            }
    }
}