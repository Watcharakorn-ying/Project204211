
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 *
 * @author HawksSalatan
 */
public class ImagePanel extends JPanel {
    public ImagePanel(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(0, 0, 710, 430);
    }
    public ImagePanel(int x, int y) {
	setPreferredSize(new Dimension(710, 430));
	setBounds(x, y, 710, 430);
    }
    public void paint(Graphics g) {
	// Paint the image
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
    //half real time
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
