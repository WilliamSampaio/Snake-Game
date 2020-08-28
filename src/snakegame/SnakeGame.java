package snakegame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import jplay.Window;

public class SnakeGame extends JFrame {

    public static void main(String[] args) {
        // i will really need explain??

        // get current screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // declare and instances the game window object
        Window gameWindow = new Window(screenSize.width, screenSize.height);

        // set custom mouse cursor
        gameWindow.setCursor(gameWindow.createCustomCursor(Constants.RESOURCES + "default/" + Constants.SPRITES + "cursor.png"));

        Menu menu = new Menu(gameWindow);
        menu.run();
    }
}
