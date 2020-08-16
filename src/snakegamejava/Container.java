package snakegamejava;

//package S05_SimulacaoFisicaJogo;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

public class Container extends JFrame {

    public Container() throws LineUnavailableException, IOException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //System.out.println(screenSize.width + " . " + screenSize.height);
        
        Level fase = new Level(new Point(screenSize.width, screenSize.height));

        add(fase);

        setTitle("Meu Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 600);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        //setResizable(false);
        
        //setUndecorated(true); // barra superior
        setVisible(true);
    }

    public static void main(String[] args) throws LineUnavailableException, IOException {
        Container container = new Container();
    }
}
