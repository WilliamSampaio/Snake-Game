package snakegame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;

public class SnakeGame extends JFrame {

    public SnakeGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException, FileNotFoundException, URISyntaxException {

        // get current screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // declares and instance an object of type Level
        Level fase;
        fase = new Level(new Point(screenSize.width, screenSize.height));
        //fase = new Level(new Point(800, 600));

        // add object of type Level in current JFrame instance
        add(fase);

        // sets the name to show in game window
        setTitle("Snake Game");

        // enable close button in game window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sets the default game window size
        setSize(800, 600);

        // if true then is possible resize game window
        //setResizable(false);
        
        // if true then the upper bar in game window are hide
        //setUndecorated(true);

        // sets game window start maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // sets game window start in center of screen
        setLocationRelativeTo(null);

        // sets window as visible
        setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException, FileNotFoundException, URISyntaxException {

        // i will really need explain??
        SnakeGame game;
        game = new SnakeGame();
    }
}
