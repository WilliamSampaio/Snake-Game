package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

public class Menu {

    private Point position;
    private Point dimension;
    private Image menuScreen;

    /*public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }*/

    public Menu(Point screenSize) {
        this.menuScreen = new ImageIcon("src/resources/menu_screen.png").getImage();
        this.dimension = new Point();
        this.dimension.x = menuScreen.getWidth(null) * 5; 
        this.dimension.y = menuScreen.getHeight(null) * 5; 
        this.position = new Point();
        this.position.x = (screenSize.x - this.dimension.x) / 2;
        this.position.y = (screenSize.y - this.dimension.y) / 2;
    }

    /*void newPosition(Point gridSize, List<Point> segments) {

        Point possiblePosition;
        possiblePosition = new Point();

        Random rand = new Random();
        int rangeX = gridSize.x;
        int rangeY = gridSize.y;

        possiblePosition.x = rand.nextInt(rangeX);
        possiblePosition.y = rand.nextInt(rangeY);

        for (int i = 0; i < segments.size(); i++) {
            if (possiblePosition.equals(segments.get(i))) {
                this.newPosition(gridSize, segments);
            }
        }

        this.position.x = rand.nextInt(rangeX);
        this.position.y = rand.nextInt(rangeY);
    }*/

    public Graphics2D paint(Graphics2D g/*, Point screenSize, Point gridSize, int unitSize*/) {

        Graphics2D graficos = (Graphics2D) g;
        
        //this.menuScreen.
        graficos.drawImage(this.menuScreen, this.position.x, this.position.y, this.dimension.x, this.dimension.y, null);

        /*graficos.setColor(Color.BLACK);
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.position.x * unitSize),
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.position.y * unitSize),
                unitSize,
                unitSize);

        graficos.setColor(new Color(137, 151, 116));
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.position.x * unitSize) + 1,
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.position.y * unitSize) + 1,
                unitSize - 2,
                unitSize - 2);

        graficos.setColor(Color.BLACK);
        graficos.fillRect(
                ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.position.x * unitSize) + 3,
                ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.position.y * unitSize) + 3,
                unitSize - 6,
                unitSize - 6);*/

        return graficos;
    }

}
