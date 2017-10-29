
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
    Draw drawPanel;
    private JButton clearBtn;
    private JButton btnRecognize;
    private JComboBox selectnumber;
    private JLabel textnumber;
    private JToggleButton training;
    
    ActionListener actionListener = new ActionListener() {        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn)
                drawPanel.clear();
            if (e.getSource() == btnRecognize)
                ;
            if (training.isSelected()) { 
                training.setText("Training");
                btnRecognize.setText("Recognize");
            } 
            else { 
                training.setText("Guess");
                btnRecognize.setText("Check number");
            } 
        }

    };    
    
    public DrawWin() {
        setTitle("Test");
        setSize(new Dimension(600, 410));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);        
        
        drawPanel = new Draw();
        
        selectnumber = new JComboBox();
        selectnumber.setBounds(390,300,60,30);
        selectnumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        textnumber = new JLabel();
        textnumber.setBounds(320, 290, 70, 50);
        textnumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textnumber.setText("Number");
        
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
        
        getContentPane().add(drawPanel);
        getContentPane().add(clearBtn);
        getContentPane().add(btnRecognize);
        getContentPane().add(selectnumber);
        getContentPane().add(textnumber);
        getContentPane().add(training);
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
