package snakegame;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author William Benjamim Menezes Sampaio
 */
public final class Game extends JPanel implements ActionListener {

    private Menu menu;
    private Grid grid;
    private Snake snake;
    private Food food;
    private Timer timer;
    private int FPS = 50;
    private Point screenSize;
    private int unitSize;
    private int delay = 250;
    private boolean pause;
    private int gameStatus;
    private MusicPlayer musicPlayer;

    long start = System.nanoTime();
    long finish;
    long timeElapsed = finish - start;

    /**
     * Snake game in Java
     *
     * @param screenSize Current resolution of the screen
     */
    public Game(Point screenSize) {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyBoardAdapter());
        this.screenSize = screenSize;
        unitSize = screenSize.y / 50;

       // menu = new Menu(screenSize, unitSize);

        grid = new Grid(new Point(30, 40), new Color(137, 151, 116));
        snake = new Snake(Constants.RIGHT, Color.BLACK, Color.DARK_GRAY);
        snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(1, 0));
        snake.addSegments(new Point(0, 0));

        food = new Food(new Point(grid.getGridSize().x / 2, grid.getGridSize().y / 2));
        pause = false;

        gameStatus = Constants.IN_MENU;

        musicPlayer = new MusicPlayer(screenSize);
        //musicPlayer.playMusic();
        /*System.out.println(musicPlayer.getPlayList().getComponent(1).getName());
        System.out.println(this.getName());*/

        Thread thread = new Thread(musicPlayer);
        thread.start();

        //musicPlayer.playMusic();
        timer = new Timer(0, this);
        timer.start();

    }

    /**
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {

        start = finish;
        finish = System.nanoTime();

        timeElapsed = finish - start;
        
        if(timeElapsed / 1000000 > 150){
            timeElapsed = 150 * 1000000;
        }

        System.out.println(timeElapsed);

        if (gameStatus == Constants.IN_MENU) {

            switch (menu.getSelectedOption()) {
                case 1:
                    gameStatus = Constants.IN_GAME;
                    break;
                case 2:
                    // in comming!
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

        if (gameStatus == Constants.IN_GAME) {
            if (snake.getSegments().get(0).equals(food.getPosition())) {
                snake.eat(grid.getGridSize());
                food.newPosition(grid.getGridSize(), snake.getSegments());
            } else {
                if (snake.isAlive()) {
                    snake.move(grid.getGridSize());
                }
            }
        }

        repaint();
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(new Color(137, 151, 116));
        graficos.fillRect(0, 0, screenSize.x, screenSize.y);

        if (gameStatus == Constants.IN_MENU) {
            menu.paint(graficos, screenSize, unitSize);
        }

        if (gameStatus == Constants.IN_GAME) {
            grid.paint(graficos, screenSize, unitSize);
            food.paint(graficos, screenSize, grid.getGridSize(), unitSize);
            snake.paint(graficos, screenSize, grid.getGridSize(), unitSize);
        }

        try {
            musicPlayer.paint(graficos, screenSize, unitSize);
        } catch (FontFormatException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        g.dispose();

    }

    private class KeyBoardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            musicPlayer.keyPressed(e);

            if (gameStatus == Constants.IN_MENU) {
                menu.keyPressed(e);
            }

            if (gameStatus == Constants.IN_GAME) {

                if (!snake.isKeyPressed()) {
                    snake.keyPressed(e);
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
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

}
