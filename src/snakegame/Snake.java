package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Snake {

    private int x, y;
    private int dx, dy;
    private int largura, altura;
    private boolean isVisible;
    private Image imagem;
    private List<Missel> misseis;

    private boolean alive;
    private List<Point> segments;
    private String direction;

    private Color colorAlive;
    private Color colorDie;

    Snake(String left) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Point> getSegments() {
        return segments;
    }

    public void setSegments(List<Point> segments) {
        this.segments = segments;
    }

    public void addSegments(Point p) {
        this.segments.add(p);
        //this.segments.re
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public List<Missel> getMisseis() {
        return misseis;
    }

    public void setMisseis(List<Missel> misseis) {
        this.misseis = misseis;
    }

    public Snake(String direction, Color alive, Color die) {

        this.direction = direction;
        this.colorAlive = alive;
        this.colorDie = die;

        /*setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
        setAltura(imagem.getHeight(null) / 2);
        setLargura(imagem.getHeight(null) / 2);
        setMisseis(new ArrayList<Missel>());// misseis = new ArrayList<Missel>();*/

 /*this.x = 100;
        this.y = 300;*/
        //setSegments(new ArrayList<>());
        this.segments = new ArrayList<>();
        this.alive = true;

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

    public void atira() {
        this.misseis.add(new Missel(x + (largura / 3), y + (altura / 7)));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int cod = tecla.getKeyCode();

        if (cod == KeyEvent.VK_SPACE) {
            atira();
        }

        if (cod == KeyEvent.VK_UP || cod == KeyEvent.VK_W) {
            if (!this.direction.equals("down")) {
                this.direction = "up";
            }
        }

        if (cod == KeyEvent.VK_DOWN || cod == KeyEvent.VK_S) {
            if (!this.direction.equals("up")) {
                this.direction = "down";
            }
        }

        if (cod == KeyEvent.VK_LEFT || cod == KeyEvent.VK_A) {
            if (!this.direction.equals("right")) {
                this.direction = "left";
            }
        }

        if (cod == KeyEvent.VK_RIGHT || cod == KeyEvent.VK_D) {
            if (!this.direction.equals("left")) {
                this.direction = "right";
            }
        }
    }

    /*public void keyReleased(KeyEvent tecla) {
        int cod = tecla.getKeyCode();

        if (cod == KeyEvent.VK_UP) {
            dy = 0;

        }

        if (cod == KeyEvent.VK_DOWN) {
            dy = 0;

        }

        if (cod == KeyEvent.VK_LEFT) {
            dx = 0;

        }

        if (cod == KeyEvent.VK_RIGHT) {
            dx = 0;

        }
    }*/
    public Graphics2D paint(Graphics2D g, Point screenSize, Point gridSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;
        if (this.alive) {
            graficos.setColor(this.colorAlive);

            for (int i = 0; i < this.getSegments().size(); i++) {
                //int snake
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.getSegments().get(i).x * unitSize),
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.getSegments().get(i).y * unitSize),
                        unitSize - 1,
                        unitSize - 1);
            }
        } else {
            graficos.setColor(this.colorDie);

            for (int i = 0; i < this.getSegments().size(); i++) {
                //int snake
                graficos.fillRect(
                        ((screenSize.x - (gridSize.x * unitSize)) / 2) + (this.getSegments().get(i).x * unitSize),
                        ((screenSize.y - (gridSize.y * unitSize)) / 2) + (this.getSegments().get(i).y * unitSize),
                        unitSize - 1,
                        unitSize - 1);
            }
        }
        return graficos;
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

        //this.segments.remove(this.segments.size() - 1);
        this.segments.add(0, newPos);

    }

    void die() {

    }

    private void checkSelfCollision() {

        for (int i = 2; i < this.segments.size(); i++) {
            if (this.segments.get(0).equals(this.segments.get(i))) {
                this.alive = false;
            }
        }
    }
    
}
