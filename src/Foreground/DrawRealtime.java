
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawRealtime extends JPanel {
    public  DrawRealtime(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(300, 10, 280, 280);
        setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void paint(int px, int py, int x, int y) {
        Graphics g = getGraphics();
        int[] rCoords = DrawWin.r.getRectangleCoords();
        g.drawLine(px,py,x,y);//draw realtime
        if (rCoords != null) {
            g.setColor(Color.RED);
            g.drawRect(rCoords[0] - 1, rCoords[1] - 1,
        rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);
        }
    }
    public void clear() {//clear image real time        
        repaint();
    }
}
