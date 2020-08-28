/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import jplay.GameImage;
import jplay.Window;

/**
 *
 * @author william
 */
public class MenuInGame {

    private Window gameWindow;
    private GameImage menu;
    private Color backColor;

    public MenuInGame(Window gameWindow, String filePath, Color backColor) {
        this.gameWindow = gameWindow;
        menu = new GameImage(filePath);
        this.backColor = backColor;

        Dimension aux = new Dimension(menu.width, menu.height);
        menu.height = gameWindow.getHeight() / 2;
        menu.width = (aux.width * (gameWindow.getHeight() / 2)) / aux.height;

        menu.x = (gameWindow.getWidth() - menu.width) / 2;
        menu.y = (gameWindow.getHeight() - menu.height) / 2;
    }

    public void draw() {
        gameWindow.getGameGraphics().setColor(backColor);
        gameWindow.getGameGraphics().fillRect((int) menu.x, (int) menu.y, menu.width, menu.height);
        menu.draw();
    }

}
