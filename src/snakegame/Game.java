package snakegame;

import java.awt.Color;
import java.awt.Point;
import jplay.Keyboard;
import jplay.Window;

/**
 *
 * @author  William Benjamim Menezes Sampaio
 * GitHub  https://github.com/WilliamSampaio
 * E-mail  williambenjamimms97@gmail.com
 */
public final class Game {

    private Grid grid;
    private Snake snake;
    private Food food;
    private Point screenSize;
    private int unitSize;
    private boolean pause;
    private int gameStatus;

    private Window gameWindow;
    private Keyboard gameKeyboard;

    private int delay;

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
        pause = false;

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
        unitSize = gameWindow.getHeight() / 32;
        String imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_off.png";
        grid = new Grid(new Point(15, 20), imagePath, new Color(137, 151, 116));
        //snake = new Snake(Constants.RIGHT, Color.BLACK, Color.DARK_GRAY);
        imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_on.png";
        snake = new Snake(Constants.RIGHT, imagePath);
        snake.addSegments(grid.getGridSize().x / 2, grid.getGridSize().y / 2);
        //imagePath = Constants.RESOURCES + "default/" + Constants.SPRITES + "unit_on.png";
        food = new Food(new Point(0, 0), imagePath);
        pause = false;
        gameStatus = Constants.IN_GAME;
        delay = 100;
    }

    private void update() {
        

        if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {
            gameWindow.exit();
            // new Menu(gameWindow);
        }

        //if(!snake.isKeyPressed()){
        snake.keyboardActions(gameKeyboard);
        //}

        if (gameStatus == Constants.IN_GAME) {
            if (snake.getSegments().get(0).equals(food.getPosition())) {
                snake.eat(grid.getGridSize());
                food.newPosition(grid.getGridSize(), snake.getSegments());
                //delay += 50;
            } else {
                if (snake.isAlive()) {
                    snake.move(grid.getGridSize());
                }
            }
        }

        
        //gameWindow.set
        gameWindow.update();
        //gameWindow.delay(delay);
        
        
        //gameWindow.delay(1000);
    }

    private void draw() {
        gameWindow.clear(new Color(137, 151, 116));
        //gameWindow.drawText(" teste", gameWindow.getWidth() / 2, gameWindow.getHeight() / 2, Color.yellow);
        //grid.paint(graficos, screenSize, unitSize);
        // food.paint(graficos, screenSize, grid.getGridSize(), unitSize);
        //snake.paint(gameWindow, screenSize, grid.getGridSize(), unitSize);
        grid.draw(gameWindow, unitSize);
        food.draw(gameWindow, grid.getGridSize(), unitSize);
        snake.draw(gameWindow, grid.getGridSize(), unitSize);

    }

    /**
     *
     * @param arg0
     */
    /*@Override
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
     *//*
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

    }*/

 /*private class KeyBoardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            musicPlayer.keyPressed(e);

            if (gameStatus == Constants.IN_MENU) {
                //menu.keyPressed(e);
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
    }*/
}
