
package Foreground;

import Background.NeuralNetwork;
import java.awt.Cursor;
import java.awt.Dimension;
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
    
    private NeuralNetwork nn;
    private int N;
    
    public TrainPanel(){
        setPreferredSize(new Dimension(710, 600));
        setBounds(0, 0, 710, 600);
        
        btnSave = new JButton("Save Pattern");
        btnSave.setBounds(440, 500, 110, 25);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setFocusPainted(false);
        
        createSide();
        
        add(btnSave);
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
        
        txtIterLimit = new JTextField();
	txtIterLimit.setBounds(200, 350, 100, 20);
	txtIterLimit.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        txtLearningRate = new JTextField();
	txtLearningRate.setBounds(200, 380, 100, 20);
	txtLearningRate.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        txtHLNeurons = new JTextField();
	txtHLNeurons.setBounds(200, 410, 100, 20);
	txtHLNeurons.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        add(lblIterLimit);
        add(lblLearningRate);
        add(lblHLNeurons);
        add(txtIterLimit);
        add(txtLearningRate);
        add(txtHLNeurons);
        add(lblItersTxt);
        add(lblSuccessTxt);
        add(lblMSETxt);
    }
    
    
        
    public static void main(String [] args){
            
    }
    
}
