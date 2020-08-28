/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import jplay.GameObject;
import jplay.Window;

/**
 *
 * @author william
 */
public class Label extends GameObject {

    private String label;
    private Font font;
    private Color color;

    public Label(String label, Font font, Color color) {
        this.label = label;
        this.font = font;
        this.color = color;
        this.width = (int) font.getStringBounds(label, new FontRenderContext() {
        }).getWidth();
        this.height = (int) font.getStringBounds(label, new FontRenderContext() {
        }).getHeight();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        this.width = (int) font.getStringBounds(label, new FontRenderContext() {
        }).getWidth();
        this.height = (int) font.getStringBounds(label, new FontRenderContext() {
        }).getHeight();
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

}
