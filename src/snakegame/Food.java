package snakegame;

import java.awt.Point;
import java.util.List;
import java.util.Random;
import jplay.GameImage;
import jplay.Window;

public class Food {

    private final Window gameWindow;
    private final Point gridSize;
    private final int unitSize;
    private GameImage food;
    private Point foodPosition;

    public Point getPosition() {
        return foodPosition;
    }

    public Food(Window gameWindow, Point gridSize, int unitSize, Point initialPos, String imagePath) {
        this.gameWindow = gameWindow;
        this.gridSize = gridSize;
        this.unitSize = unitSize;
        foodPosition = initialPos;
        food = new GameImage(imagePath);
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

        foodPosition = possiblePosition;
    }

    void draw() {
        food.width = unitSize;
        food.height = unitSize;
        food.x = ((gameWindow.getWidth() / 2 - (gridSize.x * unitSize))) + (foodPosition.x * unitSize);
        food.y = ((gameWindow.getHeight() - (gridSize.y * unitSize)) / 2) + (foodPosition.y * unitSize);
        food.draw();
    }

}
