package snakegame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
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
        gameWindow.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(Constants.RESOURCES + "default/" + Constants.SPRITES + "cursor.png").getImage(),
                new Point(0, 0), "custom cursor"));

        Menu menu = new Menu(gameWindow);
        menu.run();
    }
}
