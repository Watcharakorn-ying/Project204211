
package Foreground;

import Background.*;
import Background.NeuralNetwork;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HawksSalatan
 */
public class TrainPanel extends JComponent implements WindowListener{    
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
    
    private FileWriter trainDataFile;
    private NeuralNetwork nn;
    private int[][] trainPatterns;
    private int[][] expectedOutputs;
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
            btnSave.addActionListener(new SaveListener());
        btnTrain = new JButton("Train");
            btnTrain.setBounds(125, 450, 100, 30);
            btnTrain.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnTrain.setFocusPainted(false);
            btnTrain.addActionListener(new TrainListener());
        
        lblError = new JLabel("");
            lblError.setBounds(80, 490, 300, 30);
            lblError.setForeground(Color.RED);
        
        createSide();
        loadWriteFile();
        
        add(btnSave);
        add(btnTrain);
        add(lblError);
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
            txtIterLimit.addFocusListener(hint);
        txtLearningRate = new JTextField(hintLearningRate);
            txtLearningRate.setForeground(Color.GRAY);
            txtLearningRate.setBounds(200, 380, 120, 20);
            txtLearningRate.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            txtLearningRate.addFocusListener(hint);
        txtHLNeurons = new JTextField(hintLimit);
            txtHLNeurons.setForeground(Color.GRAY);
            txtHLNeurons.setBounds(200, 415, 120, 20);
            txtHLNeurons.setCursor(new Cursor(Cursor.TEXT_CURSOR));
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
    
    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                boolean[][] data = DrawWin.drawPanel.getData();
                data = Image.getBits(Image.getRectangle(data), data);
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 10; j++){
                        System.out.print(data[j][i]? "1 ": "0 ");
                            trainDataFile.write((data[j][i])? "1 " : "0 ");
                    }
                    System.out.println();
                }
                System.out.println("W" + DrawWin.getSelectNumber());
                trainDataFile.write(Shared.getBinary(DrawWin.getSelectNumber()) + "\n");
                N++;
                System.out.println("N = " + N);
                //DrawWin.drawPanel.clear();
            } catch (IOException e1) { e1.printStackTrace(); }
        }
    }
    
    private class TrainListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            new Thread(){
                public void run(){
                    btnTrain.setEnabled(false);
                    btnTrain.setText("Training");
                    lblIters.setText("");
                    lblSuccess.setText("");
                    lblMSE.setText("");
//                    System.out.println(txtIterLimit.getText().matches("[1-9]9+"));
//                    System.out.println(txtLearningRate.getText().matches("[0-9]\\.[0-9]+"));
                    if (!txtIterLimit.getText().matches("[1-9][0-9]*"))
                        lblError.setText("Please enter the number of iterations.");
                    else if (!txtLearningRate.getText().matches("[0-9]\\.[0-9]+"))
                        lblError.setText("Please enter a valid Learning Rate.");
                    else if (!txtHLNeurons.getText().matches("[1-9][0-9]*"))
			lblError.setText("Please enter a valid number for HL neurons.");
                    else{
                        lblError.setText("");
                        double lr = Double.parseDouble(txtLearningRate.getText());
                        int hn = Integer.parseInt(txtHLNeurons.getText());
                        nn = new NeuralNetwork(lr, 100, hn, 10);
                        try{
                            nn.loadWeights("data/nn_weights.txt");
                            saveLastData();
                            loadTrainPatterns();
                            nn.train(trainPatterns, expectedOutputs, Integer.parseInt(txtIterLimit.getText()));
                            lblIters.setText(nn.iters() + "");
                            double [] r = nn.test(trainPatterns, expectedOutputs, false);
                            lblSuccess.setText(String.format("%.2f %%", r[0] * 100));
                            nn.saveWeights("data/nn_weights.txt");
                            lblMSE.setText(String.format("%f", r[1]));
                        }catch (Exception e1) { e1.printStackTrace(); }
                    }
                    btnTrain.setEnabled(true);
                    btnTrain.setText("Train");
                }
            }.start();            
        }
        
    }
    
    private void loadTrainPatterns() {
        try {
            saveLastData();
            Scanner in = new Scanner(new File("data/train.txt"));
            int N = in.nextInt();
            int size = in.nextInt();
            trainPatterns = new int[N][size];
            expectedOutputs = new int[N][10];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < size; j++)
                    trainPatterns[i][j] = in.nextInt();
                for (int j = 0; j < 10; j++)
                    expectedOutputs[i][j] = in.nextInt();
            }
            in.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}
}
    
    private void loadWriteFile(){
        try {
            Scanner input = new Scanner(new File("data/train.txt"));
            try { N = input.nextInt(); }
            catch (NoSuchElementException e) { N = 0; }
            input.close();
            System.out.println("N Load = " + N);
            trainDataFile = new FileWriter("data/train.txt",true);
        }
        catch (IOException e) {e.printStackTrace();}
    }
    
    private void saveLastData() {
        try {
            trainDataFile.close();
            File tmpFile = new File("data/train.tmp");
            File trainFile = new File("data/train.txt");
            Scanner in = new Scanner(trainFile);
            FileWriter tmpFileOut = new FileWriter(tmpFile);
            tmpFileOut.write(N + " 100\n ");
            in.nextInt(); 
            in.nextInt();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 100; j++)
                    tmpFileOut.write(in.nextInt() + " ");
                tmpFileOut.write("\n");
                for (int j = 0; j < 10; j++)
                    tmpFileOut.write(in.nextInt() + " ");
                tmpFileOut.write("\n");
            }
            tmpFileOut.close();
            in.close();
            //System.out.println("check " + trainFile.canWrite());
            trainFile.delete();
            boolean bool = tmpFile.renameTo(trainFile);
            System.out.println("rename sucess" + bool);
            loadWriteFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.out.println("closing");
        saveLastData();
	try { trainDataFile.close(); }
	catch (IOException e1) { e1.printStackTrace(); }
    
    }
    public void windowClosed(WindowEvent e) {
        System.out.println("closing");
        saveLastData();
	try { trainDataFile.close(); }
	catch (IOException e1) { e1.printStackTrace(); }
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
