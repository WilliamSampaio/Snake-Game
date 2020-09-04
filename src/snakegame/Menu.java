package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Window;
import org.xml.sax.SAXException;

/**
 *
 * @author William Benjamim Menezes Sampaio
 * @link    https://github.com/WilliamSampaio
 * @link    williambenjamimms97@gmail.com
 */
public class Menu extends Constants {

    private final Window gameWindow;
    private Keyboard gameKeyboard;
    private Mouse gameMouse;
    private GameImage background;
    private List<Button> options;
    private int option;

    /**
     *
     * @param gameWindow
     */
    public Menu(Window gameWindow) {
        // get the game instance
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

    private void load() throws IOException {
        // initialize the keyboard and add keys
        gameKeyboard = gameWindow.getKeyboard();
        gameKeyboard.addKey(KeyEvent.VK_W);
        gameKeyboard.addKey(KeyEvent.VK_S);
        gameKeyboard.addKey(KeyEvent.VK_UP);
        gameKeyboard.addKey(KeyEvent.VK_DOWN);
        gameKeyboard.addKey(KeyEvent.VK_P);
        gameKeyboard.addKey(KeyEvent.VK_M);

        // initialize the mouse
        gameMouse = gameWindow.getMouse();

        // load and set dimensions and position of the background image
        background = new GameImage(DIR_SPRITES + "menu.png");
        Dimension aux = new Dimension(background.width, background.height);
        background.height = gameWindow.getHeight();
        background.width = (aux.width * gameWindow.getHeight()) / aux.height;
        background.x = (gameWindow.getWidth() - background.width) / 2;

        // initialize and add buttons to menu options
        options = new ArrayList<>();
        options.add(new Button(DIR_SPRITES + "menu_opt_0.png", gameWindow));
        options.add(new Button(DIR_SPRITES + "menu_opt_1.png", gameWindow));
        options.add(new Button(DIR_SPRITES + "menu_opt_2.png", gameWindow));

        // loop to set the size and the position of the buttons
        for (int i = 0; i < options.size(); i++) {
            aux = new Dimension(options.get(i).getWidth(), options.get(i).getHeight());
            options.get(i).setHeight(gameWindow.getHeight() / 20);
            options.get(i).setWidth((aux.width * options.get(i).getHeight()) / aux.height);
            options.get(i).setX((gameWindow.getWidth() - options.get(i).getWidth()) / 2);
            options.get(i).setY(((gameWindow.getHeight() - (options.size() * options.get(i).getHeight())) / 2) + (i * options.get(i).getHeight()));
        }

        // initialize with default option selected
        option = 0;
    }

    private void update() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // detect all mouse actions
        mouseActions();

        // detect all keyboard actions
        keyboardActions();

        // update the game instance
        gameWindow.update();
    }

    private void draw() {
        // clear the game window and paint with a specific color
        gameWindow.clear(new Color(137, 151, 116));

        // draw a background image
        background.draw();

        // loop to draw a menu options
        for (int i = 0; i < options.size(); i++) {
            // draw the menu indexed option
            if (option == i) {
                options.get(i).drawSelected();
            } else {
                options.get(i).draw();
            }
        }
    }

    private void exit() {
        gameWindow.exit();
    }

    private void mouseActions() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // loop to verify if some option is mouse cursor over. if on, option receive index
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isMouseOn()) {
                option = i;
            }
        }

        // verify if mouse left button has pressed
        if (gameMouse.isLeftButtonPressed()) {
            // loop to verify if left mouse has pressed over some option
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).isMouseOn()) {
                    switch (i) {
                        case 0:
                            new Game(gameWindow).run();
                            break;
                        case 1:
                            break;
                        case 2:
                            exit();
                            break;
                    }
                }
            }
        }
    }

    private void keyboardActions() throws SAXException, IOException, ParserConfigurationException, FontFormatException {
        // if ENTER was pressed verify which option is selected and run
        if (gameKeyboard.keyDown(Keyboard.ENTER_KEY)) {
            switch (option) {
                case 0:
                    new Game(gameWindow).run();
                    break;
                case 1:
                    break;
                case 2:
                    exit();
                    break;
            }
        }

        // if W key was pressed option receive the back option
        if (gameKeyboard.keyDown(KeyEvent.VK_W)) {
            if (option == 0) {
                option = options.size() - 1;
            } else {
                option--;
            }
        }

        // if UP key was pressed option receive the back option
        if (gameKeyboard.keyDown(Keyboard.UP_KEY)) {
            if (option == 0) {
                option = options.size() - 1;
            } else {
                option--;
            }
        }

        // if S key was pressed option receive next option
        if (gameKeyboard.keyDown(KeyEvent.VK_S)) {
            if (option == options.size() - 1) {
                option = 0;
            } else {
                option++;
            }
        }

        // if DOWN key was pressed option receive next option
        if (gameKeyboard.keyDown(Keyboard.DOWN_KEY)) {
            if (option == options.size() - 1) {
                option = 0;
            } else {
                option++;
            }
        }

        // if ESC key was pressed independent of the option
        if (gameKeyboard.keyDown(Keyboard.ESCAPE_KEY)) {
            //gameWindow.exit();
        }
    }
}
