package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
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
        this.position = initialPos;
    }

    void newPosition(Point gridSize/*, List<Point> segments*/) {

        Random rand = new Random(); //instance of random class
        int rangeX = gridSize.x;
        int rangeY = gridSize.y;

        this.position.x = rand.nextInt(rangeX);
        this.position.y = rand.nextInt(rangeY);
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, Point gridSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(Color.CYAN);

        //for (int i = 0; i < this.getSegments().size(); i++) {
        //int snake
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.position.x * unitSize),
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.position.y * unitSize),
                unitSize - 1,
                unitSize - 1);
        // }

        return graficos;
    }

}
