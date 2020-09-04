package snakegame;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import jplay.Window;
import org.xml.sax.SAXException;

public class SnakeGame extends Constants {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // i will really need explain??

        // get current screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // declare and instances the game window object
        Window gameWindow = new Window(screenSize.width, screenSize.height);

        // set custom mouse cursor
        gameWindow.setCursor(gameWindow.createCustomCursor(DIR_SPRITES + "cursor.png"));

        Menu menu = new Menu(gameWindow);
        menu.run();
    }
}
