/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawarea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
 
import javax.swing.JComponent;
/**
 *
 * @author Lenovo-PC
 */
public class DrawArea extends JComponent {
// create 2D array for keep number
  public int[][] xy = new int[100][100];
// Image in which we're going to draw
  private Image image;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, startX, startY, maximumX=0,minimumX=900, maximumY=0, minimumY=900;
 
  public DrawArea() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        // save coord x,y when mouse is pressed
        startX = e.getX();
        startY = e.getY();
        System.out.println("Press Mouse X:"+startX);
        System.out.println("Press Mouse Y:"+startY);
      }
    });
 
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        // coord x,y when drag mouse
        int  miny=0, maxy=9, row=0;
        currentX = e.getX();
        currentY = e.getY();
        if (g2 != null) {
          System.out.println("Drag X: "+currentX);
          System.out.println("Drag Y: "+currentY);
          System.out.println("----------------------------------");
          // draw line if g2 context not null
          g2.drawLine(startX, startY, currentX, currentY);
          // refresh draw area to repaint
          repaint();
          // store current coords x,y as olds x,y
          
          while (maxy <= 900){
              int col=0,minx=0,maxx=9;
              while(maxx<=900){
                  if((minx <= currentX && currentX <= maxx) && (miny <= currentY && currentY <= maxy)){
                      if (currentX >= maximumX){maximumX=currentX;}
                      if (currentY >= maximumY){maximumY=currentY;}
                      if (currentX <= minimumX){minimumX=currentX;}
                      if (currentY <= minimumY){minimumY=currentX;}
                      xy[row][col] = 1;
                      break;
                  }
                  maxx += 9;
                  minx += 9;
                  col++;
              }
            miny += 9;
            maxy += 9;
            row++;
          }
          startX = currentX;
          startY = currentY;
        }
      }
    });
  }
 
  protected void paintComponent(Graphics g) {
    if (image == null) {
      // image to draw null ==> we create
      image = createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // clear draw area
      clear();
    }
 
    g.drawImage(image, 0, 0, null);
  }
  public void cal() {
    int  miny=0, maxy=(maximumY-minimumY)/10, row=0;
    System.out.println("Maximum X:"+maximumX);
    System.out.println("Maximum Y:"+maximumY);
    System.out.println("Minimum X:"+minimumX);
    System.out.println("Minimum Y:"+minimumY);
    /*while (maxy <= maximumY){
              int col=0,minx=0,maxx=(maximumX-minimumX)/10;
              while(maxx<=maximumX){
                  if(){
                      xy[row][col] = 1;
                      break;
                  }
                  maxx += (maximumX-minimumX)/10;
                  minx += (maximumX-minimumX)/10;
                  col++;
              }
            miny += (maximumY-minimumY)/10;
            maxy += (maximumY-minimumY)/10;
            row++;
          }*/
    for(int i=0;i<100;i++){
        for(int j=0;j<100;j++){
            System.out.print(xy[i][j]+" ");
        }
        System.out.println();
    }
//    g2.setPaint(Color.white);
//    // draw white on entire draw area to clear
//    g2.fillRect(0, 0, getSize().width, getSize().height);
//    g2.setPaint(Color.black);
//    repaint();
  }
 
  // now we create exposed methods
  public void clear() {
    g2.setPaint(Color.white);
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    for(int i=0;i<100;i++){
        for(int j=0;j<100;j++){
            xy[i][j] = 0;
        }}
    repaint();
  }
 
 
  
 
}


