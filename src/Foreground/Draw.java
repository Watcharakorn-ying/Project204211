
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author HawksSalatan
 */
class DrawWin extends JFrame {
    public static Draw drawPanel;
    ImagePanel image;
    
    static Recognition r = new Recognition();
    private JButton clearBtn;
    private JButton btnRecognize;
    private JComboBox selectNumber;
    private JLabel textNumber;
    private JToggleButton training;
    
    ActionListener actionListener = new ActionListener() {        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn)
                drawPanel.clear();
            if (e.getSource() == btnRecognize){
                if (btnRecognize.getText().equals("Recognize")){
                    dispose();
                    r.loadImage();
                    r.setVisible(true);
                }
                else if (btnRecognize.getText().equals("Check number")){
                    
                    //drawPanel.setVisible(false);
                    
                    //i.setVisible(false);
                    r.loadImage();
                    image.paint();
                    //getContentPane().remove(i);                                      
                    //i.repaint();
                    //repaint();
                    //getContentPane().add(i);
                    
                }
            }
            if (e.getSource() == training){
                if (training.isSelected()) { 
                    training.setText("Training");
                    btnRecognize.setText("Recognize");
                    textNumber.setText("Number");
                    selectNumber.setVisible(true);                
                } 
                else { 
                    training.setText("Guess");
                    btnRecognize.setText("Check number");
                    selectNumber.setVisible(false);
                    textNumber.setText("Number is");
                }
            }
            //System.out.println(selectnumber.getSelectedItem());
        }

    };    
    
    public DrawWin() {
        setTitle("Test");
        setSize(new Dimension(1000, 410));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        drawPanel = new Draw();
        image = new ImagePanel(300, 0);
        r.loadImage();
        
        selectNumber = new JComboBox();
        selectNumber.setBounds(390,300,60,30);
        selectNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        selectNumber.setVisible(false);

        textNumber = new JLabel();
        textNumber.setBounds(320, 290, 80, 50);
        textNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textNumber.setText("Number is");
        
        training = new JToggleButton("Training");
        training.setText("Guess");
        training.setBounds(10,300,280, 30);
        training.setFocusPainted(false);
        
        btnRecognize = new JButton("Check number");
        btnRecognize.setBounds(10, 340, 135, 30);
        btnRecognize.setFocusPainted(false);
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(155, 340, 135, 30);
        clearBtn.setFocusPainted(false);
        
        clearBtn.addActionListener(actionListener);
        btnRecognize.addActionListener(actionListener);
        training.addActionListener(actionListener);
        selectNumber.addActionListener(actionListener);
        
        getContentPane().add(drawPanel);
        getContentPane().add(clearBtn);
        getContentPane().add(btnRecognize);
        getContentPane().add(selectNumber);
        getContentPane().add(textNumber);
        getContentPane().add(training);
        getContentPane().add(image);
        setVisible(true);
    }
}

public class Draw extends JPanel implements MouseMotionListener, MouseListener {
    private boolean p;
    private boolean painting;
    private int px, py;
    private boolean[][] data;
    
    public Draw() {
	setPreferredSize(new Dimension(280, 280));
	setBounds(10, 10, 280, 280);
	setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
	p = false;
	painting = false;
	px = 0; py = 0;
	data = new boolean[280][280];
	addMouseListener(this);
	addMouseMotionListener(this);
    }
    
    public void clear() {
        data = new boolean[280][280];
	getGraphics().clearRect(0, 0, 280, 280);
	setBackground(Color.WHITE);
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public boolean[][] getData(){
        return data;
    }
    
    public void mousePressed(MouseEvent e) {
        p = true;
	painting = true;
    }
    
    public void mouseDragged(MouseEvent e) {
	int x = e.getX(), y = e.getY();
	Graphics graphics = getGraphics();
	graphics.setColor(Color.BLACK);
	if (painting && p) {
		graphics.drawLine(x, y, x, y);
                System.out.println("T T " + x + " " + y);
		p = false;
	} else if (painting) {
		graphics.drawLine(px,py,x,y);
                System.out.println("T F " + px + " " + py + " " + x + " " + y);
	}        
	px = x;
	py = y;
	if (painting) data[x][y] = true;
    }
    
    public void mouseExited(MouseEvent e) {
	painting = false;
    }
	
    public void mouseEntered(MouseEvent e) {
	painting = true;
    }
    
    public void mouseMoved(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    
    public static void main(String [] args){
        DrawWin d = new DrawWin();
    }
}
