/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import jplay.GameObject;
import jplay.Window;

/**
 *
 * @author william
 */
public class Label extends GameObject {

    private Window gameWindow;
    private String label;
    private Font font;
    private Color color;
    private FontMetrics fontMetrics;

    public void setLabel(String label) {
        
        this.label = label;
        width = fontMetrics.stringWidth(label);
        height = fontMetrics.getHeight();
        
    }

    void setLabel(int score) {
        
        this.label = Integer.toString(score);
        width = fontMetrics.stringWidth(label);
        height = fontMetrics.getHeight();
        
    }

    public Label(Window gameWindow, String label, Font font, Color color) {

        this.gameWindow = gameWindow;
        this.label = label;
        this.font = font;
        this.color = color;

        fontMetrics = gameWindow.getGameGraphics().getFontMetrics(font);

        width = fontMetrics.stringWidth(label);
        height = fontMetrics.getHeight();

    }

    public void draw(int x, int y) {

        FontMetrics fontMetrics = gameWindow.getGameGraphics().getFontMetrics(font);
        gameWindow.drawText(label, x, y + fontMetrics.getAscent(), color, font);

    }

}
