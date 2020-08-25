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
import jplay.Window;

public class Menu {

    private Window gameWindow;
    private Keyboard gameKeyboard;
    private Mouse gameMouse;

    private GameImage background;
    private Font menuFont;
    private List<Label> menuOptions;

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
        menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont((float) 50 * 2);
        menuOptions = new ArrayList<>();
        menuOptions.add(new Label("NEW GAME", menuFont, Color.BLACK));
        menuOptions.add(new Label("settings", menuFont, Color.BLACK));
        menuOptions.add(new Label("aboult", menuFont, Color.BLACK));
        menuOptions.add(new Label("exit", menuFont, Color.BLACK));
        option = 0;
    }

    private void update() {

        gameWindow.update();
        /*gameWindow.getKeyboard().
        if (gameKeyboard.keyPressed(KeyEvent.VK_DOWN == gameKeyboard.keyDown(KeyEvent.VK_UP))) {
            option++;
            if (option == menuOptions.size() - 1) {
                option = 0;
            }
        } else {
        }

        if (gameKeyboard.keyPressed(Keyboard.UP_KEY)) {
            option--;
            if (option < 0) {
                option = menuOptions.size() - 1;
            }
        }*/
    }

    private void draw() {
        background.draw();

        for (int i = 0; i < menuOptions.size(); i++) {
            Label l = menuOptions.get(i);
            if (i == option) {
                gameWindow.drawText(
                        l.getLabel(),
                        (gameWindow.getWidth() - l.width) / 2,
                        ((gameWindow.getHeight() - (l.height * (menuOptions.size() - 1))) / 2) + (i * l.height/* + 15*/),
                        Color.BLUE,
                        l.getFont());
            } else {
                gameWindow.drawText(
                        l.getLabel(),
                        (gameWindow.getWidth() - l.width) / 2,
                        ((gameWindow.getHeight() - (l.height * (menuOptions.size() - 1))) / 2) + (i * l.height),
                        l.getColor(),
                        l.getFont());
            }

        }
    }

}
