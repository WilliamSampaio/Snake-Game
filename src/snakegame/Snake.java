package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private boolean alive;
    private List<Point> segments;
    private String direction;
    private Color colorAlive;
    private Color colorDie;
    private ArrayList<KeyEvent> aux;

    public List<Point> getSegments() {
        return segments;
    }

    public void addSegments(Point p) {
        this.segments.add(p);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Snake(String direction, Color alive, Color die) {
        this.direction = direction;
        this.colorAlive = alive;
        this.colorDie = die;
        this.segments = new ArrayList<>();
        this.alive = true;

        this.aux = new ArrayList<>();
    }

    public void move(Point gridSize) {
        Point newPos;
        if (this.alive) {
            if (direction.equals("left")) {
                if (this.segments.get(0).x == 0) {
                    newPos = new Point(gridSize.x - 1, this.segments.get(0).y);
                } else {
                    newPos = new Point(this.segments.get(0).x - 1, this.segments.get(0).y);
                }
            } else if (direction.equals("right")) {
                if (this.segments.get(0).x == (gridSize.x - 1)) {
                    newPos = new Point(0, this.segments.get(0).y);
                } else {
                    newPos = new Point(this.segments.get(0).x + 1, this.segments.get(0).y);
                }
            } else if (direction.equals("up")) {
                if (this.segments.get(0).y == 0) {
                    newPos = new Point(this.segments.get(0).x, gridSize.y - 1);
                } else {
                    newPos = new Point(this.segments.get(0).x, this.segments.get(0).y - 1);
                }
            } else {
                if (this.segments.get(0).y == (gridSize.y - 1)) {
                    newPos = new Point(this.segments.get(0).x, 0);
                } else {
                    newPos = new Point(this.segments.get(0).x, this.segments.get(0).y + 1);
                }
            }
            this.segments.remove(this.segments.size() - 1);
            this.segments.add(0, newPos);
        }
        this.checkSelfCollision();
    }

    void eat(Point gridSize) {
        Point newPos;
        if (direction.equals("left")) {
            if (this.segments.get(0).x == 0) {
                newPos = new Point(gridSize.x - 1, this.segments.get(0).y);
            } else {
                newPos = new Point(this.segments.get(0).x - 1, this.segments.get(0).y);
            }
        } else if (direction.equals("right")) {
            if (this.segments.get(0).x == (gridSize.x - 1)) {
                newPos = new Point(0, this.segments.get(0).y);
            } else {
                newPos = new Point(this.segments.get(0).x + 1, this.segments.get(0).y);
            }
        } else if (direction.equals("up")) {
            if (this.segments.get(0).y == 0) {
                newPos = new Point(this.segments.get(0).x, gridSize.y - 1);
            } else {
                newPos = new Point(this.segments.get(0).x, this.segments.get(0).y - 1);
            }
        } else {
            if (this.segments.get(0).y == (gridSize.y - 1)) {
                newPos = new Point(this.segments.get(0).x, 0);
            } else {
                newPos = new Point(this.segments.get(0).x, this.segments.get(0).y + 1);
            }
        }
        this.segments.add(0, newPos);
        this.checkSelfCollision();
    }

    private void checkSelfCollision() {
        for (int i = 2; i < this.segments.size(); i++) {
            if (this.segments.get(0).equals(this.segments.get(i))) {
                this.alive = false;
            }
        }
    }

    public void keyPressed(KeyEvent tecla) {

        if (aux.isEmpty()) {
            aux.add(tecla);
        } else if (aux.size() == 1) {
            aux.add(tecla);
        } else if (aux.size() == 2) {
            aux.add(tecla);
        } else {
            aux.remove(aux.size() - 1);
            aux.add(tecla);
        }

        int code = aux.get(aux.size() - 1).getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            if (!this.direction.equals("down")) {
                this.direction = "up";
            }
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            if (!this.direction.equals("up")) {
                this.direction = "down";
            }
        }

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            if (!this.direction.equals("right")) {
                this.direction = "left";
            }
        }

        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            if (!this.direction.equals("left")) {
                this.direction = "right";
            }
        }
    }

    public Graphics2D paint(Graphics2D g, Point screenSize, Point gridSize, int unitSize) {
        Graphics2D graficos = (Graphics2D) g;
        if (this.alive) {
            graficos.setColor(this.colorAlive);

            for (int i = 0; i < this.segments.size(); i++) {

                graficos.setColor(this.colorAlive);
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.segments.get(i).x * unitSize),
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.segments.get(i).y * unitSize),
                        unitSize,
                        unitSize);
                graficos.setColor(new Color(137, 151, 116));
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.segments.get(i).x * unitSize) + 1,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.segments.get(i).y * unitSize) + 1,
                        unitSize - 2,
                        unitSize - 2);
                graficos.setColor(this.colorAlive);
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.segments.get(i).x * unitSize) + 3,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.segments.get(i).y * unitSize) + 3,
                        unitSize - 6,
                        unitSize - 6);
            }
        } else {
            graficos.setColor(this.colorDie);

            for (int i = 0; i < this.segments.size(); i++) {
                graficos.drawRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.segments.get(i).x * unitSize) + 7,
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.segments.get(i).y * unitSize) + 7,
                        unitSize - 14,
                        unitSize - 14);
            }
        }
        return graficos;
    }

}
