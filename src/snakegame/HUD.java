/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import jplay.GameObject;
import jplay.Window;

/**
 *
 * @author William Benjamim Menezes Sampaio <williambenjamimms97@gmail.com>
 */
public class HUD extends GameObject {

    private Window gameWindow;
    private final Grid grid;
    private final int unitSize;
    private Font font;
    private Color color;
    private List<Label> hudText;

    public HUD(Window gameWindow, Grid grid, int unitSize, Font font, Color color) {

        this.gameWindow = gameWindow;
        this.grid = grid;
        this.unitSize = unitSize;
        this.font = font;
        this.color = color;
        hudText = new ArrayList<>();
        hudText.add(new Label(this.gameWindow, "SCORE", font, color));
        hudText.add(new Label(this.gameWindow, Integer.toString(0), font, color));
        hudText.add(new Label(this.gameWindow, "HI-SCORE", font, color));
        hudText.add(new Label(this.gameWindow, Integer.toString(0), font, color));
        hudText.add(new Label(this.gameWindow, "LEVEL", font, color));
        hudText.add(new Label(this.gameWindow, Integer.toString(0), font, color));

    }

    void update(int score, int highScore, int level) {

            hudText.get(1).setLabel(score);
            hudText.get(3).setLabel(highScore);
            hudText.get(5).setLabel(level);

    }

    public void draw() {

        for (int i = 0; i < hudText.size(); i++) {
            hudText.get(i).draw(((gameWindow.getWidth() / 4) * 3) - hudText.get(i).width / 2, ((gameWindow.getHeight() - (grid.getGridSize().y * unitSize)) / 2) + i * (hudText.get(i).height + unitSize));
        }

    }

}
