package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author William Benjamim Menezes Sampaio
 */
public final class Level extends JPanel implements ActionListener {

    private Snake snake;
    private Food food;
    private Timer timer;
    private int FPS = 50;
    private Point screenSize;
    private Point gridSize;
    private int unitSize;
    private int delay = 250;
    private boolean pause;

    /**
     * Snake game in Java
     * @param screenSize Current resolution of the screen
     */
    public Level(Point screenSize)/* throws LineUnavailableException, IOException */ {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyBoardAdapter());
        this.screenSize = screenSize;
        this.unitSize = this.screenSize.y / 50;
        this.gridSize = new Point(45, 45);
        this.snake = new Snake("left", Color.YELLOW, Color.LIGHT_GRAY);
        snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(3, 0));
        snake.addSegments(new Point(4, 0));
        /*snake.addSegments(new Point(5, 0));
        snake.addSegments(new Point(6, 0));
        snake.addSegments(new Point(7, 0));
        snake.addSegments(new Point(8, 0));
        snake.addSegments(new Point(9, 0));
        snake.addSegments(new Point(10, 0));
        snake.addSegments(new Point(11, 0));
        snake.addSegments(new Point(12, 0));
        snake.addSegments(new Point(13, 0));
        snake.addSegments(new Point(14, 0));
        snake.addSegments(new Point(15, 0));
        snake.addSegments(new Point(16, 0));
        snake.addSegments(new Point(17, 0));*/
        this.food = new Food(new Point(this.gridSize.x / 2, this.gridSize.y / 2));
        this.pause = false;
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(Color.BLACK);
        graficos.fillRect(0, 0, screenSize.x, screenSize.y);
        graficos.setColor(Color.DARK_GRAY);
        graficos.fillRect(
                (screenSize.x - (gridSize.x * unitSize)) / 2,
                (screenSize.y - (gridSize.y * unitSize)) / 2,
                gridSize.x * unitSize,
                gridSize.y * unitSize);

        snake.paint(graficos, screenSize, gridSize, unitSize);
        food.paint(graficos, screenSize, gridSize, unitSize);

        /* switch (emJogo) {
            case ESTADO_EMJOGO:
                //graficos.drawImage(snake.getImagem(), snake.getX(), snake.getY(), snake.getAltura() / 2, snake.getLargura() / 2, this);

                //List<Missel> misseis = snake.getMisseis();
                /*for (int i = 0; i < misseis.size(); i++) {

                    Missel m = (Missel) misseis.get(i);
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
                for (int i = 0; i < inimigos.size(); i++) {

                    Inimigo in = inimigos.get(i);
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                //graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("VIDAS: " + vidas, 200, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("NAVE: " + naveAtual, 350, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("FASE: " + faseAtual, 500, 20);

                if (faseAtual == 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("EQUIPE:", 300, 100);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LEVY LIRA", 300, 120);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LISVANETE GARCIA", 300, 140);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  GABRIEL BEZERRA", 300, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  WILLIAM SAMPAIO", 300, 180);

                    ImageIcon unip = new ImageIcon("./assets/img/unip.png");
                    graficos.drawImage(unip.getImage(), 600, 75, null);
                }

                break;
            case ESTADO_FASECONCLUIDA:
                if (faseAtual != 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 60));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("FASE " + faseAtual, 100, 110);
                    graficos.setFont(new Font("arial", Font.BOLD, 40));
                    graficos.drawString("CONCLUIDA", 100, 160);
                    graficos.setFont(new Font("arial", Font.BOLD, 80));
                    graficos.drawString(">>>", 550, 160);

                }
                break;
            case ESTADO_GAMEOVER: {
                graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);

                graficos.setColor(Color.WHITE);

                graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("GAME OVER", 100, 160);
                break;
            }
            case ESTADO_MENU: {
                graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);

 graficos.setColor(Color.YELLOW);

                for (int i = 0; i < snake.getSegments().size(); i++) {
                    //int snake
                    graficos.fillRect(
                            ((getScreenSize().x - (getGridSize().x * getUnitSize())) / 2) + (snake.getSegments().get(i).x * getUnitSize()),
                            ((getScreenSize().y - (getGridSize().y * getUnitSize())) / 2) + (snake.getSegments().get(i).y * getUnitSize()),
                            getUnitSize() - 1,
                            getUnitSize() - 1);
                }*/

 /*graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("DS1A34", 200, 160);
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("PRESSIONE ENTER PARA INICIAR", 440, 550);
                break;
            }

        }*/
        g.dispose();

    }

    /**
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (snake.getSegments().get(0).equals(food.getPosition())) {
            snake.eat(this.gridSize);
            food.newPosition(this.gridSize, snake.getSegments());
        } else {
            if (snake.isAlive()) {
                snake.move(this.gridSize);
            }
        }
        repaint();
    }

    private class KeyBoardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            snake.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // coming soon

            }

            if (e.getKeyCode() == KeyEvent.VK_P) {
                if (!pause) {
                    timer.stop();
                    pause = true;
                } else {
                    timer.start();
                    pause = false;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

}
