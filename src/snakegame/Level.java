package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author William Benjamim Menezes Sampaio
 */
public final class Level extends JPanel implements ActionListener {

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

    /**
     * Snake game in Java
     *
     * @param screenSize Current resolution of the screen
     */
    public Level(Point screenSize) throws JavaLayerException, FileNotFoundException, URISyntaxException {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyBoardAdapter());
        this.screenSize = screenSize;
        unitSize = screenSize.y / 50;

        menu = new Menu(screenSize, unitSize);

        grid = new Grid(new Point(30, 40), new Color(137, 151, 116));
        snake = new Snake(Game.RIGHT, Color.BLACK, Color.DARK_GRAY);
        snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(1, 0));
        snake.addSegments(new Point(0, 0));

        food = new Food(new Point(grid.getGridSize().x / 2, grid.getGridSize().y / 2));
        pause = false;

        gameStatus = Game.IN_MENU;

        musicPlayer = new MusicPlayer();
        Thread thread = new Thread(musicPlayer);
        thread.start();

        timer = new Timer(delay, this);
        timer.start();

        /*thread = new Thread();
        FileInputStream fileInputStream = new FileInputStream("resources/du-hast.mp3");
        player = new Player(fileInputStream);
        thread.start();
        run();*/
    }

    /*public void play() throws JavaLayerException {
        run();
    }

    private void run() throws JavaLayerException {
        player.play();
    }*/
    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.setColor(new Color(137, 151, 116));
        graficos.fillRect(0, 0, screenSize.x, screenSize.y);

        if (gameStatus == Game.IN_MENU) {
            menu.paint(graficos, screenSize, unitSize);
        }

        if (gameStatus == Game.IN_GAME) {
            grid.paint(graficos, screenSize, unitSize);
            food.paint(graficos, screenSize, grid.getGridSize(), unitSize);
            snake.paint(graficos, screenSize, grid.getGridSize(), unitSize);
        }

        g.dispose();

    }

    /**
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (gameStatus == Game.IN_MENU) {

            switch (menu.getSelectedOption()) {
                case 1:
                    gameStatus = Game.IN_GAME;
                    break;
                case 2:
                    // in comming!
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

        if (gameStatus == Game.IN_GAME) {
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

    private class KeyBoardAdapter extends KeyAdapter {

        //boolean keyPressed = false;
        @Override
        public void keyPressed(KeyEvent e) {

            if (gameStatus == Game.IN_MENU) {
                menu.keyPressed(e);
            }

            if (gameStatus == Game.IN_GAME) {

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
