
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawRealtime extends JPanel {
    int oldr1=0,oldr2=0,oldr3=0,oldr4=0;
    public  DrawRealtime(){
        setPreferredSize(new Dimension(710, 430));
	setBounds(370, 103, 280, 280);
        setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void paint(int px, int py, int x, int y) {
        Graphics g = getGraphics();
        int[] rCoords = DrawWin.r.getRectangleCoords();
        g.drawLine(px,py,x,y);//draw realtime
        if (rCoords != null) {
            g.setColor(Color.WHITE);
            g.drawRect(oldr1, oldr2,oldr3, oldr4);
            g.setColor(Color.BLACK);
            g.drawLine(px,py,x,y);
            g.setColor(Color.RED);
            g.drawRect(rCoords[0] - 1, rCoords[1] - 1,
            rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);
            oldr1 = rCoords[0] - 1; oldr2 =  rCoords[1] - 1;oldr3 = rCoords[2] - rCoords[0] + 2;oldr4=rCoords[3] - rCoords[1] + 2;
        }
    }
    public void clear() {//clear image real time        
        repaint();
    }
}
