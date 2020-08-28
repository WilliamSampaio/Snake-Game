/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jplay.GameObject;
import jplay.Window;

/**
 *
 * @author william
 */
public class Button {

    private GameObject button;
    private BufferedImage image1;
    private BufferedImage image2;
    private Window gameWindow;

    public Button(String filePath, Window gameWindow) {

        this.gameWindow = gameWindow;

        BufferedImage imageFile;
        button = new GameObject();

        try {
            imageFile = ImageIO.read(new File(filePath));
            image1 = imageFile.getSubimage(0, 0, imageFile.getWidth() / 2, imageFile.getHeight());
            image2 = imageFile.getSubimage(imageFile.getWidth() / 2, 0, imageFile.getWidth() / 2, imageFile.getHeight());
        } catch (IOException ex) {
            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
        }

        button.width = image1.getWidth();
        button.height = image1.getHeight();

    }

    public void draw() {
        gameWindow.getGameGraphics().drawImage(image1, (int) button.x, (int) button.y, button.width, button.height, null);
    }

    public void drawSelected() {
        gameWindow.getGameGraphics().drawImage(image2, (int) button.x, (int) button.y, button.width, button.height, null);
    }

    public boolean isMouseOn() {
        return gameWindow.getMouse().isOverObject(button);
    }

    public void setX(int x) {
        button.x = x;
    }

    public void setY(int y) {
        button.y = y;
    }

    public void setWidth(int width) {
        button.width = width;
    }

    public void setHeight(int height) {
        button.height = height;
    }

    public int getX() {
        return (int) button.x;
    }

    public int getY() {
        return (int) button.y;
    }

    public int getWidth() {
        return button.width;
    }

    public int getHeight() {
        return button.height;
    }

}
