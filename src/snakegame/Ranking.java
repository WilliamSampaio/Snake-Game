/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

/**
 *
 * @author william
 */
public class Ranking extends Constants {

    private Window gameWindow;
    private GameImage background;
    private Color backColor;

    public List<Button> options;
    public int option;

    public Ranking(Window gameWindow, String filePath, Color backColor) throws FontFormatException, IOException {
        this.gameWindow = gameWindow;
        background = new GameImage(filePath);
        this.backColor = backColor;

        Dimension aux = new Dimension(background.width, background.height);
        background.width = ((gameWindow.getWidth() / 2) / 4) * 3;
        background.height = (aux.height * (((gameWindow.getWidth() / 2) / 4) * 3)) / aux.width;

        background.x = (gameWindow.getWidth() - background.width) / 2;
        background.y = /*(gameWindow.getHeight() - background.height) / 2*/ 0;

        Font menuFont;
        menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(DIR_FONTS + "digitalix.ttf")).deriveFont((float) gameWindow.getHeight() / 32);

        options = new ArrayList<>();
        options.add(new Button("RESUME", menuFont, Color.BLACK, gameWindow));
        options.add(new Button("MUSIC ON/OFF", menuFont, Color.BLACK, gameWindow));
        options.add(new Button("RANKING", menuFont, Color.BLACK, gameWindow));
        options.add(new Button("MAIN MENU", menuFont, Color.BLACK, gameWindow));
        options.add(new Button("EXIT", menuFont, Color.BLACK, gameWindow));

        for (int i = 0; i < options.size(); i++) {
            options.get(i).setX((int) ((background.x + (background.width / 2)) - options.get(i).getWidth() / 2));
            options.get(i).setY((int) (background.y + (((background.height - (options.get(i).getHeight() * options.size())) / 2)) + (options.get(i).getHeight() + 8) * i));
        }

        option = 0;

    }

    public void draw() {
        gameWindow.clear(backColor);
        background.draw();

        // loop to draw a background options
        for (int i = 0; i < options.size(); i++) {
            // draw the background indexed option
            if (option == i) {
                options.get(i).drawSelected();
            } else {
                options.get(i).draw();
            }

        }

    }

    /*private void mouseActions() {
        // loop to verify if some option is mouse cursor over. if on, option receive index
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isMouseOn()) {
                option = i;
            }
        }

        // verify if mouse left button has pressed
        if (gameWindow.getMouse().isLeftButtonPressed()) {
            // loop to verify if left mouse has pressed over some option
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).isMouseOn()) {
                    switch (option) {
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
                            break;
                        case 3:
                            Menu background = new Menu(gameWindow);
                            background.run();
                            break;
                        case 4:
                            gameWindow.exit();
                            break;
                    }
                }
            }
        }
    }*/
    public void keyboardActions() {
        // if W key was pressed option receive the back option
        if (gameWindow.getKeyboard().keyDown(KeyEvent.VK_W)) {
            if (option == 0) {
                option = options.size() - 1;
            } else {
                option--;
            }
        }

        // if UP key was pressed option receive the back option
        if (gameWindow.getKeyboard().keyDown(Keyboard.UP_KEY)) {
            if (option == 0) {
                option = options.size() - 1;
            } else {
                option--;
            }
        }

        // if S key was pressed option receive next option
        if (gameWindow.getKeyboard().keyDown(KeyEvent.VK_S)) {
            if (option == options.size() - 1) {
                option = 0;
            } else {
                option++;
            }
        }

        // if DOWN key was pressed option receive next option
        if (gameWindow.getKeyboard().keyDown(Keyboard.DOWN_KEY)) {
            if (option == options.size() - 1) {
                option = 0;
            } else {
                option++;
            }
        }

    }

}
