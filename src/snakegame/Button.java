package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.FontRenderContext;
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
    private Window gameWindow;

    private BufferedImage image1;
    private BufferedImage image2;

    private String label;
    private Font font1;
    private Font font2;
    private Color color;

    /**
     *
     * @param filePath
     * @param gameWindow
     */
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

        label = new String();

    }

    /**
     *
     * @param label
     * @param font
     * @param color
     * @param gameWindow
     */
    public Button(String label, Font font, Color color, Window gameWindow) {

        this.gameWindow = gameWindow;
        button = new GameObject();
        this.label = label;
        this.font1 = font;
        this.font2 = font.deriveFont(Font.BOLD);
        this.color = color;

        button.width = (int) font2.getStringBounds(this.label, new FontRenderContext() {
        }).getWidth();
        button.height = (int) font2.getStringBounds(this.label, new FontRenderContext() {
        }).getHeight();

    }

    /**
     *
     */
    public void draw() {
        if (label.isEmpty()) {
            gameWindow.getGameGraphics().drawImage(image1, (int) button.x, (int) button.y, button.width, button.height, null);
        } else {
            FontMetrics metrics = gameWindow.getGameGraphics().getFontMetrics(font2);
            gameWindow.drawText(label, (int) button.x, (int) button.y + metrics.getAscent(), color, font1);
        }

    }

    /**
     *
     */
    public void drawSelected() {

        if (label.isEmpty()) {
            gameWindow.getGameGraphics().drawImage(image2, (int) button.x, (int) button.y, button.width, button.height, null);
        } else {
            FontMetrics metrics = gameWindow.getGameGraphics().getFontMetrics(font2);
            gameWindow.drawText(label, (int) button.x, (int) button.y + metrics.getAscent(), color, font2);
        }

    }

    /**
     *
     * @return
     */
    public boolean isMouseOn() {

        return gameWindow.getMouse().isOverObject(button);

    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        button.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        button.y = y;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        button.width = width;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        button.height = height;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return (int) button.x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return (int) button.y;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return button.width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return button.height;
    }

}
