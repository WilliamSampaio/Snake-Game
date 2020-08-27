package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Window;

/**
 *
 * @author William Benjamim Menezes Sampaio
 * @link    https://github.com/WilliamSampaio
 * @link    williambenjamimms97@gmail.com
 */
public class Menu {

    private Window gameWindow;
    private Keyboard gameKeyboard;
    private Mouse gameMouse;

    private GameImage background;
    //private Font menuFont;
    private List<Button> options;

    private int option;
    private int selectedOption;

    /**
     *
     * @return
     */
    public int getOption() {
        return option;
    }

    /**
     *
     * @return
     */
    public int getSelectedOption() {
        return selectedOption;
    }

    /**
     *
     * @param gameWindow
     * @throws IOException
     * @throws FontFormatException
     */
    public Menu(Window gameWindow) throws IOException, FontFormatException {

        this.gameWindow = gameWindow;
        gameKeyboard = this.gameWindow.getKeyboard();
        gameMouse = this.gameWindow.getMouse();
        run();

    }

    /**
     *
     */
    public void nextOption() {

        switch (option) {
            case 1:
                option = 2;
                break;
            case 2:
                option = 3;
                break;
            default:
                option = 1;
                break;
        }

    }

    /**
     *
     */
    public void previousOption() {

        switch (option) {
            case 1:
                option = 3;
                break;
            case 2:
                option = 1;
                break;
            default:
                option = 2;
                break;
        }

    }

    /**
     *
     * @param key
     */
    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            previousOption();
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            nextOption();
        }

        if (code == KeyEvent.VK_ENTER) {
            switch (option) {
                case 1:
                    selectedOption = 1;
                    break;
                case 2:
                    selectedOption = 2;
                    break;
                case 3:
                    selectedOption = 3;
                    break;
            }
        }

    }

    private void run() throws FontFormatException, IOException {
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

    private void load() throws FontFormatException, IOException, IOException {
        // load and set dimensions and position of the background image
        background = new GameImage(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu.png");
        Dimension aux = new Dimension(background.width, background.height);
        background.height = gameWindow.getHeight();
        background.width = (aux.width * gameWindow.getHeight()) / aux.height;
        background.x = (gameWindow.getWidth() - background.width) / 2;

        /*String path = Constants.RESOURCES + "default/" + Constants.FONTS + "digitalix.ttf";
        menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont((float) 48 * 2);*/
        // initialize and add buttons to menu options
        options = new ArrayList<>();
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 0 + ".png", gameWindow));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 1 + ".png", gameWindow));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 2 + ".png", gameWindow));

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

    private void update() {

        gameWindow.update();

        if (gameMouse.isLeftButtonPressed()) {
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).isMouseOn()) {
                    option = i;
                }
            }
        }
    }

    private void draw() {
        // draws a rectangle behind the background image in a specific color
        gameWindow.getGameGraphics().setColor(new Color(137, 151, 116));
        gameWindow.getGameGraphics().fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());

        // draw a background image
        background.draw();

        // loop to draw a menu options
        for (int i = 0; i < options.size(); i++) {
            // draw the menu indexed option
            options.get(i).draw();
        }
    }
}
