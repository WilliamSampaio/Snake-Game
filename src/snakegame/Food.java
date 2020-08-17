package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Food {

    private Point position;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Food(Point initialPos) {
        position = initialPos;
    }

    void newPosition(Point gridSize, List<Point> segments) {

        Point possiblePosition;
        possiblePosition = new Point();

        Random rand = new Random();
        int rangeX = gridSize.x;
        int rangeY = gridSize.y;

        possiblePosition.x = rand.nextInt(rangeX);
        possiblePosition.y = rand.nextInt(rangeY);

        for (int i = 0; i < segments.size(); i++) {
            if (possiblePosition.equals(segments.get(i))) {
                newPosition(gridSize, segments);
            }
        }

        position.x = rand.nextInt(rangeX);
        position.y = rand.nextInt(rangeY);
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, Point gridSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(Color.BLACK);
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (position.x * unitSize),
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (position.y * unitSize),
                unitSize,
                unitSize);

        graficos.setColor(new Color(137, 151, 116));
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (position.x * unitSize) + 1,
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (position.y * unitSize) + 1,
                unitSize - 2,
                unitSize - 2);

        graficos.setColor(Color.BLACK);
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (position.x * unitSize) + 3,
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (position.y * unitSize) + 3,
                unitSize - 6,
                unitSize - 6);

        return graficos;
    }

}
