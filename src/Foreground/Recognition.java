
package Foreground;

import Background.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Recognition extends JFrame {
    private ImagePanel imagePanel = new ImagePanel();
    private TrainPanel TrainPanel = new TrainPanel();
    
    private JButton btnReset;
    private BufferedImage image;
    private int[] rectCoords;
    private boolean[][] bits;
    
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnReset){
                dispose();
                DrawWin d = new DrawWin();
            }
        }
    };
    
    public Recognition(){
        setTitle("Recognition");
        setSize(710, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setLayout(null);
        
        btnReset = new JButton("Reset");
	btnReset.setBounds(565, 500, 100, 25);
	btnReset.setFocusPainted(false);
        
        
        btnReset.addActionListener(actionListener);
        
        addWindowListener(TrainPanel);
        getContentPane().add(btnReset);
        getContentPane().add(imagePanel);
        getContentPane().add(TrainPanel);
    }
    
    public void loadImage() {
	boolean[][] data = Main.drawPanel.getData();
	image = Image.getImage(data);
	rectCoords = Image.getRectangle(data);
	bits = Image.getBits(rectCoords, data);
        System.out.println("pass bits");
    }
    
    public String recognize(){
        boolean[][] booleanBits = Main.recognition.bits;
	int[] intBits = new int[196];
	for (int i = 0; i < 14; i++)
            for (int j = 0; j < 14; j++)
		intBits[14*j + i] = (booleanBits[i][j])? 1 : 0;
        int result = Main.neuralNet.resultIndex(intBits);
        System.out.println(result);
        return Integer.toString(result);
    }
    
    public int[] getRectangleCoords() {
	return rectCoords;
    }
    
    public boolean[][] getImageBits() {
        return bits;
    }
    
    public BufferedImage getImage() {
	return image;
    }
}
