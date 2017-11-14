
package Foreground;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private JButton clearBtn;
    private JButton btnRecognize;
    static JComboBox selectNumber;
    private JLabel textNumber;
    private JLabel outputNumber;
    private JButton btnChecknumber;
    private JToggleButton training;
    private JToggleButton bgm;
    private JLabel background;
    private JLabel Numis;

    ActionListener actionListener = new ActionListener() {        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn)
                drawPanel.clear();
            
            if (e.getSource() == btnChecknumber){
                Numis.setVisible(true);
                if (r.recognize()=="0")
                    Numis.setIcon(new ImageIcon("gui/number/0.png"));
                else if (r.recognize()=="1")
                    Numis.setIcon(new ImageIcon("gui/number/1.png"));
                else if (r.recognize()=="2")
                    Numis.setIcon(new ImageIcon("gui/number/2.png"));
                else if (r.recognize()=="3")
                    Numis.setIcon(new ImageIcon("gui/number/3.png"));
                else if (r.recognize()=="4")
                    Numis.setIcon(new ImageIcon("gui/number/4.png"));
                else if (r.recognize()=="5")
                    Numis.setIcon(new ImageIcon("gui/number/5.png"));
                else if (r.recognize()=="6")
                    Numis.setIcon(new ImageIcon("gui/number/6.png"));
                else if (r.recognize()=="7")
                    Numis.setIcon(new ImageIcon("gui/number/7.png"));
                else if (r.recognize()=="8")
                    Numis.setIcon(new ImageIcon("gui/number/8.png"));
                else if (r.recognize()=="9")
                    Numis.setIcon(new ImageIcon("gui/number/9.png"));
                
            }        
            if (e.getSource() == btnRecognize){
                dispose();
                //System.out.println(selectNumber.getSelectedIndex());
                r.loadImage();
                r.setVisible(true);
            }
            if (e.getSource() == btnChecknumber){
                    
                try {
                    //drawPanel.setVisible(false);

                    //i.setVisible(false);
                    r.loadImage();
                    //image.paint();
                    //getContentPane().remove(i);
                    //i.repaint();
                    //repaint();
                    //getContentPane().add(i);
                    outputNumber.setText(r.recognize());
                } catch (FileNotFoundException ex) {
                    System.out.println("Not found file");
                }
            }
            if (e.getSource() == training){
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
            if (e.getSource() == bgm){
                if (bgm.isSelected()) { 
                    bgm.setIcon(new ImageIcon("gui/button/bgmon.png"));
                    
                } 
                else {
                    bgm.setIcon(new ImageIcon("gui/button/bgmoff.png"));

                }
            }
//            System.out.println(selectNumber.getSelectedIndex());
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
        
//        selectNumber = new JComboBox();
//            selectNumber.setBounds(390,300,60,30);
//            selectNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
//            selectNumber.setVisible(false);

//        textNumber = new JLabel();
//            textNumber.setBounds(320, 290, 80, 50);
//            textNumber.setFont(new java.awt.Font("Tahoma", 0, 18));
//            textNumber.setText("Number is");
        outputNumber = new JLabel();
            outputNumber.setBounds(600, 430, 80, 100);
            outputNumber.setFont(new java.awt.Font("Verdana", Font.PLAIN, 70));
            outputNumber.setText("");

        background = new JLabel();
            background.setSize(800,550);
            background.setIcon(new ImageIcon("gui/BG/back1.png"));
            background.setVisible(true);
        
//        training = new JToggleButton("Training");
//            training.setText("Guess");
//            training.setBounds(10,300,280, 30);
//            training.setFocusPainted(false);

        Numis = new JLabel();
        Numis.setBounds(580,465,60,30);
        Numis.setVisible(false);
        
//<<<<<<< HEAD
//        btnRecognize = new JButton("Check number");
//            btnRecognize.setBounds(10, 340, 135, 30);
//            btnRecognize.setFocusPainted(false);
//        clearBtn = new JButton("Clear");
//            clearBtn.setBounds(155, 340, 135, 30);
//            clearBtn.setFocusPainted(false);
//=======
        selectNumber = new JComboBox();
        selectNumber.setBounds(535,470,80,30);
        selectNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        selectNumber.setVisible(false);
        
        training = new JToggleButton();
        training.setIcon(new ImageIcon("gui/button/Training.png"));
        training.setBounds(710,38,68, 65);
        training.setFocusPainted(false);
        
        bgm = new JToggleButton();
        bgm.setIcon(new ImageIcon("gui/button/bgmoff.png"));
        bgm.setBounds(710,150,68, 65);
        bgm.setFocusPainted(false);
        
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
//>>>>>>> JJ
        
        
        clearBtn.addActionListener(actionListener);
        btnRecognize.addActionListener(actionListener);
        btnChecknumber.addActionListener(actionListener);
        training.addActionListener(actionListener);
        selectNumber.addActionListener(actionListener);
        bgm.addActionListener(actionListener);
        
        getContentPane().add(btnChecknumber);        
        getContentPane().add(drawPanel);
        getContentPane().add(clearBtn);
        getContentPane().add(btnRecognize);
        getContentPane().add(Numis);
        getContentPane().add(selectNumber);
//<<<<<<< HEAD
//        getContentPane().add(textNumber);
        getContentPane().add(outputNumber);
        getContentPane().add(training);
        getContentPane().add(drawPanel.drawRealtime);
        getContentPane().add(bgm);
        getContentPane().add(background);
        setVisible(true);
    }
    
    public static int getSelectNumber(){
        //System.out.println(selectNumber.getSelectedItem());
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


