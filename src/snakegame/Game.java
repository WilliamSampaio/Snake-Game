package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jplay.Keyboard;
import jplay.Window;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public final class Game extends Constants {

    private Window gameWindow;
    private Grid grid;
    private Food food;
    private Snake snake;
    private HUD hud;

    private int UNIT_SIZE;
    private boolean MUSIC_STATUS;

    private int GAME_STATUS;
    private int SCORE;
    private int HI_SCORE;
    private int LEVEL;
    private int FOOD_COUNT;

    private Keyboard gameKeyboard;

    private Button pauseBtn;
    private Button musicBtn;

    private int DELAY;
    private Font menuFont;

    private MenuInGame menuInGame;

    private Document ranking;
    private long previusTime;
    private long timer;

    private List<String> playerNames;
    private List<Integer> highScores;

    private Ranking ranking2;
    private RankingScreen ranking1;
    private List<RankElement> rankingList;

    public Game(Window gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void run() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // call load function that load all files and other things referred the menu
        load();
        // menu loop
        while (true) {
            // update function, i really explain it?
            update();
            // draw in the screen
            draw();
        }

    }

    private void load() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // game status receive in game
        GAME_STATUS = IN_GAME;
        // previusTime time receive the game window elapsed time
        previusTime = gameWindow.timeElapsed();
        // timer receive 0
        timer = 0;
        // set the game keyboard
        gameKeyboard = gameWindow.getKeyboard();
        // set the unit size
        UNIT_SIZE = gameWindow.getHeight() / 30;
        // initialize the grid object
        grid = new Grid(gameWindow, UNIT_SIZE, new Point(15, 20), DIR_SPRITES + "unit_off.png", new Color(137, 151, 116));
        // initialize the food object
        food = new Food(gameWindow, grid.getGridSize(), UNIT_SIZE, new Point(grid.getGridSize().x / 2, grid.getGridSize().y / 2), DIR_SPRITES + "unit_on.png");
        // initialize the snake object and add a segment
        snake = new Snake(gameWindow, grid.getGridSize(), UNIT_SIZE, RIGHT, DIR_SPRITES + "unit_on.png");
        snake.addSegments(grid.getGridSize().x / 2, grid.getGridSize().y / 2);
        // initialize the HeadsUp display
        menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(DIR_FONTS + "digital-7.ttf")).deriveFont((float) UNIT_SIZE * 2).deriveFont(Font.PLAIN);
        hud = new HUD(gameWindow, grid, UNIT_SIZE, menuFont, Color.BLACK);
        MUSIC_STATUS = true;

        DELAY = 750;

        SCORE = 0;
        HI_SCORE = 0;
        LEVEL = 1;
        FOOD_COUNT = 0;

        pauseBtn = new Button(DIR_SPRITES + "pause_btn.png", gameWindow);
        pauseBtn.setX((gameWindow.getWidth() / 4) * 3);
        pauseBtn.setY((gameWindow.getHeight() - grid.getGridSize().y * UNIT_SIZE) / 2 + (grid.getGridSize().y * UNIT_SIZE) - pauseBtn.getHeight());
        pauseBtn.setWidth(3 * UNIT_SIZE);
        pauseBtn.setHeight(3 * UNIT_SIZE);

        musicBtn = new Button(DIR_SPRITES + "music_btn.png", gameWindow);
        musicBtn.setX(((gameWindow.getWidth() / 4) * 3) - 4 * UNIT_SIZE);
        musicBtn.setY((gameWindow.getHeight() - grid.getGridSize().y * UNIT_SIZE) / 2 + (grid.getGridSize().y * UNIT_SIZE) - musicBtn.getHeight());
        musicBtn.setWidth(3 * UNIT_SIZE);
        musicBtn.setHeight(3 * UNIT_SIZE);

        menuInGame = new MenuInGame(gameWindow, DIR_SPRITES + "menu_ingame.png", new Color(137, 151, 116));

        ranking1 = new RankingScreen(gameWindow, DIR_SPRITES + "ranking.png");

        playerNames = new ArrayList<>();
        highScores = new ArrayList<>();

        ranking2 = new Ranking(DIR_DATA + "ranking.xml");

        rankingList = new Ranking(DIR_DATA + "ranking.xml").getRankingList();

        HI_SCORE = Integer.parseInt(rankingList.get(0).getPlayerScore());

        /*// read the ranking.xml file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ranking = builder.parse(DIR_DATA + "ranking.xml");*/

 /*NodeList players = ranking.getElementsByTagName("player");

        if (players.getLength() > 0) {

            NodeList names = ranking.getElementsByTagName("name");
            NodeList scores = ranking.getElementsByTagName("score");

            for (int i = 0; i < players.getLength(); i++) {

                playerNames.add(names.item(i).getTextContent());
                highScores.add(Integer.parseInt(scores.item(i).getTextContent()));

            }

            HI_SCORE = highScores.get(highScores.size() - 1);

        }*/
 /*Element name = (Element) names.item(0);

        NodeList scores = ranking.getElementsByTagName("SCORE");
        Element SCORE = (Element) scores.item(0);

        System.out.println("NAME: " + name.getTextContent());
        System.out.println("SCORE: " + SCORE.getTextContent());*/
    }

    private void update() throws SAXException, IOException, ParserConfigurationException, FontFormatException {

        if (GAME_STATUS == IN_GAME) {

            long currentTime = gameWindow.timeElapsed();
            timer += currentTime - previusTime;
            previusTime = currentTime;

            if (timer >= DELAY) {

                timer -= DELAY;

                if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {

                    GAME_STATUS = IN_GAME_MENU;
                    if (!snake.isPause()) {
                        snake.pauseOrPlay();
                    }

                }

                snake.keyboardActions(gameKeyboard);

                if (snake.getSegments().get(0).equals(food.getPosition())) {
                    snake.eat(grid.getGridSize());
                    food.newPosition(grid.getGridSize(), snake.getSegments());
                    SCORE += 100;
                    if (FOOD_COUNT == 9) {
                        LEVEL++;
                        FOOD_COUNT = 0;
                    } else {
                        FOOD_COUNT++;
                    }
                } else {
                    if (snake.isAlive()) {
                        snake.move(grid.getGridSize());
                    } else {
                        GAME_STATUS = IN_GAME_OVER;
                    }
                }

                if (gameKeyboard.keyDown(KeyEvent.VK_M)) {
                    if (MUSIC_STATUS) {
                        MUSIC_STATUS = false;
                    } else {
                        MUSIC_STATUS = true;
                    }
                }

            }

        }

        if (GAME_STATUS == IN_GAME_MENU) {

            if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {

                GAME_STATUS = IN_GAME;
                snake.pauseOrPlay();
                previusTime = gameWindow.timeElapsed();

            }

            menuInGameKeyboardActions();
            menuInGameMouseActions();
        }

        if (GAME_STATUS == IN_GAME_OVER) {

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            ranking = builder.parse(DIR_DATA + "ranking.xml");
            Node rootTag = ranking.getFirstChild();

            if (playerNames.size() >= 1) {

                if (highScores.get(highScores.size() - 1) < SCORE) {
                    HI_SCORE = SCORE;
                    String name = JOptionPane.showInputDialog(gameWindow, "PLAYER NAME:", null, JOptionPane.PLAIN_MESSAGE);
                    if (name == null) {
                        Menu menu = new Menu(gameWindow);
                        menu.run();
                    }
                    String newHighScore = Integer.toString(SCORE);

                    Element playerTag = ranking.createElement("player");

                    Text nameTagValue = ranking.createTextNode(name);
                    Element nameTag = ranking.createElement("name");
                    nameTag.appendChild(nameTagValue);

                    Text scoreTagValue = ranking.createTextNode(newHighScore);
                    Element scoreTag = ranking.createElement("score");
                    scoreTag.appendChild(scoreTagValue);

                    playerTag.appendChild(nameTag);
                    playerTag.appendChild(scoreTag);

                    rootTag.appendChild(playerTag);

                }

            } else if (playerNames.size() == 0) {

                HI_SCORE = SCORE;
                String name = JOptionPane.showInputDialog(gameWindow, "PLAYER NAME:", null, JOptionPane.PLAIN_MESSAGE);
                if (name == null) {
                    Menu menu = new Menu(gameWindow);
                    menu.run();
                }
                String newHighScore = Integer.toString(SCORE);

                Element playerTag = ranking.createElement("player");

                Text nameTagValue = ranking.createTextNode(name);
                Element nameTag = ranking.createElement("name");
                nameTag.appendChild(nameTagValue);

                Text scoreTagValue = ranking.createTextNode(newHighScore);
                Element scoreTag = ranking.createElement("score");
                scoreTag.appendChild(scoreTagValue);

                playerTag.appendChild(nameTag);
                playerTag.appendChild(scoreTag);

                rootTag.appendChild(playerTag);

            }

            try {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(new DOMSource(rootTag), new StreamResult(new FileOutputStream(DIR_DATA + "ranking.xml")));
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (JOptionPane.showConfirmDialog(gameWindow, "PLAY AGAIN??", null, JOptionPane.YES_NO_OPTION) == 0) {
                previusTime = gameWindow.timeElapsed();
                run();
            } else {
                Menu menu = new Menu(gameWindow);
                menu.run();
            }

        }

        if (GAME_STATUS == IN_GAME_RANK) {
            ranking1.load();

            if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY) || gameKeyboard.keyDown(Keyboard.ENTER_KEY)) {
                GAME_STATUS = IN_GAME_MENU;
            }

            if (gameWindow.getMouse().isLeftButtonPressed()) {
                if (ranking1.getBackButton().isMouseOn()) {
                    GAME_STATUS = IN_GAME_MENU;
                }
            }
        }

        hud.update(SCORE, HI_SCORE, LEVEL);
        gameWindow.update();

    }

    private void draw() throws ParserConfigurationException, SAXException, IOException {
        // clear the window with especifc color
        gameWindow.clear(new Color(137, 151, 116));
        // draw the grid, food, snake and hud
        grid.draw();
        food.draw();
        snake.draw();
        hud.draw();
        // if music is true
        if (MUSIC_STATUS) {
            // draw music icon
            musicBtn.draw();
        } else {
            // draw music icon
            musicBtn.drawSelected();
        }
        // if snake is paused
        if (snake.isPause()) {
            // draw pause icon
            pauseBtn.draw();
        } else {
            // draw pause icon
            pauseBtn.drawSelected();
        }
        // if game status is in game menu
        if (GAME_STATUS == IN_GAME_MENU) {
            // draw the in game menu
            menuInGame.draw();
        }
        // if game status is in game menu
        if (GAME_STATUS == IN_GAME_RANK) {
            // draw ranking
            gameWindow.clear(new Color(137, 151, 116));
            ranking1.draw();
        }

    }

    public void menuInGameKeyboardActions() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // if ENTER was pressed verify which option is selected and run
        if (gameKeyboard.keyDown(Keyboard.ENTER_KEY)) {
            switch (menuInGame.option) {
                case 0:
                    GAME_STATUS = IN_GAME;
                    snake.pauseOrPlay();
                    previusTime = gameWindow.timeElapsed();
                    break;
                case 1:
                    if (MUSIC_STATUS) {
                        MUSIC_STATUS = false;
                    } else {
                        MUSIC_STATUS = true;
                    }
                    break;
                case 2:
                    GAME_STATUS = IN_GAME_RANK;
                    break;
                case 3:
                    Menu menu = new Menu(gameWindow);
                    menu.run();
                    break;
                case 4:
                    gameWindow.exit();
                    break;
            }
        }

        // if W key was pressed option receive the back option
        if (gameKeyboard.keyDown(KeyEvent.VK_W)) {
            if (menuInGame.option == 0) {
                menuInGame.option = menuInGame.options.size() - 1;
            } else {
                menuInGame.option--;
            }
        }

        // if UP key was pressed option receive the back option
        if (gameKeyboard.keyDown(Keyboard.UP_KEY)) {
            if (menuInGame.option == 0) {
                menuInGame.option = menuInGame.options.size() - 1;
            } else {
                menuInGame.option--;
            }
        }

        // if S key was pressed option receive next option
        if (gameKeyboard.keyDown(KeyEvent.VK_S)) {
            if (menuInGame.option == menuInGame.options.size() - 1) {
                menuInGame.option = 0;
            } else {
                menuInGame.option++;
            }
        }

        // if DOWN key was pressed option receive next option
        if (gameKeyboard.keyDown(Keyboard.DOWN_KEY)) {
            if (menuInGame.option == menuInGame.options.size() - 1) {
                menuInGame.option = 0;
            } else {
                menuInGame.option++;
            }
        }

        // if ESC key was pressed independent of the option
        if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {
            //gameWindow.exit();
        }
    }

    public void menuInGameMouseActions() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // loop to verify if some option is mouse cursor over. if on, option receive index
        for (int i = 0; i < menuInGame.options.size(); i++) {
            if (menuInGame.options.get(i).isMouseOn()) {
                menuInGame.option = i;
            }
        }

        // verify if mouse left button has pressed
        if (gameWindow.getMouse().isLeftButtonPressed()) {
            // loop to verify if left mouse has pressed over some option
            for (int i = 0; i < menuInGame.options.size(); i++) {
                switch (menuInGame.option) {
                    case 0:
                        GAME_STATUS = IN_GAME;
                        snake.pauseOrPlay();
                        break;
                    case 1:
                        if (MUSIC_STATUS) {
                            MUSIC_STATUS = false;
                        } else {
                            MUSIC_STATUS = true;
                        }
                        break;
                    case 2:
                        GAME_STATUS = IN_GAME_RANK;
                        break;
                    case 3:
                        Menu menu = new Menu(gameWindow);
                        menu.run();
                        break;
                    case 4:
                        gameWindow.exit();
                        break;
                } // end switch
            } // end for
        } // end if

    }

}
