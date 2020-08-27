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
import jplay.Animation;
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
    //private List<Label> options;
    //private List<Sprite> options;
    private List<Button> options;
    
    private Animation a;

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
        //option = 0;

        run();

        /*menuScreen = new ImageIcon(getClass().getClassLoader().getResource("menu_screen.png")).getImage();
        dimension = new Point();
        /*dimension.x = menuScreen.getWidth(null) * unitSize;
        dimension.y = menuScreen.getHeight(null) * unitSize;*/
        position = new Point();
        /*position.x = (screenSize.x - dimension.x) / 2;
        position.y = (screenSize.y - dimension.y) / 2;*/

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
        options = new ArrayList<>();
        /*options.add(new Label("NEW GAME", menuFont, Color.BLACK));
        options.add(new Label("SETTINGS", menuFont, Color.BLACK));
        options.add(new Label("ABOULT", menuFont, Color.BLACK));
        options.add(new Label("EXIT", menuFont, Color.BLACK));*/
        //options.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_newgame.png", 2));
        /*options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + i + ".png", gameMouse));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + i + ".png", gameMouse));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + i + ".png", gameMouse));*/

 /*GameImage auxImage = new GameImage(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 0 + ".png");
        aux = new Dimension(auxImage.width, auxImage.height);
        auxImage.height = gameWindow.getHeight() / 20;
        auxImage.width = (aux.width * auxImage.height) / aux.height;
        auxImage.x = (gameWindow.getWidth() - auxImage.width) / 2;
        auxImage.y = ((gameWindow.getHeight() - (options.size() * auxImage.height)) / 2) + (0 * auxImage.height);*/
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 0 + ".png", gameWindow));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 1 + ".png", gameWindow));
        options.add(new Button(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 2 + ".png", gameWindow));

        for (int i = 0; i < options.size(); i++) {
            aux = new Dimension(options.get(i).getWidth(), options.get(i).getHeight());
            options.get(i).setHeight(gameWindow.getHeight() / 20);
            options.get(i).setWidth((aux.width * options.get(i).getHeight()) / aux.height);
            options.get(i).setX((gameWindow.getWidth() - options.get(i).getWidth()) / 2);
            options.get(i).setY(((gameWindow.getHeight() - (options.size() * options.get(i).getHeight())) / 2) + (i * options.get(i).getHeight()));
        }

        /*a = new Animation(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_opt_" + 0 + ".png", 20);
        a.width = 900;
        a.height = 20;*/
        /*options.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_about.png", 2));
        options.add(new Sprite(Constants.RESOURCES + "default/" + Constants.SPRITES + "menu_exit.png", 2));*/
 /*options.add(new Label("SETTINGS", menuFont, Color.BLACK));
        options.add(new Label("ABOULT", menuFont, Color.BLACK));
        options.add(new Label("EXIT", menuFont, Color.BLACK));*/
        option = 0;
    }

    private void update() {

        gameWindow.update();
        //gameWindow.getKeyboard().
        /*if (gameKeyboard.keyDown(Keyboard.DOWN_KEY)) {
            option++;
            if (option == options.size() - 1) {
                option = 0;
            }
        } else {
        }

        if (gameKeyboard.keyDown(Keyboard.UP_KEY)) {
            option--;
            if (option < 0) {
                option = options.size() - 1;
            }
        }*/

        if (gameMouse.isLeftButtonPressed()) {
            for (int i = 0; i < options.size(); i++) // Verifica se o mouse estava sobre algum botão
            {
                if (options.get(i).isMouseOn()) {
                    option = i;
                }
            }
        }

        /*if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
            opc = buttons.size() - 1;
        }

        Draw();*/
    }

    private void draw() {
        gameWindow.getGameGraphics().setColor(new Color(137, 151, 116));
        gameWindow.getGameGraphics().fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());
        background.draw();
        //a.draw();
        for (int i = 0; i < options.size(); i++) {

            options.get(i).draw();

        }
    }
}
