
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

/**
 *
 * @author HawksSalatan
 */

class DrawWin extends JFrame {
    static Draw drawPanel;
    
    static Recognition r = new Recognition();
    static JComboBox selectNumber;
    private JButton clearBtn;
    private JButton btnRecognize;
    private JButton btnChecknumber;
    private JToggleButton training;
    private JToggleButton bgMusic;
    private JLabel background;
    private JLabel Numis;

    ActionListener actionListener = new ActionListener() {        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn)
                drawPanel.clear();
            
            if (e.getSource() == btnRecognize){
                dispose();
                r.loadImage();
                r.setVisible(true);
            }
            if (e.getSource() == btnChecknumber){
                    
                try {
                    r.loadImage();
                    Numis.setVisible(true);
                    if (r.recognize().equals("0"))
                        Numis.setIcon(new ImageIcon("gui/number/0.png"));
                    else if (r.recognize().equals("1"))
                        Numis.setIcon(new ImageIcon("gui/number/1.png"));
                    else if (r.recognize().equals("2"))
                        Numis.setIcon(new ImageIcon("gui/number/2.png"));
                    else if (r.recognize().equals("3"))
                        Numis.setIcon(new ImageIcon("gui/number/3.png"));
                    else if (r.recognize().equals("4"))
                        Numis.setIcon(new ImageIcon("gui/number/4.png"));
                    else if (r.recognize().equals("5"))
                        Numis.setIcon(new ImageIcon("gui/number/5.png"));
                    else if (r.recognize().equals("6"))
                        Numis.setIcon(new ImageIcon("gui/number/6.png"));
                    else if (r.recognize().equals("7"))
                        Numis.setIcon(new ImageIcon("gui/number/7.png"));
                    else if (r.recognize().equals("8"))
                        Numis.setIcon(new ImageIcon("gui/number/8.png"));
                    else if (r.recognize().equals("9"))
                        Numis.setIcon(new ImageIcon("gui/number/9.png"));
                } catch (FileNotFoundException ex) {
                    System.out.println("Not found file");
                }
            }
            if (e.getSource() == training){
                drawPanel.clear();
                if (training.isSelected()) { 
                    btnChecknumber.setVisible(false);
                    btnRecognize.setVisible(true);
                    training.setIcon(new ImageIcon("gui/button/clickTraining.png"));
                    background.setIcon(new ImageIcon("gui/BG/back2.png"));
                    Numis.setVisible(false);
                    selectNumber.setVisible(true);                
                } 
                else {
                    btnRecognize.setVisible(false);
                    btnChecknumber.setVisible(true);
                    training.setIcon(new ImageIcon("gui/button/Training.png"));
                    background.setIcon(new ImageIcon("gui/BG/back1.png"));
                    selectNumber.setVisible(false);
                }
            }
            if (e.getSource() == bgMusic){
                if (bgMusic.isSelected()) { 
                    bgMusic.setIcon(new ImageIcon("gui/button/bgmon.png"));
                    
                } 
                else {
                    bgMusic.setIcon(new ImageIcon("gui/button/bgmoff.png"));

                }
            }
        }
        
    };    

    public DrawWin() {
        setTitle("Test");
        setSize(new Dimension(800, 570));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        drawPanel = new Draw();
        drawPanel.drawRealtime = new DrawRealtime();
        
        selectNumber = new JComboBox();
            selectNumber.setBounds(535,470,80,30);
            selectNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
            selectNumber.setVisible(false);
        
        background = new JLabel();
            background.setSize(800,550);
            background.setIcon(new ImageIcon("gui/BG/back1.png"));
            background.setVisible(true);
        Numis = new JLabel();
            Numis.setBounds(580,465,60,30);
            Numis.setVisible(false);
            
        training = new JToggleButton();
            training.setIcon(new ImageIcon("gui/button/Training.png"));
            training.setBounds(710,38,68, 65);
            training.setFocusPainted(false);
        bgMusic = new JToggleButton();
            bgMusic.setIcon(new ImageIcon("gui/button/bgmoff.png"));
            bgMusic.setBounds(710,150,68, 65);
            bgMusic.setFocusPainted(false);
        
        btnChecknumber = new JButton();
            btnChecknumber.setIcon(new ImageIcon("gui/button/checknumber.png"));
            btnChecknumber.setHorizontalTextPosition(AbstractButton.CENTER);
            btnChecknumber.setBounds(20, 437, 190, 90);
            btnChecknumber.setFocusPainted(false);
            btnChecknumber.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnChecknumber.setIcon(new ImageIcon("gui/button/clickchecknumber.png"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnChecknumber.setIcon(new ImageIcon("gui/button/checknumber.png"));
                }
            });
        btnRecognize = new JButton();
            btnRecognize.setIcon(new ImageIcon("gui/button/recognize.png"));
            btnRecognize.setHorizontalTextPosition(AbstractButton.CENTER);
            btnRecognize.setBounds(20, 437, 190, 90);
            btnRecognize.setFocusPainted(false);
            btnRecognize.setVisible(false);
            btnRecognize.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnRecognize.setIcon(new ImageIcon("gui/button/clickrecognize.png"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnRecognize.setIcon(new ImageIcon("gui/button/recognize.png"));
                }
            });
            
        clearBtn = new JButton();
            clearBtn.setIcon(new ImageIcon("gui/button/testbut.png"));
            clearBtn.setBounds(240, 440, 115, 40);
            clearBtn.setHorizontalTextPosition(AbstractButton.CENTER);
            clearBtn.setFocusPainted(false);
            clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    clearBtn.setIcon(new ImageIcon("gui/button/clicktestbut.png"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    clearBtn.setIcon(new ImageIcon("gui/button/testbut.png"));
                }
            });     
        
        btnChecknumber.addActionListener(actionListener);
        clearBtn.addActionListener(actionListener);
        btnRecognize.addActionListener(actionListener);
        training.addActionListener(actionListener);
        selectNumber.addActionListener(actionListener);
        bgMusic.addActionListener(actionListener);
        
        getContentPane().add(drawPanel);
        getContentPane().add(btnChecknumber);
        getContentPane().add(clearBtn);
        getContentPane().add(btnRecognize);
        getContentPane().add(selectNumber);
        getContentPane().add(Numis);
        getContentPane().add(training);
        getContentPane().add(drawPanel.drawRealtime);
        getContentPane().add(bgMusic);
        getContentPane().add(background);
        setVisible(true);
    }
    
    public static int getSelectNumber(){
        return selectNumber.getSelectedIndex();
    }
}

public class Draw extends JPanel implements MouseMotionListener, MouseListener {
    private boolean p;
    private boolean painting;
    private int px, py;
    private boolean[][] data;
    static DrawRealtime drawRealtime;
    
    
    public Draw() {
	setPreferredSize(new Dimension(280, 280));
	setBounds(40, 103, 280, 280);
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
        drawRealtime.clear();
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
            DrawWin.r.loadImage();
            graphics.drawLine(px,py,x,y);
            drawRealtime.paint(px, py, x, y);
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


