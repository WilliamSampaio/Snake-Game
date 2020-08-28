package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

public class Snake {

    private boolean alive;
    private final List<Point> segments;
    private int direction;
    private boolean keyPressed;
    private GameImage snake;

    public List<Point> getSegments() {
        return segments;
    }

    public void addSegments(int x, int y) {
        segments.add(new Point(x, y));
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }

    public Snake(int direction, Color alive, Color die) {
        this.direction = direction;
        /*colorAlive = alive;
        colorDie = die;*/
        segments = new ArrayList<>();
        keyPressed = false;
        this.alive = true;
    }

    public Snake(int direction, String imagePath) {
        this.direction = direction;
        /*colorAlive = Color.BLACK;
        colorDie = Color.BLACK;*/
        snake = new GameImage(imagePath);
        segments = new ArrayList<>();
        keyPressed = false;
        this.alive = true;
    }

    public void move(Point gridSize) {
        Point newPos;
        if (alive) {
            switch (direction) {
                case Constants.LEFT:
                    if (segments.get(0).x == 0) {
                        newPos = new Point(gridSize.x - 1, segments.get(0).y);
                    } else {
                        newPos = new Point(segments.get(0).x - 1, segments.get(0).y);
                    }
                    break;
                case Constants.RIGHT:
                    if (segments.get(0).x == (gridSize.x - 1)) {
                        newPos = new Point(0, segments.get(0).y);
                    } else {
                        newPos = new Point(segments.get(0).x + 1, segments.get(0).y);
                    }
                    break;
                case Constants.UP:
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
            case Constants.LEFT:
                if (segments.get(0).x == 0) {
                    newPos = new Point(gridSize.x - 1, segments.get(0).y);
                } else {
                    newPos = new Point(segments.get(0).x - 1, segments.get(0).y);
                }
                break;
            case Constants.RIGHT:
                if (segments.get(0).x == (gridSize.x - 1)) {
                    newPos = new Point(0, segments.get(0).y);
                } else {
                    newPos = new Point(segments.get(0).x + 1, segments.get(0).y);
                }
                break;
            case Constants.UP:
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
            if (direction != Constants.DOWN) {
                direction = Constants.UP;
            }
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            if (direction != Constants.UP) {
                direction = Constants.DOWN;
            }
        }

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            if (direction != Constants.RIGHT) {
                direction = Constants.LEFT;
            }
        }

        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            if (direction != Constants.LEFT) {
                direction = Constants.RIGHT;
            }
        }

    }

    public void keyboardActions(Keyboard gameKeyboard) {
        if (gameKeyboard.keyDown(Keyboard.UP_KEY)) {
            if (direction != Constants.DOWN) {
                direction = Constants.UP;
            }
        }

        if (gameKeyboard.keyDown(Keyboard.DOWN_KEY)) {
            if (direction != Constants.UP) {
                direction = Constants.DOWN;
            }
        }

        if (gameKeyboard.keyDown(Keyboard.LEFT_KEY)) {
            if (direction != Constants.RIGHT) {
                direction = Constants.LEFT;
            }
        }

        if (gameKeyboard.keyDown(Keyboard.RIGHT_KEY)) {
            if (direction != Constants.LEFT) {
                direction = Constants.RIGHT;
            }
        }
        keyPressed = true;
    }

    public void draw(Window gameWindow, Point gridSize, int unitSize) {

        snake.width = unitSize;
        snake.height = unitSize;

        for (int i = 0; i < segments.size(); i++) {
            snake.x = ((gameWindow.getWidth() / 2 - (gridSize.x * unitSize))) + (segments.get(i).x * unitSize);
            snake.y = ((gameWindow.getHeight() - (gridSize.y * unitSize)) / 2) + (segments.get(i).y * unitSize);
            snake.draw();
        }
    }

}
