package snakegame;

import com.sun.tools.javac.Main;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import jplay.Window;
import org.xml.sax.SAXException;

public class SnakeGame extends JFrame {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // i will really need explain??

        // get current screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // declare and instances the game window object
        Window gameWindow = new Window(screenSize.width, screenSize.height);

        // set custom mouse cursor
        gameWindow.setCursor(gameWindow.createCustomCursor(Constants.SPRITES + "cursor.png"));

        Menu menu = new Menu(gameWindow);
        menu.run();
    }
}
