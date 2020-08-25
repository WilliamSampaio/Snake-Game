package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Sprite;
import jplay.Window;

public class Menu {

    private Window gameWindow;
    private Keyboard gameKeyboard;
    private Mouse gameMouse;

    private GameImage background;
    private Font menuFont;
    //private List<Label> menuOptions;
    private List<Sprite> menuOptions;

    private Point position;
    private Point dimension;
    private Image menuScreen;
    private int option;
    private int selectedOption;

    public int getOption() {
        return option;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public Menu(Window gameWindow) throws IOException, FontFormatException {

        this.gameWindow = gameWindow;
        gameKeyboard = this.gameWindow.getKeyboard();
        gameMouse = this.gameWindow.getMouse();

        run();

        menuScreen = new ImageIcon(getClass().getClassLoader().getResource("menu_screen.png")).getImage();
        dimension = new Point();
        /*dimension.x = menuScreen.getWidth(null) * unitSize;
        dimension.y = menuScreen.getHeight(null) * unitSize;*/
        position = new Point();
        /*position.x = (screenSize.x - dimension.x) / 2;
        position.y = (screenSize.y - dimension.y) / 2;*/
        option = 1;
    }

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

    public Graphics2D paint(Graphics2D g, Point screenSize, int unitSize) {

        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(menuScreen, position.x, position.y, dimension.x, dimension.y, null);

        graficos.setColor(Color.BLACK);

        graficos.setStroke(new BasicStroke(10.0f));

        switch (option) {
            case 1:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (8 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
            case 2:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (20 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
            case 3:
                graficos.drawRect(
                        ((screenSize.x - dimension.x) / 2) + (7 * unitSize),
                        ((screenSize.y - dimension.y) / 2) + (32 * unitSize),
                        dimension.x - (14 * unitSize),
                        unitSize * 11);
                break;
        }

        return graficos;
    }

    private void run() throws FontFormatException, IOException {
        load();
        while (true) {
            update();
            draw();
        }
    }

    private void load() throws FontFormatException, IOException, IOException {
        background = new GameImage(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu.png");
        Dimension aux = new Dimension(background.width, background.height);
        background.height = gameWindow.getHeight();
        background.width = (aux.width * gameWindow.getHeight()) / aux.height;
        background.x = (gameWindow.getWidth() - background.width) / 2;
        String path = Constants.RESOURCES + "default/" + Constants.FONTS + "digitalix.ttf";
        menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont((float) 48 * 2);
        menuOptions = new ArrayList<>();
        /*menuOptions.add(new Label("NEW GAME", menuFont, Color.BLACK));
        menuOptions.add(new Label("SETTINGS", menuFont, Color.BLACK));
        menuOptions.add(new Label("ABOULT", menuFont, Color.BLACK));
        menuOptions.add(new Label("EXIT", menuFont, Color.BLACK));*/
        menuOptions.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_newgame.png", 2));
        menuOptions.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_about.png", 2));
        menuOptions.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_exit.png", 2));
        /*menuOptions.add(new Label("SETTINGS", menuFont, Color.BLACK));
        menuOptions.add(new Label("ABOULT", menuFont, Color.BLACK));
        menuOptions.add(new Label("EXIT", menuFont, Color.BLACK));*/
        option = 0;
    }

    private void update() {

        gameWindow.update();
        //gameWindow.getKeyboard().
        if (gameKeyboard.keyDown(Keyboard.DOWN_KEY)) {
            option++;
            if (option == menuOptions.size() - 1) {
                option = 0;
            }
        } else {
        }

        if (gameKeyboard.keyDown(Keyboard.UP_KEY)) {
            option--;
            if (option < 0) {
                option = menuOptions.size() - 1;
            }
        }
    }

    private void draw() {
        gameWindow.getGameGraphics().setColor(new Color(137, 151, 116));
        gameWindow.getGameGraphics().fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());
        background.draw();

        for (int i = 0; i < menuOptions.size(); i++) {
            
            menuOptions.get(i).x = (gameWindow.getWidth() - menuOptions.get(i).width) / 2;
            menuOptions.get(i).y = ((gameWindow.getHeight() - (menuOptions.size() * menuOptions.get(i).height)) / 2) + i * menuOptions.get(i).height;
            
            if(option == i){
                
                menuOptions.get(i).setCurrFrame(1);
                menuOptions.get(i).draw();
            }else{
                menuOptions.get(i).setCurrFrame(0);
                menuOptions.get(i).draw();
            }
            
            
            
            /*Label l = menuOptions.get(i);

            if (i == option) {
                gameWindow.getGameGraphics().setColor(l.getColor());
                gameWindow.getGameGraphics().fillRect(
                        (gameWindow.getWidth() - l.width) / 2,
                        (gameWindow.getHeight() - (l.height * (menuOptions.size() - 1))) / 2,
                        //((gameWindow.getHeight() - ((l.height + l.height / 2) * (menuOptions.size() - 1))) / 2), // Y position
                        l.width,
                        l.height);
                gameWindow.drawText(
                        l.getLabel(), //string text
                        (gameWindow.getWidth() - l.width) / 2, // X position
                        ((gameWindow.getHeight() - ((l.height + l.height / 2) * (menuOptions.size() - 1))) / 2) + (i * (l.height + l.height / 2)), // Y position
                        new Color(137, 151, 116), // font color
                        l.getFont()); // set font
            }else{
                gameWindow.drawText(
                        l.getLabel(), //string text
                        (gameWindow.getWidth() - l.width) / 2, // X position
                        ((gameWindow.getHeight() - ((l.height + l.height / 2) * (menuOptions.size() - 1))) / 2) + (i * (l.height + l.height / 2)), // Y position
                        l.getColor(), // font color
                        l.getFont()); // set font
            }*/
        }
    }
}
