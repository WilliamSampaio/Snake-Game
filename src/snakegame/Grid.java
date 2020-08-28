package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import jplay.GameImage;
import jplay.Window;

public class Grid {

    private Point gridSize;
    private final Color gridColor;

    private GameImage gridUnit;

    public Point getGridSize() {
        return gridSize;
    }

    public void setGridSize(Point gridSize) {
        this.gridSize = gridSize;
    }

    public Grid(Point gridSize, String imagePath, Color gridColor) {
        this.gridSize = gridSize;
        gridUnit = new GameImage(imagePath);
        this.gridColor = gridColor;
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(Color.BLACK);
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) - 1,
                ((screenSize.y - (gridSize.y * unitSize)) / 2) - 1,
                (gridSize.x * unitSize) + 2,
                (gridSize.y * unitSize) + 2);

        graficos.setColor(gridColor);
        graficos.fillRect(
                (screenSize.x - (gridSize.x * unitSize)) / 2,
                (screenSize.y - (gridSize.y * unitSize)) / 2,
                gridSize.x * unitSize,
                gridSize.y * unitSize);

        return graficos;
    }

    public void draw(Window gameWindow, int unitSize) {

        gridUnit.width = unitSize;
        gridUnit.height = unitSize;

        gameWindow.getGameGraphics().setColor(Color.BLACK);
        gameWindow.getGameGraphics().fillRect(
                ((gameWindow.getWidth() / 2 - (gridSize.x * unitSize))) - 1,
                ((gameWindow.getHeight() - (gridSize.y * unitSize)) / 2) - 1,
                (gridSize.x * unitSize) + 2,
                (gridSize.y * unitSize) + 2);

        gameWindow.getGameGraphics().setColor(gridColor);
        gameWindow.getGameGraphics().fillRect(
                (gameWindow.getWidth() / 2 - (gridSize.x * unitSize)),
                (gameWindow.getHeight() - (gridSize.y * unitSize)) / 2,
                gridSize.x * unitSize,
                gridSize.y * unitSize);

        for (int x = 0; x < gridSize.x; x++) {
            for (int y = 0; y < gridSize.y; y++) {
                gridUnit.x = ((gameWindow.getWidth() / 2 - (gridSize.x * unitSize))) + x * unitSize;
                gridUnit.y = ((gameWindow.getHeight() - (gridSize.y * unitSize)) / 2) + y * unitSize;
                gridUnit.draw();
            }
        }

    }

}
