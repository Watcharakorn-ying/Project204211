
package Foreground;

import Background.NeuralNetwork;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HawksSalatan
 */
public class TrainPanel extends JComponent{    
    private JButton btnSave;
    private JButton btnTrain;
    private JTextField txtIterLimit;
    private JTextField txtLearningRate;
    private JTextField txtHLNeurons;
    private JLabel lblError;
    private JLabel lblIters;
    private JLabel lblSuccess;
    private JLabel lblMSE;
    private String hintLimit = "Only number > 0";
    private String hintLearningRate = "Only decimal [0-1]";
    
    private NeuralNetwork nn;
    private int N;
    
    FocusListener hint = new FocusListener(){
        public void focusGained(FocusEvent fe) {
            if (fe.getSource() == txtIterLimit){
                txtIterLimit.addKeyListener(new KeyListener(){
                    public void keyTyped(KeyEvent ke) {
                        if (ke.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                            if (txtIterLimit.getText().equals(hintLimit)){
                                txtIterLimit.setText("");
                                txtIterLimit.setForeground(Color.BLACK);
                            }
                        }
                        else{
                            if (txtIterLimit.getText().isEmpty()) {
                                txtIterLimit.setForeground(Color.GRAY);
                                txtIterLimit.setText(hintLimit);
                                txtIterLimit.setCaretPosition(0);
                            }
                        }
                    }
                    public void keyPressed(KeyEvent ke) {}
                    public void keyReleased(KeyEvent ke) {}
                });
            }
            else if (fe.getSource() == txtLearningRate){
                txtLearningRate.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent ke) {
                        if (ke.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                            if (txtLearningRate.getText().equals(hintLearningRate)){
                                txtLearningRate.setText("");
                                txtLearningRate.setForeground(Color.BLACK);
                            }
                        }
                        else{
                            if (txtLearningRate.getText().isEmpty()) {
                                txtLearningRate.setForeground(Color.GRAY);
                                txtLearningRate.setText(hintLearningRate);
                                txtLearningRate.setCaretPosition(0);
                            }
                        }
                    }
                    public void keyPressed(KeyEvent ke) {}
                    public void keyReleased(KeyEvent ke) {}
                });
            }
            else if (fe.getSource() == txtHLNeurons){
                txtHLNeurons.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent ke) {
                        if (ke.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                            if (txtHLNeurons.getText().equals(hintLimit)){
                                txtHLNeurons.setText("");
                                txtHLNeurons.setForeground(Color.BLACK);
                            }
                        }
                        else{
                            if (txtHLNeurons.getText().isEmpty()) {
                                txtHLNeurons.setForeground(Color.GRAY);
                                txtHLNeurons.setText(hintLimit);
                                txtHLNeurons.setCaretPosition(0);
                            }
                        }
                    }
                    public void keyPressed(KeyEvent ke) {}
                    public void keyReleased(KeyEvent ke) {}
                });
            }
        }
        
        public void focusLost(FocusEvent fe) {}
    };
    
    
    public TrainPanel(){
        setPreferredSize(new Dimension(710, 600));
        setBounds(0, 0, 710, 600);
        
        btnSave = new JButton("Save Pattern");
        btnSave.setBounds(440, 500, 110, 25);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setFocusPainted(false);
        btnTrain = new JButton("Train");
        btnTrain.setBounds(125, 450, 100, 30);
        btnTrain.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTrain.setFocusPainted(false);
        btnTrain.requestFocusInWindow();
        
        createSide();
        
        add(btnSave);
        add(btnTrain);
    }
    
    private void createSide(){
        //Bottom left side
        JLabel lblIterLimit = new JLabel("Iterations Limit:");
            lblIterLimit.setBounds(50, 350, 100, 20);
        JLabel lblLearningRate = new JLabel("Learning Rate:");
            lblLearningRate.setBounds(50, 380, 100, 20);
        JLabel lblHLNeurons = new JLabel("Hidden Layer Neurons");
            lblHLNeurons.setBounds(50, 410, 150, 20);
        //Bottom Right side
        JLabel lblItersTxt = new JLabel("Iterations performed:");
            lblItersTxt.setBounds(380, 350, 150, 20);
	JLabel lblSuccessTxt = new JLabel("Success rate:");
            lblSuccessTxt.setBounds(380, 380, 100, 20);
	JLabel lblMSETxt = new JLabel("Mean Squared Error:");
            lblMSETxt.setBounds(380, 410, 150, 20);
        //Screen result train
        lblIters = new JLabel("");
            lblIters.setBounds(520, 350, 100, 20);
            lblIters.setForeground(Color.BLUE);
	lblSuccess = new JLabel("");
            lblSuccess.setBounds(520, 380, 100, 20);
            lblSuccess.setForeground(Color.BLUE);
	lblMSE = new JLabel("");
            lblMSE.setBounds(520, 410, 100, 20);
            lblMSE.setForeground(Color.BLUE);
        
        txtIterLimit = new JTextField(hintLimit);
            txtIterLimit.setForeground(Color.GRAY);
            txtIterLimit.setBounds(200, 350, 120, 20);
            txtIterLimit.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            //txtIterLimit.addKeyListener(KeyListener);
            txtIterLimit.addFocusListener(hint);
        txtLearningRate = new JTextField(hintLearningRate);
            txtLearningRate.setForeground(Color.GRAY);
            txtLearningRate.setBounds(200, 380, 120, 20);
            txtLearningRate.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            //txtLearningRate.addKeyListener(KeyListener);
            txtLearningRate.addFocusListener(hint);
        txtHLNeurons = new JTextField(hintLimit);
            txtHLNeurons.setForeground(Color.GRAY);
            txtHLNeurons.setBounds(200, 415, 120, 20);
            txtHLNeurons.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            //txtHLNeurons.addKeyListener(KeyListener);
            txtHLNeurons.addFocusListener(hint);
        
        add(lblIterLimit);
        add(lblLearningRate);
        add(lblHLNeurons);
        add(txtIterLimit);
        add(txtLearningRate);
        add(txtHLNeurons);
        add(lblItersTxt);
        add(lblSuccessTxt);
        add(lblMSETxt);
        add(lblIters);
	add(lblSuccess);
	add(lblMSE);
    }
    
}
