package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Grid {

    private Point gridSize;
    private final Color gridColor;

    public Point getGridSize() {
        return gridSize;
    }

    public void setGridSize(Point gridSize) {
        this.gridSize = gridSize;
    }

    public Grid(Point gridSize, Color gridColor) {
        this.gridSize = gridSize;
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

}
