package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private boolean alive;
    private final List<Point> segments;
    private int direction;
    private final Color colorAlive;
    private final Color colorDie;
    private boolean keyPressed;

    public List<Point> getSegments() {
        return segments;
    }

    public void addSegments(Point p) {
        segments.add(p);
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }

    public Snake(int direction, Color alive, Color die) {
        this.direction = direction;
        colorAlive = alive;
        colorDie = die;
        segments = new ArrayList<>();
        keyPressed = false;
        this.alive = true;
    }

    public void move(Point gridSize) {
        Point newPos;
        if (alive) {
            switch (direction) {
                case Game.LEFT:
                    if (segments.get(0).x == 0) {
                        newPos = new Point(gridSize.x - 1, segments.get(0).y);
                    } else {
                        newPos = new Point(segments.get(0).x - 1, segments.get(0).y);
                    }
                    break;
                case Game.RIGHT:
                    if (segments.get(0).x == (gridSize.x - 1)) {
                        newPos = new Point(0, segments.get(0).y);
                    } else {
                        newPos = new Point(segments.get(0).x + 1, segments.get(0).y);
                    }
                    break;
                case Game.UP:
                    if (segments.get(0).y == 0) {
                        newPos = new Point(segments.get(0).x, gridSize.y - 1);
                    } else {
                        newPos = new Point(segments.get(0).x, segments.get(0).y - 1);
                    }
                    break;
                default:
                    if (segments.get(0).y == (gridSize.y - 1)) {
                        newPos = new Point(segments.get(0).x, 0);
                    } else {
                        newPos = new Point(segments.get(0).x, segments.get(0).y + 1);
                    }
                    break;
            }
            segments.remove(segments.size() - 1);
            segments.add(0, newPos);
        }
        checkSelfCollision();
        keyPressed = false;
    }

    void eat(Point gridSize) {
        Point newPos;
        switch (direction) {
            case Game.LEFT:
                if (segments.get(0).x == 0) {
                    newPos = new Point(gridSize.x - 1, segments.get(0).y);
                } else {
                    newPos = new Point(segments.get(0).x - 1, segments.get(0).y);
                }
                break;
            case Game.RIGHT:
                if (segments.get(0).x == (gridSize.x - 1)) {
                    newPos = new Point(0, segments.get(0).y);
                } else {
                    newPos = new Point(segments.get(0).x + 1, segments.get(0).y);
                }
                break;
            case Game.UP:
                if (segments.get(0).y == 0) {
                    newPos = new Point(segments.get(0).x, gridSize.y - 1);
                } else {
                    newPos = new Point(segments.get(0).x, segments.get(0).y - 1);
                }
                break;
            default:
                if (segments.get(0).y == (gridSize.y - 1)) {
                    newPos = new Point(segments.get(0).x, 0);
                } else {
                    newPos = new Point(segments.get(0).x, segments.get(0).y + 1);
                }
                break;
        }
        segments.add(0, newPos);
        checkSelfCollision();
    }

    private void checkSelfCollision() {
        for (int i = 2; i < segments.size(); i++) {
            if (segments.get(0).equals(segments.get(i))) {
                alive = false;
            }
        }
    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            if (direction != Game.DOWN) {
                direction = Game.UP;
            }
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            if (direction != Game.UP) {
                direction = Game.DOWN;
            }
        }

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            if (direction != Game.RIGHT) {
                direction = Game.LEFT;
            }
        }

        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            if (direction != Game.LEFT) {
                direction = Game.RIGHT;
            }
        }
        keyPressed = true;
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, Point gridSize, int unitSize) {
        Graphics2D graficos = (Graphics2D) g;
        if (alive) {
            graficos.setColor(colorAlive);

            for (int i = 0; i < segments.size(); i++) {

                graficos.setColor(colorAlive);
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (segments.get(i).x * unitSize),
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (segments.get(i).y * unitSize),
                        unitSize,
                        unitSize);
                graficos.setColor(new Color(137, 151, 116));
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (segments.get(i).x * unitSize) + 1,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (segments.get(i).y * unitSize) + 1,
                        unitSize - 2,
                        unitSize - 2);
                graficos.setColor(colorAlive);
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (segments.get(i).x * unitSize) + 3,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (segments.get(i).y * unitSize) + 3,
                        unitSize - 6,
                        unitSize - 6);
            }
        } else {
            graficos.setColor(colorDie);
            for (int i = 0; i < segments.size(); i++) {
                graficos.drawRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (segments.get(i).x * unitSize) + 7,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (segments.get(i).y * unitSize) + 7,
                        unitSize - 14,
                        unitSize - 14);
            }
        }
        return graficos;
    }

}
