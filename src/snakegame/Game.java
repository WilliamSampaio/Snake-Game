package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jplay.Keyboard;
import jplay.Window;

/**
 *
 * @author William Benjamim Menezes Sampaio GitHub
 * https://github.com/WilliamSampaio E-mail williambenjamimms97@gmail.com
 */
public final class Game {

    private Grid grid;
    private Snake snake;
    private Food food;
    private Point screenSize;
    private int unitSize;
    private boolean musicOn;
    private int gameStatus;

    private Window gameWindow;
    private Keyboard gameKeyboard;

    private int score;
    private int level;
    private int foodCount;

    private Button pauseBtn;
    private Button musicBtn;

    private int delay;
    private Font menuFont;

    /**
     * Snake game in Java
     *
     * @param screenSize Current resolution of the screen
     */
    public Game(Point screenSize) {

        /*setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyBoardAdapter());*/
        this.screenSize = screenSize;
        unitSize = screenSize.y / 20;

        // menu = new Menu(screenSize, unitSize);
        //grid = new Grid(new Point(30, 40), new Color(137, 151, 116));
        snake = new Snake(Constants.RIGHT, Color.BLACK, Color.DARK_GRAY);
        /*snake.addSegments(new Point(2, 0));
        snake.addSegments(new Point(1, 0));
        snake.addSegments(new Point(0, 0));*/

        //food = new Food(new Point(grid.getGridSize().x / 2, grid.getGridSize().y / 2));
        // pause = false;
        gameStatus = Constants.IN_MENU;

        //musicPlayer = new MusicPlayer(screenSize);
        //musicPlayer.playMusic();
        /*System.out.println(musicPlayer.getPlayList().getComponent(1).getName());
        System.out.println(this.getName());*/

 /*Thread thread = new Thread(musicPlayer);
        thread.start();*/
        //musicPlayer.playMusic();
        /*timer = new Timer(0, this);
        timer.start();*/
    }

    public Game(Window gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void run() {
        // call load function that load all files and other things referred the menu
        load();

        // menu loop
        while (true) {
            // draw in the screen
            draw();
            // update function, i really explain it?
            update();
        }
    }

    private void load() {
        gameKeyboard = gameWindow.getKeyboard();
        gameKeyboard.addKey(KeyEvent.VK_P);
        gameKeyboard.addKey(KeyEvent.VK_M);

        unitSize = gameWindow.getHeight() / 30;
        String imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_off.png";
        grid = new Grid(new Point(15, 20), imagePath, new Color(137, 151, 116));
        //snake = new Snake(Constants.RIGHT, Color.BLACK, Color.DARK_GRAY);
        imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_on.png";
        snake = new Snake(Constants.RIGHT, imagePath);
        snake.addSegments(grid.getGridSize().x / 2, grid.getGridSize().y / 2);
        //imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_on.png";
        food = new Food(new Point(0, 0), imagePath);
        musicOn = true;
        gameStatus = Constants.IN_GAME;
        delay = 100;
        score = 0;
        level = 1;
        foodCount = 0;

        String path = Constants.RESOURCES + "default/" + Constants.FONTS + "digital-7.ttf";
        try {
            menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont((float) unitSize * 2);
        } catch (FontFormatException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        pauseBtn = new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "pause_btn.png", gameWindow);
        musicBtn = new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "music_btn.png", gameWindow);

    }

    private void update() {

        if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {
            gameWindow.exit();
        }

        if (gameKeyboard.keyDown(KeyEvent.VK_M)) {
            if (musicOn) {
                musicOn = false;
            } else {
                musicOn = true;
            }
        }

        if (gameKeyboard.keyDown(Keyboard.ENTER_KEY)) {
            if(!snake.isAlive()){
                run();
            }
        }

        snake.keyboardActions(gameKeyboard);

        if (gameStatus == Constants.IN_GAME) {
            if (snake.getSegments().get(0).equals(food.getPosition())) {
                snake.eat(grid.getGridSize());
                food.newPosition(grid.getGridSize(), snake.getSegments());
                score += 100;
                if (foodCount == 9) {
                    level++;
                    foodCount = 0;
                } else {
                    foodCount++;
                }
            } else {
                if (snake.isAlive()) {
                    snake.move(grid.getGridSize());
                }
            }
        }

        gameWindow.update();
        gameWindow.delay(delay);
    }

    private void draw() {
        gameWindow.clear(new Color(137, 151, 116));

        grid.draw(gameWindow, unitSize);
        food.draw(gameWindow, grid.getGridSize(), unitSize);
        snake.draw(gameWindow, grid.getGridSize(), unitSize);

        Label scoreLabel = new Label("SCORE", menuFont, Color.black);

        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = (gameWindow.getHeight() - (grid.getGridSize().y * unitSize)) / 2;
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        scoreLabel.setLabel(Integer.toString(score));
        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = ((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + (unitSize + scoreLabel.height);
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        scoreLabel.setLabel("HI-SCORE");
        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = ((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 2);
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        scoreLabel.setLabel("999.999.999");
        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = ((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 3);
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        scoreLabel.setLabel("LEVEL");
        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = ((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 5);
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        scoreLabel.setLabel(Integer.toString(level));
        scoreLabel.x = ((gameWindow.getWidth() / 4) * 3) - scoreLabel.width / 2;
        scoreLabel.y = ((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 6);
        gameWindow.drawText(scoreLabel.getLabel(), (int) scoreLabel.x, (int) scoreLabel.y, scoreLabel.getColor(), menuFont);

        pauseBtn.setX((gameWindow.getWidth() / 4) * 3);
        pauseBtn.setY(((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 7));
        pauseBtn.setWidth(4 * unitSize);
        pauseBtn.setHeight(4 * unitSize);

        if (snake.isPause()) {
            pauseBtn.draw();
        } else {
            pauseBtn.drawSelected();
        }

        musicBtn.setX(((gameWindow.getWidth() / 4) * 3) - 4 * unitSize);
        musicBtn.setY(((gameWindow.getHeight() - grid.getGridSize().y * unitSize) / 2) + ((unitSize + scoreLabel.height) * 7));
        musicBtn.setWidth(4 * unitSize);
        musicBtn.setHeight(4 * unitSize);

        if (musicOn) {
            musicBtn.draw();
        } else {
            musicBtn.drawSelected();
        }
    }
}
