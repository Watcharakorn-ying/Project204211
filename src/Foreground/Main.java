
package Foreground;

import Background.*;
import java.io.FileNotFoundException;

public class Main {
    public static DrawWin drawWin;
    public static Draw drawPanel;
    public static DrawRealtime drawRealtime;
    public static Recognition recognition;
    public static NeuralNetwork neuralNet;
    
    public static void main(String[] args) throws FileNotFoundException {
        neuralNet = new NeuralNetwork("data/nn_weights.txt", 0.3, 100, 25, 10);
        recognition = new Recognition();
        drawWin = new DrawWin();
    }
}